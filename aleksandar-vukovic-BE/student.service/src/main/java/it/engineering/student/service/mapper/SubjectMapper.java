package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.SubjectDto;
import it.engineering.student.service.entity.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper {
    SubjectDto toSubjectDto(Subject subject);
    Subject toSubject(SubjectDto subjectDto);
}
