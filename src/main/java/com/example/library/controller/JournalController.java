package com.example.library.controller;

import com.example.library.dto.Journal.JournalDto;
import com.example.library.dto.Journal.JournalListDto;
import com.example.library.wrapper.JournalWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {
    private final JournalWrapper journalWrapper;

    @GetMapping("/{bookId}")
    public JournalDto createNote(@PathVariable long bookId) {
        return journalWrapper.save(bookId);
    }

    @GetMapping("/pagination")
    public JournalListDto findAllWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        return journalWrapper.getAllWithPagination(PageRequest.of(page, size));
    }
}
