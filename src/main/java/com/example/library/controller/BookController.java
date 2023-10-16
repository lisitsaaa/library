package com.example.library.controller;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.book.BookListDto;
import com.example.library.dto.book.UpdatedBookDto;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.wrapper.BookWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Operations with books")
public class BookController {
    private final BookWrapper bookWrapper;

    @PostMapping()
    @Operation(summary = "Create a book")
    public BookDto create(@RequestBody @Valid BookDto bookDto,
                                          BindingResult bindingResult){
        return bookWrapper.save(bookDto, bindingResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public BookDto getById(@PathVariable long id){
        return bookWrapper.getById(id);
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get a book by ISBN (International Standard Book Number)")
    public BookDto getByISBN(@PathVariable String isbn){
        return bookWrapper.getByIsbn(isbn);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a book")
    public void remove(@PathVariable long id){
        bookWrapper.remove(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update information about book (ISBN, title, description, authors or genres)")
    public BookDto updateInfo(@RequestBody @Valid UpdatedBookDto bookDto,
                              BindingResult bindingResult,
                              @PathVariable long id){
        return bookWrapper.updateBookInfo(bookDto, id, bindingResult);
    }

    @GetMapping("/inside")
    @Operation(summary = "Get books, which book status - INSIDE")
    public BookListDto getInsideBook(){
        return bookWrapper.getAllInside();
    }

    @GetMapping()
    @Operation(summary = "Get all books with pagination")
    public BookListDto getAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size){
        return bookWrapper.getAllWithPagination(PageRequest.of(page, size));
    }

    @PutMapping("/status/{bookStatus}/{id}")
    @Operation(summary = "Update book status")
    public BookDto updateBookStatus(@PathVariable BookStatus bookStatus,
                                    @PathVariable long id){
        return bookWrapper.updateBookStatus(bookStatus, id);
    }
}
