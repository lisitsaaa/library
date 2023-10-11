package com.example.library.entity.library;

import com.example.library.entity.AbstractEntity;
import com.example.library.entity.library.book.Book;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Journal extends AbstractEntity {
    private LocalDate dateOfGetBook;
    private LocalDate dateOfReturnBook;

    @OneToOne
    private Book book;
}
