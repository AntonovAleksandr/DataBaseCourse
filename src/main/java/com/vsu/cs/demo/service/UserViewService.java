package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;

import java.util.List;

public interface UserViewService {
    List<UserViewDto> findAll();

    List<UserViewDto> findAllByValueAndItem(String strValue, String item);

    UserViewDto findById(Long id);
    UserViewDto findByLogin(String login);

    void save(UserViewDto dto);

    void update(UserViewDto targetDto);

    void delete(UserViewDto user);
}
