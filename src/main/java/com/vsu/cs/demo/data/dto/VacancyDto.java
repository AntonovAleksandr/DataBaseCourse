package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long id;
    private Long employerId;
    private String title;
    private String company;

    public VacancyDto(Long employerId, String title, String company) {
        this.employerId = employerId;
        this.title = title;
        this.company = company;
    }
}
