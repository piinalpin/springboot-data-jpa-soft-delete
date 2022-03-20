package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.http.request.BookRequest;
import com.piinalpin.customsoftdeletes.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<Object> getBookDetail(@PathVariable(value = "id") Long bookId) {
        return bookService.getBookDetail(bookId);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> updatePrice(@PathVariable(value = "id") Long bookId,
                                              @RequestBody BookRequest request) {
        return bookService.updatePrice(request, bookId);
    }

}
