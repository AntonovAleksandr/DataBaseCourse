package com.vsu.cs.demo.data.dto.view;

import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.dto.ValuesDto;
import com.vsu.cs.demo.data.entity.ItemsOfForm;
import com.vsu.cs.demo.data.entity.ValuesOfForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDto {
    private Long id;
    private String login;
    private List<ValuesDto> values = new ArrayList<>();
    private List<ItemsDto> items = new ArrayList<>();
    private LocalDate date;

    public void addValue(ValuesDto dto){
        values.add(dto);
    }
}
