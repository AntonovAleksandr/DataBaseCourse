package com.vsu.cs.demo.data.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryViewDto {
    private Long id;

    private String title;
    private String company;
    private UserViewDto employee;


    private Long employeeId;
    private Long desiredVacancyId;
    private LocalDate date;

    public SummaryViewDto(String company, UserViewDto employee, String title, LocalDate date) {
        this.company = company;
        this.title = title;
        this.date = date;
        this.employee = employee;
    }
}
