package com.lambdaschool.bookservice.controllers;

import com.lambdaschool.bookservice.models.Authors;
import com.lambdaschool.bookservice.models.Book;
import com.lambdaschool.bookservice.models.ErrorDetail;
import com.lambdaschool.bookservice.services.AuthorsService;
import com.lambdaschool.bookservice.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
//@RequestMapping(value = "/data")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorsService authorsService;

    @ApiOperation(value = "return all Courses using Paging and Sorting", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 3) Pageable pageable)
    {
        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

//    @GetMapping(value = "/allcourses",
//            produces = {"application/json"})
//    public ResponseEntity<?> reallyListAllCourses() {
//        List<Book> myBooks = bookService.findAll(Pageable.unpaged());
//        return new ResponseEntity<>(myBooks, HttpStatus.OK);
//    }

    @ApiOperation(value = "Return all Authors and books using paging and sorting", response = Authors.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAuthors(@PageableDefault(page = 0,
            size = 5)
                                                 Pageable pageable)
    {
        List<Authors> allAuthors = authorsService.findAll(pageable);
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a Book",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Was Updated", response = void.class),
            @ApiResponse(code = 500, message = "Error Updating The Book", response = ErrorDetail.class)
    } )
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody
                                                Book updateBook,
                                        @PathVariable
                                                long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Assigns a book to author",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New Book was added to Author", response = void.class),
            @ApiResponse(code = 500, message = "Error adding your book", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/data/{bookid}/authors/{authorid}")
    public ResponseEntity<?> addBookAuthors(@PathVariable long bookid, @PathVariable long authorid)
    {
        bookService.save(bookid, authorid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a Book",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Was Deleted", response = void.class),
            @ApiResponse(code = 500, message = "Error Deleting Your Book", response = ErrorDetail.class)
    } )
    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

