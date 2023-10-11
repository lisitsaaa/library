package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookStatusDto;
import com.example.library.dto.IsbnDto;
import com.example.library.dto.UpdatedBookDto;
import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.example.library.controller.util.Validator.checkBindingResult;
import static com.example.library.mapper.BookMapper.INSTANCE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @PostMapping("/admin")
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto bookDto,
                                          BindingResult bindingResult){
        checkBindingResult(bindingResult);
        bookDto.setAuthors(getSavedAuthors(bookDto.getAuthors()));
        return ok(INSTANCE.bookToDto(bookService.save(INSTANCE.dtoToBook(bookDto))));
    }

    private List<Author> getSavedAuthors(List<Author> authors){
        List<Author> savedAuthors = new ArrayList<>();
        for (Author author : authors){
            Author savedAuthor = authorService.save(author);
            savedAuthors.add(savedAuthor);
        }
        return savedAuthors;
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAll(){
        List<BookDto> books = new ArrayList<>();
        bookService.findAll().forEach(book -> books.add(INSTANCE.bookToDto(book)));
        return ok(books);
    }

    @GetMapping("/inside")
    public ResponseEntity<List<BookDto>> getInsideBook(){
        List<BookDto> books = new ArrayList<>();
        bookService.findInsideBooks().forEach(book -> books.add(INSTANCE.bookToDto(book)));
        return ok(books);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<BookDto>> getAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size){
        List<BookDto> books = new ArrayList<>();
        bookService.findAllWithPagination(PageRequest.of(page, size)).forEach(book -> books.add(INSTANCE.bookToDto(book)));
        return ok(books);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<BookDto> updateInfo(@RequestBody @Valid UpdatedBookDto bookDto,
                                          BindingResult bindingResult,
                                          @PathVariable long id){
        checkBindingResult(bindingResult);
        return ok(INSTANCE.bookToDto(bookService.updateInfo(checkUpdatedBook(bookDto, id))));
    }

    private Book checkUpdatedBook(UpdatedBookDto dto, long id){
        Book oldBook = bookService.findById(id);

        if (!String.valueOf(dto.getTitle()).isEmpty()) oldBook.setTitle(dto.getTitle());
        if (!String.valueOf(dto.getDescription()).isEmpty()) oldBook.setDescription(dto.getDescription());
        if (!CollectionUtils.isEmpty(dto.getGenres())) oldBook.setGenres(dto.getGenres());
        if (!CollectionUtils.isEmpty(dto.getAuthors())) oldBook.setAuthors(getSavedAuthors(dto.getAuthors()));
        return oldBook;
    }

    @PutMapping("/admin/update-book-status/{id}")
    public ResponseEntity<BookDto> updateBookStatus(@RequestBody @Valid BookStatusDto bookStatusDto,
                                                    BindingResult bindingResult,
                                                    @PathVariable long id){
        checkBindingResult(bindingResult);
        Book oldBook = bookService.findById(id);
        oldBook.setBookStatus(bookStatusDto.getBookStatus());
        bookService.updateBookStatus(oldBook);
        return ok(INSTANCE.bookToDto(oldBook));
    }

    @DeleteMapping("/admin/{id}")
    public void remove(@PathVariable long id){
        bookService.remove(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable long id){
        return ok(INSTANCE.bookToDto(bookService.findById(id)));
    }

    @PostMapping("/find-by-isbn")
    public ResponseEntity<BookDto> getByISBN(@RequestBody IsbnDto dto){
        return ok(INSTANCE.bookToDto(bookService.findByISBN(dto.getISBN())));
    }
}
