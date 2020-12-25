package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.SummaryDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;


import java.util.List;

public interface SummaryService{
    List<SummaryDto> findAll();
    List<SummaryDto> findAllByEmployee(Long id);
    List<SummaryDto> findAllByCompany(Long id);

    void save(SummaryViewDto summaryViewDto, VacancyDto vacancyDto);

    void delete(SummaryDto summary);

    void update(SummaryDto targetDto);

    void update(SummaryViewDto targetDto);

    void deleteById(Long id);

    void findByVacancy(String target);
}
