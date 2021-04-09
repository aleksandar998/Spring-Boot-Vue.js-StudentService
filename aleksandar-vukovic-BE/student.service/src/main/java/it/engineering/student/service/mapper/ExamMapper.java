package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.ExamDto;
import it.engineering.student.service.entity.Exam;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper {
    ExamDto toExamDto(Exam exam);
    Exam toExam(ExamDto examDto);
}
