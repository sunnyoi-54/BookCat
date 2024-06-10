package com.example.BookRecord.controller;

import com.example.BookRecord.domain.BookReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.BookRecord.service.BookReportService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/bookReports")
public class BookReportController {
    private final BookReportService bookReportService;

    @Autowired
    public BookReportController(BookReportService bookReportService) {
        this.bookReportService = bookReportService;
    }

    @GetMapping
    public String bookReportList(@RequestParam Long memberId, Model model) {
        List<BookReport> bookReports = bookReportService.findByMemberId(memberId);
        model.addAttribute("bookReports", bookReports);
        return "bookReport/list";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("bookReportForm", new BookReportForm());
        return "bookReport/uploadForm";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute BookReportForm bookReportForm, BindingResult result) {
        if (result.hasErrors()) {
            return "bookReport/uploadForm";
        }
        BookReport bookReport = new BookReport();
        bookReport.setBookData(bookReportForm.getBookData());
        bookReport.setBookMemberData(bookReportForm.getBookMemberData());
        bookReport.setMember(bookReportForm.getMember());
        bookReportService.upload(bookReport);
        return "redirect:/bookReports";
    }

    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable Long id, Model model) {
        BookReport bookReport = getBookReportOrThrow(id);
        BookReportForm bookReportForm = new BookReportForm();
        bookReportForm.setBookData(bookReport.getBookData());
        bookReportForm.setBookMemberData(bookReport.getBookMemberData());
        bookReportForm.setMember(bookReport.getMember());
        model.addAttribute("bookReportForm", bookReportForm);
        return "bookReport/modifyForm";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable Long id, @ModelAttribute BookReportForm bookReportForm, BindingResult result) {
        if (result.hasErrors()) {
            return "bookReport/modifyForm";
        }
        BookReport bookReport = getBookReportOrThrow(id);
        bookReport.setBookData(bookReportForm.getBookData());
        bookReport.setBookMemberData(bookReportForm.getBookMemberData());
        bookReport.setMember(bookReportForm.getMember());
        bookReportService.modify(id, bookReport);
        return "redirect:/bookReports";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookReportService.delete(id);
        return "redirect:/bookReports";
    }

    @GetMapping("/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        BookReport bookReport = getBookReportOrThrow(id);
        model.addAttribute("bookReport", bookReport);
        return "bookReport/deleteConfirm";
    }

    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        BookReport bookReport = getBookReportOrThrow(id);
        model.addAttribute("bookReport", bookReport);
        return "bookReport/viewDetail";
    }

    private BookReport getBookReportOrThrow(Long id) {
        return bookReportService.findOne(id).orElseThrow(() ->
                new NoSuchElementException("Invalid book report ID: " + id));
    }
}
