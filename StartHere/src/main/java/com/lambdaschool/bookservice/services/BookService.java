package com.lambdaschool.bookservice.services;

import com.lambdaschool.bookservice.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pageable);

    Book findBookById(long id);

    void delete(long id);

    Book save(Book book);

}
