package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.dto.SecurityUserDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.entity.User;
import com.vsu.cs.demo.data.mapper.ItemsMapper;
import com.vsu.cs.demo.data.mapper.UserMapper;
import com.vsu.cs.demo.data.repository.ItemsRepository;
import com.vsu.cs.demo.data.repository.UserRepository;
import com.vsu.cs.demo.data.repository.ValuesOfFormsRepository;
import com.vsu.cs.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.*;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValuesOfFormsRepository valuesOfFormsRepository;
    private final ItemsRepository itemsRepository;


    public UserServiceImpl(UserRepository userRepository, ValuesOfFormsRepository valuesOfFormsRepository, ItemsRepository itemsRepository) {
        this.userRepository = userRepository;
        this.valuesOfFormsRepository = valuesOfFormsRepository;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public UserDto findByLogin(String login) {
        return null;
    }

    private void validateUserDto(UserDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    @Override
    public List<UserDto> findAll(String name) {
        var users = userRepository.findAll();
        var result = new ArrayList<UserDto>();
        users.forEach(user -> result.add(UserMapper.INSTANCE.userToDto(user)));
        return result;
    }

    @Override
    public UserDto findByID(Long id) {
        return UserMapper.INSTANCE.userToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto findByUserName(String name) {
        Long personId = valuesOfFormsRepository.findByValueOfItem(name).getPersoneId();
        return findByID(personId);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> result = new ArrayList<>();
        userRepository.findAll().forEach(user -> result.add(UserMapper.INSTANCE.userToDto(user)));
        return result;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Map<String, String> findInformationById(Long id) {
        List<ItemsDto> items = new ArrayList<>();
        Map<String, String> values = new HashMap<String, String>();
        itemsRepository.findAll().forEach(itemsOfForm -> items.add(ItemsMapper.INSTANCE.itemToDto(itemsOfForm)));
        items.forEach(itemsDto -> values.put(itemsDto.getTitle(), valuesOfFormsRepository.findByItemIdAndPersoneIdOrderByDateDesc(itemsDto.getId(), id).stream().findFirst().orElse(null).getValueOfItem()));
        return values;
    }

    @Override
    public void update(UserDto targetDto) {
        User user = userRepository.findById(targetDto.getId()).get();
        user.setLogin(targetDto.getLogin());
        userRepository.save(user);
    }

    @Override
    public void save(SecurityUserDto user) {
        userRepository.save(UserMapper.INSTANCE.sdtoToUser(user));
    }

    @Override
    public void deleteById(UserDto user) {
        userRepository.deleteById(user.getId());
    }

    @Override
    public boolean update(SecurityUserDto targetDto) {
        User user = userRepository.findById(targetDto.getId()).get();
        if (user.getPassword().equals(targetDto.getPassword())) {
            user.setLogin(targetDto.getLogin());
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
