package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.mapper.ItemsMapper;
import com.vsu.cs.demo.data.mapper.UserMapper;
import com.vsu.cs.demo.data.repository.ItemsRepository;
import com.vsu.cs.demo.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public List<ItemsDto> findAll() {
        return ItemsMapper.INSTANCE.itemToDto(itemsRepository.findAll());
    }

    @Override
    public void save(ItemsDto dto) {
        itemsRepository.save(ItemsMapper.INSTANCE.dtoToItems(dto));
    }

    @Override
    public void deleteById(Long id) {
        itemsRepository.deleteById(id);
    }
}
