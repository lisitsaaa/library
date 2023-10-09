package com.example.library.service;

import com.example.library.entity.library.Journal;
import com.example.library.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    public Journal save(Journal journal) {
        journal.setDateOfGetBook(LocalDate.now());
        return journalRepository.save(journal);
    }

    @Transactional(readOnly = true)
    public List<Journal> findAllWithPagination(PageRequest pageRequest){
        return journalRepository.findAll(pageRequest).getContent();
    }
}
