package com.vsu.cs.demo.data.mapper;

import com.vsu.cs.demo.data.dto.ValuesDto;
import com.vsu.cs.demo.data.entity.ValuesOfForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ValuesMapper {
    ValuesMapper INSTANCE = Mappers.getMapper(ValuesMapper.class);

    ValuesDto valueToDto(ValuesOfForm valuesOfForm);
    List<ValuesDto> valueToDto(Iterable<ValuesOfForm> valuesOfForm);

    ValuesOfForm dtoToValue(ValuesDto dto);
    List<ValuesOfForm> dtoToValue(Iterable<ValuesDto> dto);
}
