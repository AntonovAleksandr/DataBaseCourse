package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.TechnologiesDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.entity.Technologies;
import com.vsu.cs.demo.data.entity.User;
import com.vsu.cs.demo.data.mapper.TechnologiesMapper;
import com.vsu.cs.demo.data.mapper.VacancyMapper;
import com.vsu.cs.demo.data.repository.TechnologiesRepository;
import com.vsu.cs.demo.service.TechnologiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TechnologiesServiceImpl implements TechnologiesService {
    private final TechnologiesRepository technologiesRepository;

    public TechnologiesServiceImpl(TechnologiesRepository technologiesRepository) {
        this.technologiesRepository = technologiesRepository;
    }

    @Override
    public List<TechnologiesDto> findAll() {
        List<TechnologiesDto> result = new ArrayList<>();
        technologiesRepository.findAll().forEach(element -> result.add(TechnologiesMapper.INSTANCE.technologiesToDto(element)));
        return result;
    }

    @Override
    public List<TechnologiesDto> findAllByEmployee(Long id) {
        List<TechnologiesDto> result = new ArrayList<>();
        technologiesRepository.findAllByPersonId(id).forEach(element -> result.add(TechnologiesMapper.INSTANCE.technologiesToDto(element)));
        return result;
    }

    @Override
    public List<TechnologiesDto> findAllByTitle(String title) {
        List<TechnologiesDto> result = new ArrayList<>();
        technologiesRepository.findAllByTitle(title).forEach(element -> result.add(TechnologiesMapper.INSTANCE.technologiesToDto(element)));
        return result;
    }

    @Override
    public void save(TechnologiesDto technologies) {
        technologiesRepository.save(TechnologiesMapper.INSTANCE.dtoToTechnologies(technologies));
    }

    @Override
    public void delete(TechnologiesDto technologiesDto) {
        technologiesRepository.deleteById(technologiesDto.getId());
    }

    @Override
    public void delete(TechnologiesViewDto technologiesDto) {
        technologiesRepository.deleteById(technologiesDto.getId());
    }

    @Override
    public void update(Technologies targetDto) {
        Technologies technologies = technologiesRepository.findById(targetDto.getId()).get();
        technologies.setDateOfUpdates(targetDto.getDateOfUpdates());
        technologies.setPersonId(targetDto.getPersonId());
        technologies.setTitle(targetDto.getTitle());
        technologiesRepository.save(technologies);
    }

    @Override
    public void update(TechnologiesViewDto targetDto) {
        Technologies technologies = technologiesRepository.findById(targetDto.getId()).get();
        technologies.setDateOfUpdates(targetDto.getDateOfUpdates());
        technologies.setPersonId(targetDto.getPersonId());
        technologies.setTitle(targetDto.getTitle());
        technologiesRepository.save(technologies);
    }

    @Override
    public void deleteById(Long id) {
        technologiesRepository.deleteById(id);
    }
}
