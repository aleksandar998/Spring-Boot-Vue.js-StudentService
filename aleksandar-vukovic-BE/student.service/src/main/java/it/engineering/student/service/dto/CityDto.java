package it.engineering.student.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class CityDto implements Serializable {
    private Long cityId;
    private String cityName;

    public CityDto(Long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public CityDto() {
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDto cityDto = (CityDto) o;
        return Objects.equals(cityId, cityDto.cityId) && Objects.equals(cityName, cityDto.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName);
    }
}
