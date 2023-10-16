package com.example.library.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Information about books")
public class BookListDto {
    private List<BookDto> booksDto;
}
