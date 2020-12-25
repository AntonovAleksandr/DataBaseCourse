package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {

    private String title;
    private Long count;

    public StatisticDto(String title, BigInteger count) {
        this.title = title;
        this.count = count.longValue();
    }
}
