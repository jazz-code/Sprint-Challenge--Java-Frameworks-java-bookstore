package com.lambdaschool.bookservice.repository;

import com.lambdaschool.bookservice.models.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO booksauths(bookid, authorid) values (:bookid, :authorid)", nativeQuery = true)
    Book addBookAuths(long bookid, long authorid);
}
