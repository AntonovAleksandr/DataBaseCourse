package com.vsu.cs.demo.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items_of_form", schema = "public")
public class ItemsOfForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;


}
