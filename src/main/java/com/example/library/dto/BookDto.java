package com.example.library.dto;

import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.entity.library.book.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BookDto {
    private String ISBN;
    private String title;
    private String description;
    private List<Author> authors;
    private List<Genre> genres;
    private BookStatus bookStatus;
}
