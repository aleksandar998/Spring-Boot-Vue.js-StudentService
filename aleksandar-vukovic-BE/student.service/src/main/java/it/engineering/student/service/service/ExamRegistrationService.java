package it.engineering.student.service.service;

import it.engineering.student.service.dto.ExamRegistrationDto;

import java.util.List;

public interface ExamRegistrationService {
    List<ExamRegistrationDto> findAll();
    ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception;
}
