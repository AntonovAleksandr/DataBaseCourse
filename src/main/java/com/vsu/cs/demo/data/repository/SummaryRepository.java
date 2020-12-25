package com.vsu.cs.demo.data.repository;


import com.vsu.cs.demo.data.entity.Summary;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SummaryRepository extends PagingAndSortingRepository<Summary, Long> {
    @Override
    Iterable<Summary> findAll();
    Summary findByEmployeeIdAndDesiredVacancyId(Long eId, Long vId);

    Iterable<Summary> findAllByDesiredVacancyId(Long id);
    Iterable<Summary> findAllByEmployeeId(Long id);



}
