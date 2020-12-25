package com.vsu.cs.demo.data.mapper;

import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.view.VacancyViewDto;
import com.vsu.cs.demo.data.entity.User;
import com.vsu.cs.demo.data.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    VacancyMapper INSTANCE = Mappers.getMapper(VacancyMapper.class);

    VacancyDto vacancyToDto(Vacancy  vacancy);
    VacancyViewDto vacancyToView(Vacancy  vacancy);
    List<VacancyDto> vacancyToDto(Iterable<Vacancy> vacancy);
    List<VacancyViewDto> vacancyToView(Iterable<Vacancy> vacancy);

    Vacancy dtoToVacancy(VacancyDto dto);
    Vacancy viewToVacancy(VacancyViewDto dto);
    List<Vacancy> dtoToVacancy(Iterable<VacancyDto> dto);
    List<Vacancy> viewToVacancy(Iterable<VacancyViewDto> dto);

    VacancyDto viewToDto(VacancyViewDto  vacancy);
    VacancyViewDto dtoToView(VacancyDto  vacancy);
    List<VacancyDto> viewToDto(Iterable<VacancyViewDto> vacancy);
    List<VacancyViewDto> dtoToView(Iterable<VacancyDto> vacancy);
}
