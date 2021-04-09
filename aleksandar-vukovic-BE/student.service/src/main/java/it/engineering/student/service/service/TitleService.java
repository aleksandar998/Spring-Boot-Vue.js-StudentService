package it.engineering.student.service.service;

import it.engineering.student.service.dto.TitleDto;
import it.engineering.student.service.entity.Title;

import java.util.List;

public interface TitleService {
    List<TitleDto> findAll();
}
