package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.ExamDto;
import it.engineering.student.service.entity.Exam;
import it.engineering.student.service.entity.Professor;
import it.engineering.student.service.entity.Subject;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.mapper.ExamMapper;
import it.engineering.student.service.repository.ExamRepository;
import it.engineering.student.service.repository.ProfessorRepository;
import it.engineering.student.service.repository.SubjectRepository;
import it.engineering.student.service.service.ExamService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository, SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<ExamDto> findAll() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(exam -> {
            return examMapper.toExamDto(exam);
        }).collect(Collectors.toList());
    }

    @Override
    public ExamDto save(ExamDto examDto) throws Exception {
        Optional<Exam> exam = examRepository.findById(examDto.getExamId());
        if (exam.isPresent()){
            throw new ExistEntityException(exam.get(), "Exam already exists!");
        }
        Exam e = examMapper.toExam(examDto);
        Optional<Professor> p = professorRepository.findById(examDto.getProfessor().getProfessorId());
        Optional<Subject> s = subjectRepository.findById(examDto.getSubject().getSubjectId());
        if (p.isPresent()&& s.isPresent()){
            e.setProfessor(p.get());
            e.setSubject(s.get());
        }
        return examMapper.toExamDto(examRepository.save(e));
    }
}
