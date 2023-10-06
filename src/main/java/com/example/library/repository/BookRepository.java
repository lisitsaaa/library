package com.example.library.repository;

import com.example.library.entity.library.book.Book;
import com.example.library.entity.library.book.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN(String isbn);

    @Modifying
    @Query("update Book b set b.bookStatus=:bookStatus where b.id=:id")
    void updateBookStatus(@Param("bookStatus") BookStatus bookStatus,
                          @Param("id") long id);
}
