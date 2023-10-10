package com.example.library.dto;

import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.entity.library.book.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BookDto {
    @NotNull @NotBlank @NotEmpty
    private String ISBN;

    @NotNull @NotBlank @NotEmpty
    private String title;

    @NotNull @NotBlank @NotEmpty
    private String description;

    private List<Author> authors;
    private List<Genre> genres;
    private BookStatus bookStatus;
}
