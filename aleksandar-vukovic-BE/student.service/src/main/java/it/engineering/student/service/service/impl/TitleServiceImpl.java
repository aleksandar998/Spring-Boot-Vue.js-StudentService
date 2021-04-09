package it.engineering.student.service.service.impl;

import it.engineering.student.service.dto.TitleDto;
import it.engineering.student.service.entity.Title;
import it.engineering.student.service.mapper.TitleMapper;
import it.engineering.student.service.repository.TitleRepository;
import it.engineering.student.service.service.TitleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {
    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public List<TitleDto> findAll() {
        List<Title> titles = titleRepository.findAll();
        return titles.stream().map(title -> {
            return titleMapper.toTitleDto(title);
        }).collect(Collectors.toList());
    }
}
