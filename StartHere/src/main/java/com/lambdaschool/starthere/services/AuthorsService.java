package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface AuthorsService {
//    ArrayList<Authors> findAll(Pageable pageable);

    List<Authors> findAll();

}
