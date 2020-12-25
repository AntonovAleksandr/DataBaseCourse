package com.vsu.cs.demo.data.mapper;

import com.vsu.cs.demo.data.dto.SecurityUserDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.entity.ItemsOfForm;
import com.vsu.cs.demo.data.entity.User;
import com.vsu.cs.demo.data.entity.ValuesOfForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToDto(User user);
    List<UserDto> userToDto(Iterable<User> user);

    User dtoToUser(UserDto dto);
    List<User> dtoToUser(Iterable<UserDto> dto);

    UserViewDto dtoToView(UserDto user);
    List<UserViewDto> dtoToView(Iterable<UserDto> user);
    UserViewDto userToView(User user);
    List<UserViewDto> userToView(Iterable<User> user);

    User viewToUser(UserViewDto dto);
    List<User> viewToUser(Iterable<UserViewDto> dto);


    UserDto viewToDto(UserViewDto dto);
    List<UserDto> viewToDto(Iterable<UserViewDto> dto);


    SecurityUserDto userToSDto(User user);
    List<SecurityUserDto> userToSDto(Iterable<User> user);

    User sdtoToUser(SecurityUserDto dto);
    List<User> sdtoToUser(Iterable<SecurityUserDto> dto);
}
