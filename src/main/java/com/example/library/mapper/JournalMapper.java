package com.example.library.mapper;

import com.example.library.dto.JournalDto;
import com.example.library.dto.NoteDto;
import com.example.library.entity.library.Journal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JournalMapper {
    JournalMapper INSTANCE = Mappers.getMapper(JournalMapper.class);

    Journal dtoToJournal(JournalDto dto);
    JournalDto journalToDto(Journal journal);
}
