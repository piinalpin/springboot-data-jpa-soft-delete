package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.entity.Author;
import com.piinalpin.customsoftdeletes.http.dto.AuthorRequest;
import com.piinalpin.customsoftdeletes.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ApiOperation(value = "Add new author", response = Author.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success add new author."),
        @ApiResponse(code = 500, message = "Internal server error.")
    })
    @PostMapping(value = "")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorRequest request) {
        return authorService.save(request);
    }

    @ApiOperation(value = "Get list author", response = Author.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success get list author."),
        @ApiResponse(code = 500, message = "Internal server error.")
    })
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllAuthor() {
        return authorService.getAll();
    }

}
