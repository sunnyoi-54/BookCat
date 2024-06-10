package com.example.BookRecord.controller;

import com.example.BookRecord.domain.Member;
import com.example.BookRecord.domain.MyPageProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageForm {
    private MyPageProfile myPageProfile;
    private String goal;
    private String genre;
    private Member member;

}
