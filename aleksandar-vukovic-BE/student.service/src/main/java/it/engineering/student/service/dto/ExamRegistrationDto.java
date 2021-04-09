package it.engineering.student.service.dto;

import it.engineering.student.service.entity.Exam;
import it.engineering.student.service.entity.Student;

import java.io.Serializable;
import java.util.Date;

public class ExamRegistrationDto implements Serializable {
    private Long id;
    private Student student;
    private Exam exam;

    public ExamRegistrationDto(Long id, Student student, Exam exam) {
        this.id = id;
        this.student = student;
        this.exam = exam;
    }

    public ExamRegistrationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
