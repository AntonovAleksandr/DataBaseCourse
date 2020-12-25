package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.entity.Vacancy;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends PagingAndSortingRepository<Vacancy, Long>
    {
        Optional<Vacancy> findById(Long id);
        List<Vacancy> findAll();
        List<Vacancy> findAllByTitle(String title);
        List<Vacancy> findAllByCompany(String company);
        List<Vacancy> findAllByEmployerId(Long id);
        Vacancy findByTitleAndCompany(String title, String company);

    }