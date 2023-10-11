package com.example.library.service;

import com.example.library.entity.library.book.Author;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author save(Author author) {
        Optional<Author> authorByDB = authorRepository
                .findByNameAndSurnameAndParentName(author.getName(),
                        author.getSurname(),
                        author.getParentName());
        if (authorByDB.isEmpty()) {
            return authorRepository.save(author);
        }
        return authorByDB.get();
    }
}
