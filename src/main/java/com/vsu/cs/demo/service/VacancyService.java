package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.entity.Vacancy;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> findAll();
    List<VacancyDto> findAllWhereEmployerIdIs(Long id);
    List<VacancyDto> findAllWhereTitleIs(String title);
    List<VacancyDto> findAllWhereCompanyIs(String company);

    VacancyDto findByTitleAndCompany(String title, String company);

    VacancyDto findById(Long id);

    void save(VacancyDto vacancy);

    void update(VacancyDto vacancy);

    void delete(VacancyDto vacancy);

    void deleteById(Long id);
}
