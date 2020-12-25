package com.vsu.cs.demo.data.dto.view;

import com.vsu.cs.demo.data.dto.TechnologiesDto;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class TechnologiesViewDto {
    private Long id;
    private UserViewDto person;
    private String title;
    private LocalDate dateOfUpdates;
    private Long personId;
}
