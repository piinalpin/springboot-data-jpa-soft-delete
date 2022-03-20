package com.piinalpin.customsoftdeletes.repository;

import com.piinalpin.customsoftdeletes.entity.Book;
import com.piinalpin.customsoftdeletes.repository.softdeletes.SoftDeletesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends SoftDeletesRepository<Book, Long> {
}
