package com.piinalpin.customsoftdeletes.repository;

import com.piinalpin.customsoftdeletes.entity.Transaction;
import com.piinalpin.customsoftdeletes.repository.softdeletes.SoftDeletesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends SoftDeletesRepository<Transaction, Long> {
}
