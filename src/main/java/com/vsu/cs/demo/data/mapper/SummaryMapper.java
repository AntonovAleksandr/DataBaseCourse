package com.vsu.cs.demo.data.mapper;

import com.vsu.cs.demo.data.dto.SummaryDto;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;
import com.vsu.cs.demo.data.entity.Summary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SummaryMapper {
    SummaryMapper INSTANCE = Mappers.getMapper(SummaryMapper.class);

    SummaryDto summaryToDto(Summary summary);
    List<SummaryDto> summaryToDto(Iterable<Summary> summary);

    Summary dtoToSummary(SummaryDto dto);
    List<Summary> dtoToSummary(Iterable<SummaryDto> dto);

    SummaryViewDto summaryToView(Summary summary);
    List<SummaryViewDto> summaryToView(Iterable<Summary> summary);

    SummaryViewDto dtoToView(SummaryDto dto);
    List<SummaryViewDto> dtoToView(Iterable<SummaryDto> dto);


    SummaryDto viewToDto(SummaryViewDto dto);
    List<SummaryDto> viewToDto(Iterable<SummaryViewDto> dto);
}
