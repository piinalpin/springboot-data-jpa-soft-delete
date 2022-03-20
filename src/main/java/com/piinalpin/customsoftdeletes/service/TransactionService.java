package com.piinalpin.customsoftdeletes.service;

import com.piinalpin.customsoftdeletes.entity.Book;
import com.piinalpin.customsoftdeletes.entity.Transaction;
import com.piinalpin.customsoftdeletes.entity.TransactionDetail;
import com.piinalpin.customsoftdeletes.http.request.TransactionDetailRequest;
import com.piinalpin.customsoftdeletes.http.request.TransactionRequest;
import com.piinalpin.customsoftdeletes.repository.BookRepository;
import com.piinalpin.customsoftdeletes.repository.TransactionDetailRepository;
import com.piinalpin.customsoftdeletes.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {

    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepository transactionDetailRepository;

    @Autowired
    public TransactionService(BookRepository bookRepository, TransactionRepository transactionRepository,
                              TransactionDetailRepository transactionDetailRepository) {
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    public ResponseEntity<Object> createTransaction(TransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .customerName(request.getCustomerName())
                .build();
        List<TransactionDetail> details = new ArrayList<>();
        for (TransactionDetailRequest detailRequest : request.getDetails()) {
            log.info("Find book by bookId");
            Optional<Book> book = bookRepository.findOne(detailRequest.getBookId());
            if (book.isPresent()) {
                Integer price = book.get().getPrice() * detailRequest.getQty();
                details.add(TransactionDetail.builder()
                        .transaction(transaction)
                        .book(book.get())
                        .price(price)
                        .qty(detailRequest.getQty())
                        .build());
            }
        }
        transaction.setTotalPrice(details.stream().mapToInt(TransactionDetail::getPrice).sum());
        transaction.setTotalQty(details.stream().mapToInt(TransactionDetail::getQty).sum());
        transaction.setTransactionDetails(details);
        transactionRepository.save(transaction);
        return ResponseEntity.ok().body(transaction);
    }

    public ResponseEntity<Object> getTransactionDetails(Long transactionId) {
        return ResponseEntity.ok().body(transactionDetailRepository.findAllByTransactionId(transactionId));
    }

}
