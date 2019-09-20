package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.repository.CrudRepository;

public interface AuthorsRepository extends CrudRepository<Authors, Long> {
}
