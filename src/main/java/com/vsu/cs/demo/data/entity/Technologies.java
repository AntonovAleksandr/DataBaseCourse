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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technologies", schema = "public")
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "title")
    private String title;

    @CreatedDate
    @Column(name = "date_of_updates")
    private LocalDate dateOfUpdates;

    public Technologies(Long personId, String title, LocalDate dateOfUpdates) {
        this.personId = personId;
        this.title = title;
        this.dateOfUpdates = dateOfUpdates;
    }
}