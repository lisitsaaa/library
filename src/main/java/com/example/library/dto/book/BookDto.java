package com.example.library.dto.book;

import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.BookStatus;
import com.example.library.entity.library.book.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Information about book")
public class BookDto {
    @Schema(description = "International Standard Book Number - unique field")
    @NotNull @NotBlank @NotEmpty
    private String ISBN;

    @Schema(description = "title - max length = 60")
    @Length(max = 60)
    @NotNull @NotBlank @NotEmpty
    private String title;

    @Schema(description = "description - max length = 500")
    @Length(max = 500)
    @NotNull @NotBlank @NotEmpty
    private String description;

    @Schema(description = "Information about authors")
    private List<Author> authors;

    @Schema(description = "genres - enum")
    private List<Genre> genres;

    @Schema(description = "book status - enum")
    private BookStatus bookStatus;
}
