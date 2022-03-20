package com.piinalpin.customsoftdeletes.repository;

import com.piinalpin.customsoftdeletes.entity.BookDetail;
import com.piinalpin.customsoftdeletes.repository.softdeletes.SoftDeletesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends SoftDeletesRepository<BookDetail, Long> {
}
