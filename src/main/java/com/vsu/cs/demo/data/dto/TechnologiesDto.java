package com.vsu.cs.demo.data.dto;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class TechnologiesDto {
    private Long id;
    private Long personId;
    private String title;
    private LocalDate dateOfUpdates;

    public TechnologiesDto(Long personId, String title, LocalDate date) {
        this.personId = personId;
        this.title = title;
        this.dateOfUpdates = date;
    }
}
