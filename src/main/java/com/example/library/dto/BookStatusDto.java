package com.example.library.dto;

import com.example.library.entity.library.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BookStatusDto {
    private BookStatus bookStatus;
}
