package com.piinalpin.customsoftdeletes.service;

import com.piinalpin.customsoftdeletes.constant.AppConstant;
import com.piinalpin.customsoftdeletes.entity.Author;
import com.piinalpin.customsoftdeletes.http.dto.AuthorRequest;
import com.piinalpin.customsoftdeletes.repository.AuthorRepository;
import com.piinalpin.customsoftdeletes.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public ResponseEntity<Object> save(AuthorRequest request) {
        log.info("Save new author: {}", request);
        Author author = Author.builder()
                .fullName(request.getFullName())
                .build();
        try {
            author = authorRepository.save(author);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, author, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all author");
        return ResponseEntity.ok().body(authorRepository.findAll());
    }

}
