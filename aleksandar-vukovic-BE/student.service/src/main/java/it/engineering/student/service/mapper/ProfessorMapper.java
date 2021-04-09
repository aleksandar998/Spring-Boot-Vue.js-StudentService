package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.ProfessorDto;
import it.engineering.student.service.entity.Professor;
import org.mapstruct.Mapper;

@Mapper
public interface ProfessorMapper {
    ProfessorDto toProfessorDto(Professor professor);
    Professor toProfessor(ProfessorDto professorDto);
}
