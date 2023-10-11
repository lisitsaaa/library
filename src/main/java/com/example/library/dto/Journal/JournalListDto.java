package com.example.library.dto.Journal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JournalListDto {
    private List<JournalDto> journalDtoList;
}
