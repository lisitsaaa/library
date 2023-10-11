package com.example.library.service;

import com.example.library.entity.library.book.Author;
import com.example.library.exception.ExistsException;
import com.example.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    @Value("${EXISTED}") private String existed_message;

    private static final String s = "%s %s %s";

    private final AuthorRepository authorRepository;

    public Author save(Author author) {
        String fullName = String.format(s, author.getName(), author.getSurname(), author.getParentName());

        Optional<Author> authorByDB = authorRepository
                .findByNameAndSurnameAndParentName(author.getName(),
                        author.getSurname(),
                        author.getParentName());
        if (authorByDB.isPresent()) {
            throw new ExistsException(String.format(existed_message, fullName));
        }
        return authorRepository.save(author);
    }
}
