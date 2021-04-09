package it.engineering.student.service.service;

import it.engineering.student.service.dto.SubjectDto;
import it.engineering.student.service.exception.EntityNotPresent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<SubjectDto> findAll();

    void deleteById(Long id);

    SubjectDto save(SubjectDto subjectDto) throws Exception;

    SubjectDto update(SubjectDto subjectDto) throws EntityNotPresent;

    Optional<SubjectDto> findById(Long id);

    Page<SubjectDto> findByPage(Pageable pageable);
}
