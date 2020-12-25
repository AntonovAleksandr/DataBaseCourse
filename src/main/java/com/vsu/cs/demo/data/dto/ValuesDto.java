package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValuesDto {
    private String valueOfItem;
    private Long itemId;
    private Long personeId;
    private LocalDate date;
}
