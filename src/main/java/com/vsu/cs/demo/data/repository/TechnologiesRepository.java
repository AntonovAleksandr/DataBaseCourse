package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.entity.Technologies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TechnologiesRepository extends PagingAndSortingRepository<Technologies, Long> {
    List<Technologies> findAllByTitle(String title);
    List<Technologies> findAllByPersonId(Long id);
}
