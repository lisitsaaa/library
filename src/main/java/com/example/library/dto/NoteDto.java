package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class NoteDto {
    @NotNull @NotBlank @NotEmpty
    private List<String> isbnList;

    @NotNull @NotBlank @NotEmpty
    private String readerName;

    @NotNull @NotBlank @NotEmpty
    private String readerSurname;

    @NotNull @NotBlank @NotEmpty
    private String readerParentName;
}
