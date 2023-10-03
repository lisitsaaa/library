package com.example.library.entity.library;

import com.example.library.entity.AbstractEntity;
import com.example.library.entity.library.book.Book;
import com.example.library.entity.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
