package com.example.library.service;

import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.Book;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.exception.ExistsException;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.library.controller.util.Validator.checkBindingResult;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    @Value("${NOT_FOUND}")
    private String not_found_message;
    @Value("${EXISTED}")
    private String existed_message;

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public Book save(Book book, BindingResult bindingResult) {
        checkBindingResult(bindingResult);

        Optional<Book> byISBN = bookRepository.findByISBN(book.getISBN());
        if (byISBN.isPresent()) {
            throw new ExistsException(String.format(existed_message, book.getISBN()));
        }

        book.getAuthors().forEach(authorService::save);
        return bookRepository.save(book);
    }

    public void remove(long id) {
        bookRepository.delete(findById(id));
    }

    public Book updateInfo(Book book, long id, BindingResult bindingResult) {
        return save(checkUpdatedBook(book, id), bindingResult);
    }

    private Book checkUpdatedBook(Book book, long id) {
        Book oldBook = findById(id);
        List<Author> authors = new ArrayList<>();
        book.getAuthors().forEach(author -> authors.add(authorService.save(author)));

        if (!CollectionUtils.isEmpty(book.getAuthors())) oldBook.setAuthors(authors);
        if (!String.valueOf(book.getTitle()).isEmpty()) oldBook.setTitle(book.getTitle());
        if (!String.valueOf(book.getDescription()).isEmpty()) oldBook.setDescription(book.getDescription());
        if (!CollectionUtils.isEmpty(book.getGenres())) oldBook.setGenres(book.getGenres());

        return oldBook;
    }

    public Book updateBookStatus(BookStatus bookStatus, long id) {
        bookRepository.updateBookStatus(bookStatus, id);
        return findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findAllInside() {
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
    public List<Book> findAllWithPagination(PageRequest pageRequest) {
        return checkForPresenceOfBooks(bookRepository.findAll(pageRequest).getContent());
    }

    private List<Book> checkForPresenceOfBooks(List<Book> books) {
        if (CollectionUtils.isEmpty(books)) {
            throw new NotFoundException(not_found_message);
        }
        return books;
    }

    private Book checkForPresenceOfBook(Optional<Book> book) {
        if (book.isPresent()) {
            return book.get();
        }
        throw new NotFoundException(not_found_message);
    }
}
