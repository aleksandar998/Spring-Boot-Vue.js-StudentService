package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.StudentDto;
import it.engineering.student.service.entity.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentDto toStudentDto(Student student);
    Student toStudent(StudentDto studentDto);
}
