package com.example.library.service;

import com.example.library.entity.library.Journal;
import com.example.library.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final BookService bookService;

    private static final int ONE_MONTH = 1;

    public Journal save(long bookId) {
        return journalRepository.save(Journal
                .builder()
                .book(bookService.findById(bookId))
                .dateOfGetBook(LocalDate.now())
                .dateOfReturnBook(LocalDate.now().plusMonths(ONE_MONTH))
                .build());
    }

    @Transactional(readOnly = true)
    public List<Journal> findAllWithPagination(PageRequest pageRequest) {
        return journalRepository.findAll(pageRequest).getContent();
    }
}
