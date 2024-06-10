package com.example.BookRecord.controller;

import com.example.BookRecord.domain.BookData;
import com.example.BookRecord.domain.BookMemberData;
import com.example.BookRecord.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookReportForm {
    private BookData bookData;
    private BookMemberData bookMemberData;
    private Member member;
}
