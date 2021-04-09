package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.CityDto;
import it.engineering.student.service.entity.City;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {
    CityDto toCityDto(City city);
    City toCity(CityDto cityDto);
}
