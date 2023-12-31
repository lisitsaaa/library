package com.example.library.mapper;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.book.UpdatedBookDto;
import com.example.library.entity.library.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book dtoToBook(BookDto dto);
    BookDto bookToDto(Book book);

    Book updatedDtoToBook(UpdatedBookDto dto);
}
