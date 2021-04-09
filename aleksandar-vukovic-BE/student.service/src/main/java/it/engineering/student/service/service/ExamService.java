package it.engineering.student.service.service;

import it.engineering.student.service.dto.ExamDto;

import java.util.List;

public interface ExamService {
    List<ExamDto> findAll();

    ExamDto save(ExamDto examDto) throws Exception;
}
