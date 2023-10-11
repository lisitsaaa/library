package com.example.library.wrapper;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.book.BookListDto;
import com.example.library.dto.book.UpdatedBookDto;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static com.example.library.mapper.BookMapper.INSTANCE;

@Component
@RequiredArgsConstructor
public class BookWrapper {
    private final BookService bookService;

    public BookDto save(BookDto dto, BindingResult bindingResult) {
        return INSTANCE.bookToDto(
                bookService.save(
                        INSTANCE.dtoToBook(dto), bindingResult));
    }

    public BookDto getByIsbn(String isbn) {
        return INSTANCE.bookToDto(
                bookService.findByISBN(isbn));
    }

    public BookDto getById(long id) {
        return INSTANCE.bookToDto(
                bookService.findById(id));
    }

    public BookDto updateBookStatus(BookStatus bookStatus, long id) {
        return INSTANCE.bookToDto(bookService.updateBookStatus(bookStatus, id));
    }

    public BookDto updateBookInfo(UpdatedBookDto updatedBookDto,
                                  long id,
                                  BindingResult bindingResult) {
        return INSTANCE.bookToDto(
                bookService.updateInfo(
                        INSTANCE.updatedDtoToBook(updatedBookDto), id, bindingResult));
    }

    public void remove(long id){
        bookService.remove(id);
    }

    public BookListDto getAllInside(){
        BookListDto bookListDto = new BookListDto();
        List<BookDto> booksDto = new ArrayList<>();

        bookService.findAllInside()
                .forEach(book ->
                        booksDto.add(
                                INSTANCE.bookToDto(book)));
        bookListDto.setBooksDto(booksDto);

        return bookListDto;
    }

    public BookListDto getAllWithPagination(PageRequest pageRequest){
        BookListDto bookListDto = new BookListDto();
        List<BookDto> booksDto = new ArrayList<>();

         bookService.findAllWithPagination(pageRequest)
                 .forEach(book ->
                         booksDto.add(
                                 INSTANCE.bookToDto(book)));
         bookListDto.setBooksDto(booksDto);

         return bookListDto;
    }
}
