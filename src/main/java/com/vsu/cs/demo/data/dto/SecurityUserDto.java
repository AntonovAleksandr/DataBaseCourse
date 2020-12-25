package com.vsu.cs.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUserDto {

    private Long id;
    private String login;
    private String password;

    public SecurityUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
