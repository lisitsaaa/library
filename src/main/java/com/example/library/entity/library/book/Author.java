package com.example.library.entity.library.book;

import com.example.library.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Author extends AbstractEntity {
    private String name;
    private String surname;
    private String parentName;
}
