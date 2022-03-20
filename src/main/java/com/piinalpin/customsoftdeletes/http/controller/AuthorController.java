package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.http.request.AuthorRequest;
import com.piinalpin.customsoftdeletes.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorRequest request) {
        return authorService.save(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllAuthor() {
        return authorService.getAll();
    }

}
