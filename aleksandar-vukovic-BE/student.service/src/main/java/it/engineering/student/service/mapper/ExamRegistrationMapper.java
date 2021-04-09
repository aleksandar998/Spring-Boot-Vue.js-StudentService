package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.ExamRegistrationDto;
import it.engineering.student.service.entity.ExamRegistration;
import org.mapstruct.Mapper;

@Mapper
public interface ExamRegistrationMapper {
    ExamRegistrationDto toExamRegistrationDto(ExamRegistration examRegistration);
    ExamRegistration toExamRegistration(ExamRegistrationDto examRegistrationDto);
}
