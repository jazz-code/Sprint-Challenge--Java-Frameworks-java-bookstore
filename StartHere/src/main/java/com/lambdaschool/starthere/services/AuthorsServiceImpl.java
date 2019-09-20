package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorsRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Authors> findAll()
    {
        List<Authors> list = new ArrayList<>();
        authorsrepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }


}
