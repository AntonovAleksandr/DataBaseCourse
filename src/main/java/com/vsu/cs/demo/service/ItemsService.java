package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.ItemsDto;

import java.util.List;

public interface ItemsService {
    List<ItemsDto> findAll();
    void save(ItemsDto dto);
    void deleteById(Long id);
}
