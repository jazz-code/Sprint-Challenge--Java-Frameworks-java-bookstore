package com.lambdaschool.bookservice.services;

import com.lambdaschool.bookservice.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookservice.models.Book;
import com.lambdaschool.bookservice.repository.AuthorsRepository;
import com.lambdaschool.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookrepo;

    @Autowired
    AuthorsRepository authorsrepo;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;

    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found!"));
    }

//    @Override
//    public Book findByName(String name)
//    {
//        Book rr = bookrepo.findByNameIgnoreCase(name);
//
//        if (rr != null)
//        {
//            return rr;
//        } else
//        {
//            throw new ResourceNotFoundException(name);
//        }
//    }

    @Transactional
    @Override
    public void delete(long id)
    {
        bookrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
        bookrepo.deleteById(id);
    }

    @Transactional
    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (book.getBooktitle() != null) {
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getCopy() != 0) {
            currentBook.setCopy(book.getCopy());
        }
        if (book.getISBN() != null) {
            currentBook.setISBN(book.getISBN());
        }
        return bookrepo.save(currentBook);
    }

    @Transactional
    @Override
    public Book save(long bookid, long authorid) {
//        Book newBook = new Book();
//
//        newBook.(book.getStudname());
//
//        return bookrepo.save(newBook);

       return bookrepo.addBookAuths(bookid, authorid);
    }
}
