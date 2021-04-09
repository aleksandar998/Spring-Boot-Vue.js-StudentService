package it.engineering.student.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", unique = true)
    private Long subjectId;

    @Column(name = "subject_name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "esp", nullable = false, length = 1)
    private Long esp;

    @Column(name = "year_of_study", length = 1)
    private Long yearOfStudy;

    @Column(name = "semester", length = 10)
    private String semester;

    public Subject(Long subjectId, String name, String description, Long esp, Long yearOfStudy, String semester) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.esp = esp;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }

    public Subject() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEsp() {
        return esp;
    }

    public void setEsp(Long esp) {
        this.esp = esp;
    }

    public Long getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Long yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", esp=" + esp +
                ", yearOfStudy=" + yearOfStudy +
                ", semester='" + semester + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId)
                && Objects.equals(name, subject.name)
                && Objects.equals(description, subject.description)
                && Objects.equals(esp, subject.esp)
                && Objects.equals(yearOfStudy, subject.yearOfStudy)
                && Objects.equals(semester, subject.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, name, description, esp, yearOfStudy, semester);
    }
}
