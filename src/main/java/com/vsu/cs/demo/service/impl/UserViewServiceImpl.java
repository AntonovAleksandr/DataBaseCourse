package com.vsu.cs.demo.service.impl;

import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.dto.UserDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.ValuesDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.entity.User;
import com.vsu.cs.demo.data.entity.Vacancy;
import com.vsu.cs.demo.data.entity.ValuesOfForm;
import com.vsu.cs.demo.data.mapper.ItemsMapper;
import com.vsu.cs.demo.data.mapper.UserMapper;
import com.vsu.cs.demo.data.mapper.VacancyMapper;
import com.vsu.cs.demo.data.mapper.ValuesMapper;
import com.vsu.cs.demo.data.repository.ItemsRepository;
import com.vsu.cs.demo.data.repository.UserRepository;
import com.vsu.cs.demo.data.repository.ValuesOfFormsRepository;
import com.vsu.cs.demo.service.UserViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class UserViewServiceImpl implements UserViewService {
    private final UserRepository userRepository;
    private final ItemsRepository itemsRepository;
    private final ValuesOfFormsRepository valuesOfFormsRepository;
    private final  Comparator<ValuesOfForm> comparator = Comparator.comparing(ValuesOfForm::getDate);

    public UserViewServiceImpl(UserRepository userRepository, ItemsRepository itemsRepository, ValuesOfFormsRepository valuesOfFormsRepository) {
        this.userRepository = userRepository;
        this.itemsRepository = itemsRepository;
        this.valuesOfFormsRepository = valuesOfFormsRepository;
    }

    @Override
    public List<UserViewDto> findAll() {
        List<UserViewDto> result = UserMapper.INSTANCE.userToView(userRepository.findAll());
        List<ItemsDto> itemsDtos = new ArrayList<>();
        itemsRepository.findAll().forEach(element -> itemsDtos.add(ItemsMapper.INSTANCE.itemToDto(element)));
        result.forEach(userViewDto -> userViewDto.setItems(itemsDtos));
        result.forEach(userViewDto -> {
            userViewDto.getItems().forEach(itemsDto -> {
                List<ValuesOfForm> values = valuesOfFormsRepository.findByItemIdAndPersoneIdOrderByDateDesc( itemsDto.getId(), userViewDto.getId());
                ValuesOfForm valuesOfForm = values.stream().max(comparator).orElse(null);
                ValuesDto valuesDto = ValuesMapper.INSTANCE.valueToDto(valuesOfForm);
                userViewDto.addValue(valuesDto);
                userViewDto.setDate(valuesDto.getDate());
            });
        });

        return result;
    }

    @Override
    public List<UserViewDto> findAllByValueAndItem(String strValue, String item) {
        List<ValuesOfForm> values =  valuesOfFormsRepository.findAllByValueOfItem(strValue);
        List<UserViewDto> result = new ArrayList<>();
        Long itemId = itemsRepository.findByTitle(item).getId();
        for (var value:values) {
            if(value.getItemId() == itemId) {
                result.add(findById(value.getPersoneId()));
                List<ItemsDto> itemsDtos = new ArrayList<>();
                itemsRepository.findAll().forEach(element -> itemsDtos.add(ItemsMapper.INSTANCE.itemToDto(element)));
                result.forEach(userViewDto -> userViewDto.setItems(itemsDtos));
                result.forEach(userViewDto -> {
                    userViewDto.getItems().forEach(itemsDto -> {
                        List<ValuesOfForm> localValues = valuesOfFormsRepository.findByItemIdAndPersoneIdOrderByDateDesc(itemsDto.getId(), userViewDto.getId());
                        ValuesOfForm valuesOfForm = localValues.stream().max(comparator).get();
                        ValuesDto valuesDto = ValuesMapper.INSTANCE.valueToDto(valuesOfForm);
                        userViewDto.addValue(valuesDto);
                        userViewDto.setDate(valuesDto.getDate());
                    });
                });
            }
        }
        return result;
    }

    @Override
    public UserViewDto findById(Long id) {

        UserViewDto userViewDto = UserMapper.INSTANCE.userToView(userRepository.findById(id).orElse(null));
        userViewDto.setItems(ItemsMapper.INSTANCE.itemToDto(itemsRepository.findAll()));
        userViewDto.getItems().forEach(item -> {
            List<ValuesOfForm> values = valuesOfFormsRepository.findByItemIdAndPersoneIdOrderByDateDesc(item.getId(), userViewDto.getId());
            ValuesOfForm value = values.stream().max(comparator).get();
            ValuesDto dto = ValuesMapper.INSTANCE.valueToDto(value);
            userViewDto.addValue(dto);
        });
        return userViewDto;
    }

    @Override
    public UserViewDto findByLogin(String login) {
        UserViewDto userViewDto = UserMapper.INSTANCE.userToView(userRepository.findByLogin(login));
        if(userViewDto!=null) {
            userViewDto.setItems(ItemsMapper.INSTANCE.itemToDto(itemsRepository.findAll()));
            userViewDto.getItems().forEach(item -> {
                List<ValuesOfForm> values = valuesOfFormsRepository.findByItemIdAndPersoneIdOrderByDateDesc(item.getId(), userViewDto.getId());
                ValuesOfForm value = values.stream().max(comparator).orElse(null);
                if(value!=null) {
                    ValuesDto dto = ValuesMapper.INSTANCE.valueToDto(value);
                    userViewDto.addValue(dto);
                    userViewDto.setDate(dto.getDate());
                }
            });
        }

        return userViewDto;
    }

    @Override
    public void save(UserViewDto userViewDto) {
        User user = UserMapper.INSTANCE.viewToUser(userViewDto);
        user.setPassword("12345678");
        userRepository.save(user);
        valuesOfFormsRepository.saveAll(ValuesMapper.INSTANCE.dtoToValue(userViewDto.getValues()));
    }

    @Override
    public void update(UserViewDto targetDto) {
        User user = userRepository.findById(targetDto.getId()).get();
        user.setLogin(targetDto.getLogin());
        userRepository.save(user);
    }

    @Override
    public void delete(UserViewDto user) {
        userRepository.deleteById(user.getId());
    }

}
