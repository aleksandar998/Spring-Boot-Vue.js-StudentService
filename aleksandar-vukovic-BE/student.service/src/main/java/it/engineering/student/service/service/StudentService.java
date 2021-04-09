package it.engineering.student.service.service;

import it.engineering.student.service.dto.StudentDto;
import it.engineering.student.service.exception.EntityNotPresent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> findAll();

    void deleteById(Long id);

    StudentDto save(StudentDto studentDto) throws Exception;

    StudentDto update(StudentDto studentDto) throws Exception;

    Optional<StudentDto> findById(Long id);

    Page<StudentDto> findByPage(Pageable pageable);
}
