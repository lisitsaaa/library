package com.example.library.entity.library.book;

import com.example.library.entity.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Author extends AbstractEntity {
    private String name;
    private String surname;
    private String parentName;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Book> books;
}
