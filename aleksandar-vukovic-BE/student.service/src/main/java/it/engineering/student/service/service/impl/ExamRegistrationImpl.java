package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.ExamRegistrationDto;
import it.engineering.student.service.entity.Exam;
import it.engineering.student.service.entity.ExamRegistration;
import it.engineering.student.service.entity.Student;
import it.engineering.student.service.exception.ExistEntityException;
import it.engineering.student.service.mapper.ExamRegistrationMapper;
import it.engineering.student.service.repository.ExamRegistrationRepository;
import it.engineering.student.service.repository.ExamRepository;
import it.engineering.student.service.repository.StudentRepository;
import it.engineering.student.service.service.ExamRegistrationService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamRegistrationImpl implements ExamRegistrationService {
    private final ExamRegistrationRepository examRegistrationRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ExamRegistrationMapper examRegistrationMapper = Mappers.getMapper(ExamRegistrationMapper.class);

    @Autowired
    public ExamRegistrationImpl(ExamRegistrationRepository examRegistrationRepository,
                                StudentRepository studentRepository,
                                ExamRepository examRepository) {
        this.examRegistrationRepository = examRegistrationRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Override
    public List<ExamRegistrationDto> findAll() {
        List<ExamRegistration> examRegistrations = examRegistrationRepository.findAll();
        return examRegistrations.stream().map(examRegistration -> {
            return examRegistrationMapper.toExamRegistrationDto(examRegistration);
        }).collect(Collectors.toList());
    }

    @Override
    public ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception {
        Optional<ExamRegistration> examRegistration = examRegistrationRepository.findById(examRegistrationDto.getId());
        if (examRegistration.isPresent()){
            throw new ExistEntityException(examRegistration.get(), "Registration already exists!");
        }
        ExamRegistration e = examRegistrationMapper.toExamRegistration(examRegistrationDto);
        Optional<Student> student = studentRepository.findById(examRegistrationDto.getStudent().getStudentId());
        Optional<Exam> exam = examRepository.findById(examRegistrationDto.getExam().getExamId());
        if (student.isPresent() && exam.isPresent()){
            e.setStudent(student.get());
            e.setExam(exam.get());
        }
        return examRegistrationMapper.toExamRegistrationDto(examRegistrationRepository.save(e));
    }
}
