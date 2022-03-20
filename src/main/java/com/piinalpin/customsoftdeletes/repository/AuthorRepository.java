package com.piinalpin.customsoftdeletes.repository;

import com.piinalpin.customsoftdeletes.entity.Author;
import com.piinalpin.customsoftdeletes.repository.softdeletes.SoftDeletesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends SoftDeletesRepository<Author, Long> {
}
