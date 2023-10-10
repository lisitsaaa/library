package com.example.library.dto;

import com.example.library.entity.library.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BookStatusDto {
    @NotNull @NotBlank @NotEmpty
    @Length(max = 10)
    private BookStatus bookStatus;
}
