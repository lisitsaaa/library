package com.example.library.dto;

import com.example.library.entity.library.book.Book;
import com.example.library.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JournalDto {
    private LocalDate dateOfGetBook;
    private LocalDate dateOfReturnBook;
    private User reader;
    private List<Book> books;
}
