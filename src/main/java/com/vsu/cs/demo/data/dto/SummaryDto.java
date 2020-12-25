package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SummaryDto {
    private Long id;
    private Long employeeId;
    private Long desiredVacancyId;
    private LocalDate date;

    public SummaryDto(Long employeeId, Long desiredVacancyId, LocalDate date) {
        this.employeeId = employeeId;
        this.desiredVacancyId = desiredVacancyId;
        this.date = date;
    }
}
