package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.http.dto.TransactionRequest;
import com.piinalpin.customsoftdeletes.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTransactionDetail(@PathVariable(value = "id") Long transactionId) {
        return transactionService.getTransactionDetails(transactionId);
    }

}
