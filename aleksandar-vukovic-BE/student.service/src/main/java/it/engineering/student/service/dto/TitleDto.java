package it.engineering.student.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class TitleDto implements Serializable {
    private Long titleId;
    private String titleName;

    public TitleDto(Long titleId, String titleName) {
        this.titleId = titleId;
        this.titleName = titleName;
    }

    public TitleDto() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleDto titleDto = (TitleDto) o;
        return Objects.equals(titleId, titleDto.titleId) && Objects.equals(titleName, titleDto.titleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, titleName);
    }
}
