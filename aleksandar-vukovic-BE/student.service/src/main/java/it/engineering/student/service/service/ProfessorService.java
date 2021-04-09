package it.engineering.student.service.service;

import it.engineering.student.service.dto.ProfessorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<ProfessorDto> findAll();

    void deleteById(Long id);

    ProfessorDto save(ProfessorDto professorDto) throws Exception;

    ProfessorDto update(ProfessorDto professorDto) throws Exception;

    Optional<ProfessorDto> findById(Long id);

    Page<ProfessorDto> findByPage(Pageable pageable);
}
