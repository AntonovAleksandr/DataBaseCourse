package com.vsu.cs.demo.data.mapper;


import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.entity.ItemsOfForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemsMapper {
    ItemsMapper INSTANCE = Mappers.getMapper(ItemsMapper.class);

    ItemsDto itemToDto(ItemsOfForm itemsOfForm);
    List<ItemsDto> itemToDto(Iterable<ItemsOfForm> itemsOfForms);

    ItemsOfForm dtoToItems(ItemsDto dto);
    List<ItemsOfForm> dtoToItems(Iterable<ItemsDto> dtos);
}
