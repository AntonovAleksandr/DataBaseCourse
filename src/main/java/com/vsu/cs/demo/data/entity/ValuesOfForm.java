package com.vsu.cs.demo.data.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "values_of_form", schema = "public")
public class ValuesOfForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "person_id")
    private Long personeId;

    @CreatedDate
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "value_of_item")
    private String valueOfItem;
}