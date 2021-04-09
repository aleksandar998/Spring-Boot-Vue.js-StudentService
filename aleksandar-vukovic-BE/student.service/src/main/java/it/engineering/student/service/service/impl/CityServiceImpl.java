package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.CityDto;
import it.engineering.student.service.entity.City;
import it.engineering.student.service.mapper.CityMapper;
import it.engineering.student.service.repository.CityRepository;
import it.engineering.student.service.service.CityService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> findAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(city -> {
            return cityMapper.toCityDto(city);
        }).collect(Collectors.toList());
    }
}
