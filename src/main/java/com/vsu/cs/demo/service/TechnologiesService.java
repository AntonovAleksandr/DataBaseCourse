package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.TechnologiesDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.entity.Technologies;

import java.util.List;

public interface TechnologiesService {
    List<TechnologiesDto> findAll();
    List<TechnologiesDto> findAllByEmployee(Long id);
    List<TechnologiesDto> findAllByTitle(String title);

    void save(TechnologiesDto technologiesDto);

    void delete(TechnologiesDto technologiesDto);

    void delete(TechnologiesViewDto technologiesDto);

    void update(Technologies targetDto);

    void update(TechnologiesViewDto targetDto);

    void deleteById(Long id);
}
