package it.engineering.student.service.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exam_registration")
public class ExamRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public ExamRegistration(Long id, Student student, Exam exam) {
        this.id = id;
        this.student = student;
        this.exam = exam;
    }

    public ExamRegistration() {
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

    @Override
    public String toString() {
        return "ExamRegistration{" +
                "id=" + id +
                ", student=" + student +
                ", exam=" + exam +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamRegistration that = (ExamRegistration) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, exam);
    }
}
