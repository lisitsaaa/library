package com.example.library.controller;

import com.example.library.dto.JournalDto;
import com.example.library.dto.NoteDto;
import com.example.library.entity.library.Journal;
import com.example.library.entity.library.book.Book;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.entity.user.User;
import com.example.library.service.BookService;
import com.example.library.service.JournalService;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.library.controller.util.Validator.checkBindingResult;
import static com.example.library.mapper.JournalMapper.INSTANCE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/journal/admin")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;
    private final UserService userService;
    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<JournalDto> createNote(@RequestBody @Valid NoteDto noteDto,
                                                 BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        return ok(INSTANCE.journalToDto(journalService.save(getJournal(noteDto))));
    }

    private Journal getJournal(NoteDto dto) {
        List<Book> books = new ArrayList<>();
        for (String isbn : dto.getIsbnList()){
            Book book = bookService.findByISBN(isbn);
            book.setBookStatus(BookStatus.OUTSIDE);
            bookService.updateBookStatus(book);
            books.add(book);
        }

        User reader = userService.findByNameAndSurnameAndParentName(dto.getReaderName(),
                dto.getReaderSurname(), dto.getReaderParentName());

        LocalDate dateOfGetBook = LocalDate.now();
        LocalDate dateOfReturnBook = LocalDate.now().plusMonths(1);

        return Journal.builder()
                .book(books)
                .reader(reader)
                .dateOfGetBook(dateOfGetBook)
                .dateOfReturnBook(dateOfReturnBook)
                .build();
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<JournalDto>> findAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        List<JournalDto> notes = new ArrayList<>();
        journalService.findAllWithPagination(PageRequest.of(page, size, Sort.Direction.DESC))
                .forEach(journal -> notes.add(INSTANCE.journalToDto(journal)));
        return ok(notes);
    }
}
