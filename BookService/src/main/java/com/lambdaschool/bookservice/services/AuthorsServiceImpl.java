package com.lambdaschool.bookservice.services;

import com.lambdaschool.bookservice.models.Authors;
import com.lambdaschool.bookservice.repository.AuthorsRepository;
import com.lambdaschool.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService{

    @Autowired
    BookRepository bookrepo;

    @Autowired
    AuthorsRepository authorsrepo;

    @Override
    public List<Authors> findAll(Pageable pageable)
    {
        List<Authors> list = new ArrayList<>();
        authorsrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }


}
