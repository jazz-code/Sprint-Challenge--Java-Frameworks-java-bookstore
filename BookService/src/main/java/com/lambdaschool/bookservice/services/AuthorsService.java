package com.lambdaschool.bookservice.services;

import com.lambdaschool.bookservice.models.Authors;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface AuthorsService {
    List<Authors> findAll(Pageable pageable);

//    List<Authors> findAll();

}
