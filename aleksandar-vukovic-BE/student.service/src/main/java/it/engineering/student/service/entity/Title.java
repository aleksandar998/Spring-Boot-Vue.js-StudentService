package it.engineering.student.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "title")
public class Title implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id", nullable = false, unique = true)
    private Long titleId;

    @Column(name = "title_name", nullable = false, unique = true)
    private String titleName;

    public Title(Long titleId, String titleName) {
        this.titleId = titleId;
        this.titleName = titleName;
    }

    public Title() {
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return Objects.equals(titleId, title.titleId) && Objects.equals(titleName, title.titleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, titleName);
    }
}
