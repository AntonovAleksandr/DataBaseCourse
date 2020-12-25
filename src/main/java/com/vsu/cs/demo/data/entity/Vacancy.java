package com.vsu.cs.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancy", schema = "public")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employer_id")
    private Long employerId;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    public Vacancy(Long employerId, String title, String company) {
        this.employerId = employerId;
        this.title = title;
        this.company = company;
    }
}
