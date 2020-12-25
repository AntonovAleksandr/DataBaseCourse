package com.vsu.cs.demo.service;

import com.vsu.cs.demo.data.dto.SecurityUserDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto findByLogin(String login);

    UserDto findByID(Long id);

    UserDto findByUserName(String name);

    List<UserDto> findAll(String name);

    List<UserDto> findAll();

    void deleteById(Long id);

    Map<String, String> findInformationById(Long id);

    void update(UserDto targetDto);
    void save(SecurityUserDto user);

    void deleteById(UserDto user);

    boolean update(SecurityUserDto targetDto);
}
