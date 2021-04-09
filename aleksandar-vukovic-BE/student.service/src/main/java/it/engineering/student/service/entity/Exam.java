package it.engineering.student.service.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", unique = true)
    private Long examId;

    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "period", nullable = false)
    private Date period;

    public Exam(Long examId, Subject subject, Professor professor, Date period) {
        this.examId = examId;
        this.subject = subject;
        this.professor = professor;
        this.period = period;
    }

    public Exam() {
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", subject=" + subject +
                ", professor=" + professor +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(examId, exam.examId) && Objects.equals(subject, exam.subject) && Objects.equals(professor, exam.professor) && Objects.equals(period, exam.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, subject, professor, period);
    }
}
