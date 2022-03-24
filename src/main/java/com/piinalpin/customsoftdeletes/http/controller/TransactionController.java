package com.piinalpin.customsoftdeletes.http.controller;

import com.piinalpin.customsoftdeletes.entity.Transaction;
import com.piinalpin.customsoftdeletes.http.dto.TransactionRequest;
import com.piinalpin.customsoftdeletes.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Add transaction", response = Transaction.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success add new transaction."),
        @ApiResponse(code = 500, message = "Internal server error.")
    })
    @PostMapping(value = "")
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @ApiOperation(value = "Get list transaction detail by id", response = Transaction.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success get transaction."),
        @ApiResponse(code = 400, message = "Internal server error.")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTransactionDetail(@PathVariable(value = "id") Long transactionId) {
        return transactionService.getTransactionDetails(transactionId);
    }

}
