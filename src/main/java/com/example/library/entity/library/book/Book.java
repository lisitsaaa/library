package com.example.library.entity.library.book;

import com.example.library.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book extends AbstractEntity {
    private String ISBN;
    private String title;
    private String description;

    @OneToMany
    private List<Author> authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @Enumerated(value = EnumType.STRING)
    private BookStatus bookStatus;
}
