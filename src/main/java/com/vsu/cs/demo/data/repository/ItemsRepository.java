package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.entity.ItemsOfForm;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends PagingAndSortingRepository<ItemsOfForm, Long> {
    Optional<ItemsOfForm> findById(Long id);
    ItemsOfForm findByTitle(String title);
    List<ItemsOfForm> findAll();
}
