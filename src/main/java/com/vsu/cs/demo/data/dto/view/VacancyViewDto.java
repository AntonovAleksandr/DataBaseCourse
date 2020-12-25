package com.vsu.cs.demo.data.dto.view;

import lombok.Data;

@Data
public class VacancyViewDto {
    private Long id;
    private Long employerId;
    private String title;
    private String company;
    private UserViewDto employer;
}
