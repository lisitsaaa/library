package com.example.library.entity.library;

import com.example.library.entity.AbstractEntity;
import com.example.library.entity.library.book.Book;
import com.example.library.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Journal extends AbstractEntity {
    private LocalDate dateOfGetBook;
    private LocalDate dateOfReturnBook;

    @OneToOne
    private User reader;

    @OneToMany
    private List<Book> book;
}
