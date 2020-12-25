package com.vsu.cs.demo.data.mapper;

import com.vsu.cs.demo.data.dto.TechnologiesDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.entity.Technologies;
import com.vsu.cs.demo.data.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TechnologiesMapper {
    TechnologiesMapper INSTANCE = Mappers.getMapper(TechnologiesMapper.class);

    TechnologiesDto technologiesToDto(Technologies technologies);
    List<TechnologiesDto> technologiesToDto(List<Technologies> technologies);

    Technologies dtoToTechnologies(TechnologiesDto dto);
    List<Technologies> dtoToTechnologies(List<TechnologiesDto> dto);

    TechnologiesDto viewToDto(TechnologiesViewDto technologies);
    List<TechnologiesDto> viewToDto(Iterable<TechnologiesViewDto> technologies);

    TechnologiesViewDto dtoToView(TechnologiesDto view);
    List<TechnologiesViewDto> dtoToView(Iterable<TechnologiesDto> view);

    TechnologiesViewDto technologiesToView(Technologies technologies);
    List<TechnologiesViewDto> technologiesToView(List<TechnologiesDto> technologies);

    Technologies viewToTechnologies(TechnologiesViewDto view);
    List<Technologies> viewToTechnologies(Iterable<TechnologiesViewDto> views);



}
