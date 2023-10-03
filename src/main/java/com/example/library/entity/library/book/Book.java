package com.example.library.entity.library.book;

import com.example.library.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book extends AbstractEntity {
    private String ISBN;
    private String title;
    private String description;

    @ManyToMany
    private List<Author> authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
}
