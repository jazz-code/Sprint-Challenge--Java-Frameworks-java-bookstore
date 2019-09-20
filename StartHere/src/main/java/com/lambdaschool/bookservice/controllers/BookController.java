package com.lambdaschool.bookservice.controllers;

import com.lambdaschool.bookservice.models.Authors;
import com.lambdaschool.bookservice.models.Book;
import com.lambdaschool.bookservice.services.AuthorsService;
import com.lambdaschool.bookservice.services.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long courseid)
    {
        bookService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

