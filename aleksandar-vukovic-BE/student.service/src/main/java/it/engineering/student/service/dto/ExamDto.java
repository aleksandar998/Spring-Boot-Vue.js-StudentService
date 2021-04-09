package it.engineering.student.service.dto;

import it.engineering.student.service.entity.Professor;
import it.engineering.student.service.entity.Subject;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ExamDto implements Serializable {
    private Long examId;
    private Subject subject;
    private Professor professor;
    private Date period;

    public ExamDto(Long examId, Subject subject, Professor professor, Date period) {
        this.examId = examId;
        this.subject = subject;
        this.professor = professor;
        this.period = period;
    }

    public ExamDto() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDto examDto = (ExamDto) o;
        return Objects.equals(examId, examDto.examId) && Objects.equals(subject, examDto.subject) && Objects.equals(professor, examDto.professor) && Objects.equals(period, examDto.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, subject, professor, period);
    }
}
