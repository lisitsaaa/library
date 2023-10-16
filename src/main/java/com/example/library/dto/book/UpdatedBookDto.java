package com.example.library.dto.book;

import com.example.library.entity.library.book.Author;
import com.example.library.entity.library.book.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Request information for updating book")
public class UpdatedBookDto {
    @Schema(description = "International Standard Book Number - unique field")
    private String ISBN;

    @Schema(description = "title - max length = 60")
    @Length(max = 60)
    private String title;

    @Schema(description = "description - max length = 500")
    @Length(max = 500)
    private String description;

    @Schema(description = "Information about authors")
    private List<Author> authors;

    @Schema(description = "genres - enum")
    private List<Genre> genres;
}
