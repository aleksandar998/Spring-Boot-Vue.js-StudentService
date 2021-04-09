package it.engineering.student.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SubjectDto implements Serializable {
    private Long subjectId;
    private String name;
    private String description;
    private Long esp;
    private Long yearOfStudy;
    private String semester;

    public SubjectDto(Long subjectId, String name, String description, Long esp, Long yearOfStudy, String semester) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.esp = esp;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }

    public SubjectDto() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return Objects.equals(subjectId, that.subjectId)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(esp, that.esp)
                && Objects.equals(yearOfStudy, that.yearOfStudy)
                && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, name, description, esp, yearOfStudy, semester);
    }
}
