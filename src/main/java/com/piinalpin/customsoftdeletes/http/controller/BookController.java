package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.entity.Book;
import com.piinalpin.customsoftdeletes.http.request.BookRequest;
import com.piinalpin.customsoftdeletes.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import io.swagger.annotations.Example;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Add new book", response = Book.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success add new book."),
        @ApiResponse(code = 500, message = "Internal server error.")
    })
    @PostMapping(value = "")
    public ResponseEntity<Object> addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @ApiOperation(value = "Get list book", response = Book.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success get list book."),
        @ApiResponse(code = 500, message = "Internal server error.")
    })
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBook();
    }

    @ApiOperation(value = "Get book detail", response = Book.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success get detail of book."),
        @ApiResponse(code = 400, message = "Book not found.")
    })
    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<Object> getBookDetail(@PathVariable(value = "id") Long bookId) {
        return bookService.getBookDetail(bookId);
    }

    @ApiOperation(value = "Delete book")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success delete a book.", examples = @Example(
            value = {
                @ExampleProperty(value = "{'message': 'ok'}", mediaType = MediaType.APPLICATION_JSON_VALUE)
            }
        )),
        @ApiResponse(code = 400, message = "Book not found.")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @ApiOperation(value = "Update price book", response = Book.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success update price of book."),
        @ApiResponse(code = 400, message = "Book not found.")
    })
    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> updatePrice(@PathVariable(value = "id") Long bookId,
                                              @RequestBody BookRequest request) {
        return bookService.updatePrice(request, bookId);
    }

}
