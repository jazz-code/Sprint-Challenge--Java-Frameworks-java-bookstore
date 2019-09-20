package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorsRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookrepo;

    @Autowired
    AuthorsRepository authorsrepo;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found!"));
    }

    @Override
    public Book findByName(String name)
    {
        Book rr = bookrepo.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new ResourceNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        bookrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
        bookrepo.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        return null;
    }


//    @Transactional
//    @Override
//    public Book save(Book book)
//    {
//        Book newBook = new Book();
//        newBook.setName(Book.getName());
//
//        ArrayList<UserRoles> newUsers = new ArrayList<>();
//        for (UserRoles ur : role.getUserroles())
//        {
//            long id = ur.getUser()
//                    .getUserid();
//            User user = userrepos.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
//            newUsers.add(new UserRoles(ur.getUser(), newRole));
//        }
//        newRole.setUserroles(newUsers);
//
//        return rolerepos.save(role);
//    }
}
