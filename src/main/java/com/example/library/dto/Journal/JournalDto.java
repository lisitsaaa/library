package com.example.library.dto.Journal;

import com.example.library.entity.library.book.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Information about journal note")
public class JournalDto {
    @Schema(description = "date of get book - auto field (current date)")
    private LocalDate dateOfGetBook;

    @Schema(description = "date of return book - auto field (current date + 1 month)")
    private LocalDate dateOfReturnBook;

    @Schema(description = "Information about book")
    private Book book;
}
