package com.piinalpin.customsoftdeletes.repository;

import com.piinalpin.customsoftdeletes.entity.TransactionDetail;
import com.piinalpin.customsoftdeletes.repository.softdeletes.SoftDeletesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends SoftDeletesRepository<TransactionDetail, Long> {

    List<TransactionDetail> findAllByTransactionId(Long transactionId);

}
