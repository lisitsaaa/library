package com.example.library.controller;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.book.BookListDto;
import com.example.library.dto.book.UpdatedBookDto;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.wrapper.BookWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookWrapper bookWrapper;

    @PostMapping()
    public BookDto create(@RequestBody @Valid BookDto bookDto,
                                          BindingResult bindingResult){
        return bookWrapper.save(bookDto, bindingResult);
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable long id){
        return bookWrapper.getById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getByISBN(@PathVariable String isbn){
        return bookWrapper.getByIsbn(isbn);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id){
        bookWrapper.remove(id);
    }

    @PutMapping("/{id}")
    public BookDto updateInfo(@RequestBody @Valid UpdatedBookDto bookDto,
                              BindingResult bindingResult,
                              @PathVariable long id){
        return bookWrapper.updateBookInfo(bookDto, id, bindingResult);
    }

    @GetMapping("/inside")
    public BookListDto getInsideBook(){
        return bookWrapper.getAllInside();
    }

    @GetMapping()
    public BookListDto getAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size){
        return bookWrapper.getAllWithPagination(PageRequest.of(page, size));
    }

    @PutMapping("/status/{bookStatus}/{id}")
    public BookDto updateBookStatus(@PathVariable BookStatus bookStatus,
                                    @PathVariable long id){
        return bookWrapper.updateBookStatus(bookStatus, id);
    }
}
