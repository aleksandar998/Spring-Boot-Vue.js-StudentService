package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.SubjectDto;
import it.engineering.student.service.entity.Subject;
import it.engineering.student.service.exception.EntityNotPresent;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.mapper.SubjectMapper;
import it.engineering.student.service.repository.SubjectRepository;
import it.engineering.student.service.service.SubjectService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectDto> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(entity -> {
            return subjectMapper.toSubjectDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(subjectDto.getSubjectId());
        if (subject.isPresent()){
            throw new ExistEntityException(subject.get(), "Subject already exists!");
        }
        Subject s = subjectRepository.save(subjectMapper.toSubject(subjectDto));
        return subjectMapper.toSubjectDto(s);
    }

    @Override
    public SubjectDto update(SubjectDto subjectDto) throws EntityNotPresent {
        Optional<Subject> subject = subjectRepository.findById(subjectDto.getSubjectId());
        if (!subject.isPresent()){
            throw new EntityNotPresent(subjectDto.getSubjectId(), "Subject does not exist!");
        }
        Subject s = subjectRepository.save(subjectMapper.toSubject(subjectDto));
        return subjectMapper.toSubjectDto(s);
    }

    @Override
    public Optional<SubjectDto> findById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()){
            return Optional.of(subjectMapper.toSubjectDto(subject.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<SubjectDto> findByPage(Pageable pageable) {
        Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toSubjectDto);
        return entites;
    }
}
