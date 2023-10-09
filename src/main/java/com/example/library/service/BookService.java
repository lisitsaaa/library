package com.example.library.service;

import com.example.library.entity.library.book.Book;
import com.example.library.exception.ExistsException;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Value("Not found")
    private String NOT_FOUND_MESSAGE;
    @Value("Book with ISBN - %s already existed")
    private String EXISTED_MESSAGE;

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        Optional<Book> byISBN = bookRepository.findByISBN(book.getISBN());
        if (byISBN.isPresent()) {
            throw new ExistsException(String.format(EXISTED_MESSAGE, book.getISBN()));
        }
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
    public List<Book> findInsideBooks(){
        return checkForPresenceOfBooks(bookRepository.findAllByBookStatusInside());
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
        return checkForPresenceOfBooks(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Book> findAllWithPagination(PageRequest pageRequest) {
        return checkForPresenceOfBooks(bookRepository.findAll(pageRequest).getContent());
    }

    private List<Book> checkForPresenceOfBooks(List<Book> books){
        if (CollectionUtils.isEmpty(books)) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        return books;
    }

    private Book checkForPresenceOfBook(Optional<Book> book) {
        if (book.isPresent()) {
            return book.get();
        }
        throw new NotFoundException(NOT_FOUND_MESSAGE);
    }
}
