package it.engineering.student.service.service;

import it.engineering.student.service.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> findAll();
}
