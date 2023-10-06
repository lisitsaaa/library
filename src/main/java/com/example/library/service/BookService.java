package com.example.library.service;

import com.example.library.entity.library.book.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void remove(long id) {
        bookRepository.delete(findById(id));
    }

    public Book updateInfo(Book book) {
        return save(book);
    }

    public void updateBookStatus(Book book) {
        bookRepository.updateBookStatus(book.getBookStatus(), book.getId());
    }

    @Transactional(readOnly = true)
    public Book findById(long id) {
        return checkForPresenceOfBook(bookRepository.findById(id));
    }

    @Transactional(readOnly = true)
    public Book findByISBN(String isbn) {
        return checkForPresenceOfBook(bookRepository.findByISBN(isbn));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new RuntimeException("");
        }
        return books;
    }

    private Book checkForPresenceOfBook(Optional<Book> book) {
        if (book.isPresent()) {
            return book.get();
        }
        throw new RuntimeException("not found");
    }
}
