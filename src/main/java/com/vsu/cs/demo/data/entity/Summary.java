package com.vsu.cs.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "summary", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @LastModifiedDate
    @Column(name = "desired_vacancy_id")
    private Long desiredVacancyId;

    @CreatedDate
    @Column(name = "date")
    private LocalDate date;

    public Summary(Long employeeId, Long desiredVacancyId, LocalDate date) {
        this.employeeId = employeeId;
        this.desiredVacancyId = desiredVacancyId;
        this.date = date;
    }
}
