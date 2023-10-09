package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class NoteDto {
    private List<String> isbnList;
    private String readerName;
    private String readerSurname;
    private String readerParentName;
}
