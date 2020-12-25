package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.entity.Vacancy;
import com.vsu.cs.demo.data.mapper.VacancyMapper;
import com.vsu.cs.demo.data.mapper.ValuesMapper;
import com.vsu.cs.demo.data.repository.VacancyRepository;
import com.vsu.cs.demo.service.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;

    public VacancyServiceImpl(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public List<VacancyDto> findAll() {
        List<VacancyDto> result = new ArrayList<>();
        vacancyRepository.findAll().forEach(element -> result.add(VacancyMapper.INSTANCE.vacancyToDto(element)));
        return result;
    }

    @Override
    public List<VacancyDto> findAllWhereEmployerIdIs(Long id) {
        List<VacancyDto> result = new ArrayList<>();
        vacancyRepository.findAllByEmployerId(id).forEach(element -> result.add(VacancyMapper.INSTANCE.vacancyToDto(element)));
        return result;
    }

    @Override
    public List<VacancyDto> findAllWhereTitleIs(String title) {
        List<VacancyDto> result = new ArrayList<>();
        vacancyRepository.findAllByTitle(title).forEach(element -> result.add(VacancyMapper.INSTANCE.vacancyToDto(element)));
        return result;
    }

    @Override
    public List<VacancyDto> findAllWhereCompanyIs(String company) {
        List<VacancyDto> result = new ArrayList<>();
        vacancyRepository.findAllByCompany(company).forEach(element -> result.add(VacancyMapper.INSTANCE.vacancyToDto(element)));
        return result;
    }

    @Override
    public VacancyDto findByTitleAndCompany(String title, String company) {
        return VacancyMapper.INSTANCE.vacancyToDto(vacancyRepository.findByTitleAndCompany(title, company));
    }

    @Override
    public VacancyDto findById(Long id) {
        return VacancyMapper.INSTANCE.vacancyToDto(vacancyRepository.findById(id).orElse(null));
    }

    @Override
    public void save(VacancyDto vacancy) {
        vacancyRepository.save(VacancyMapper.INSTANCE.dtoToVacancy(vacancy));
    }

    @Override
    public void update(VacancyDto targetDto) {
        Vacancy vacancy = vacancyRepository.findById(targetDto.getId()).get();
        vacancy.setCompany(targetDto.getCompany());
        vacancy.setEmployerId(targetDto.getEmployerId());
        vacancy.setTitle(targetDto.getTitle());
        vacancyRepository.save(vacancy);
    }

    @Override
    public void delete(VacancyDto vacancy) {
        vacancyRepository.deleteById(vacancy.getId());
    }

    @Override
    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }
}
