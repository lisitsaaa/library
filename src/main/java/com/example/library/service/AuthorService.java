package com.example.library.service;

import com.example.library.entity.library.book.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        Optional<Author> authorByDB = authorRepository
                .findByNameAndSurnameAndParentName(author.getName(),
                        author.getSurname(),
                        author.getParentName());
        return authorByDB.orElseGet(() -> authorRepository.save(author));
    }
}
