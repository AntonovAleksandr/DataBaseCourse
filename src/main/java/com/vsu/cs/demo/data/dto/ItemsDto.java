package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDto {
    private Long id;
    private String title;

    public ItemsDto(String title) {
        this.title = title;
    }
}
