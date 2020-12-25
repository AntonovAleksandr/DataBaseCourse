package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.SummaryDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;
import com.vsu.cs.demo.data.entity.Summary;
import com.vsu.cs.demo.data.mapper.SummaryMapper;
import com.vsu.cs.demo.data.repository.SummaryRepository;
import com.vsu.cs.demo.data.repository.VacancyRepository;
import com.vsu.cs.demo.service.SummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SummaryServiceImpl implements SummaryService {
    private final SummaryRepository summaryRepository;
    private final VacancyRepository vacancyRepository;


    public SummaryServiceImpl(SummaryRepository summaryRepository, VacancyRepository vacancyRepository) {
        this.summaryRepository = summaryRepository;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public List<SummaryDto> findAll() {
        return SummaryMapper.INSTANCE.summaryToDto(summaryRepository.findAll());

    }

    @Override
    public List<SummaryDto> findAllByEmployee(Long id) {
        return SummaryMapper.INSTANCE.summaryToDto(summaryRepository.findAllByEmployeeId(id));
    }

    @Override
    public List<SummaryDto> findAllByCompany(Long id) {
        return SummaryMapper.INSTANCE.summaryToDto(summaryRepository.findAllByDesiredVacancyId(id));
    }


    @Override
    public void save(SummaryViewDto summaryViewDto, VacancyDto vacancyDto) {
        summaryRepository.save(new Summary(summaryViewDto.getEmployee().getId(), vacancyDto.getId() ,summaryViewDto.getDate()));
    }

    @Override
    public void delete(SummaryDto summary) {
        summaryRepository.deleteById(summary.getId());
    }

    @Override
    public void update(SummaryDto targetDto) {
        Summary summary = summaryRepository.findById(targetDto.getId()).get();
        summary.setDate(targetDto.getDate());
        summary.setEmployeeId(targetDto.getEmployeeId());
        summary.setDesiredVacancyId(targetDto.getDesiredVacancyId());
        summaryRepository.save(summary);
    }

    @Override
    public void update(SummaryViewDto targetDto) {
        Summary summary = summaryRepository.findById(targetDto.getId()).get();
        summary.setDate(targetDto.getDate());
        summary.setEmployeeId(targetDto.getEmployee().getId());
        summary.setDesiredVacancyId(targetDto.getDesiredVacancyId());
        summaryRepository.save(summary);
    }

    @Override
    public void deleteById(Long id) {
        summaryRepository.deleteById(id);
    }

    @Override
    public void findByVacancy(String target) {

    }
}
