package com.lambdaschool.bookservice.repository;

import com.lambdaschool.bookservice.models.Authors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorsRepository extends PagingAndSortingRepository<Authors, Long> {
}
