package it.engineering.student.service.mapper;

import it.engineering.student.service.dto.TitleDto;
import it.engineering.student.service.entity.Title;
import org.mapstruct.Mapper;

@Mapper
public interface TitleMapper {
    TitleDto toTitleDto(Title title);
    Title toTitle(TitleDto titleDto);
}
