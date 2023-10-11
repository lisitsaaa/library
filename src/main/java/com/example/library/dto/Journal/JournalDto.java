package com.example.library.dto.Journal;

import com.example.library.entity.library.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JournalDto {
    private LocalDate dateOfGetBook;
    private LocalDate dateOfReturnBook;
    private Book books;
}
