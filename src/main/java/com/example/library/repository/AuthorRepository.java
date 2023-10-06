package com.example.library.repository;

import com.example.library.entity.library.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndSurnameAndParentName(String name,
                                                       String surname,
                                                       String parentName);
}
