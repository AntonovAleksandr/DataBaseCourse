package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.entity.ValuesOfForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ValuesOfFormsRepository extends PagingAndSortingRepository<ValuesOfForm, Long> {
     ValuesOfForm findByValueOfItem(String value);
     Optional<ValuesOfForm> findByItemId(Long id);

     List<ValuesOfForm> findByPersoneId(Long personeId);

     List<ValuesOfForm> findByItemIdAndPersoneIdOrderByDateDesc(Long itemId, Long personId);
     List<ValuesOfForm> findAllByValueOfItem(String value);



}
