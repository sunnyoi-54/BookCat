package com.example.BookRecord.controller;

import com.example.BookRecord.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistForm {
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private Member member;

}
