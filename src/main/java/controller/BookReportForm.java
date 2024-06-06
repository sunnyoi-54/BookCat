package controller;

import domain.BookData;
import domain.BookMemberData;
import domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookReportForm {
    private BookData bookData;
    private BookMemberData bookMemberData;
    private Member member;
}
