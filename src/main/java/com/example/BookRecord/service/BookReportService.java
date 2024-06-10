package com.example.BookRecord.service;

import com.example.BookRecord.domain.BookReport;
import com.example.BookRecord.repository.BookReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookReportService {
    private final BookReportRepository bookReportRepository;

    public BookReportService(BookReportRepository bookReportRepository) {
        this.bookReportRepository = bookReportRepository;
    }

    public Long upload(BookReport bookReport) {
        BookReport savedBookReport = bookReportRepository.save(bookReport);
        return savedBookReport.getId();
    }

    public void modify(Long id, BookReport updatedBookReport) {
        bookReportRepository.update(id, updatedBookReport);
    }

    public void delete(Long bookReportId) {
        bookReportRepository.delete(bookReportId);
    }

    public List<BookReport> findAll() {
        return bookReportRepository.findAll();
    }

    public Optional<BookReport> findOne(Long bookReportId) {
        return bookReportRepository.findById(bookReportId);
    }

    public List<BookReport> findByMemberId(Long memberId) {
        return bookReportRepository.findByMemberId(memberId);
    }
}
