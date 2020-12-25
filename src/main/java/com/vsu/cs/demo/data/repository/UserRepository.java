package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findById (Long id);
    User findByLogin(String name);
    void deleteById(Long id);

}