package com.example.BookRecord.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class BookMemberData {
    @Id
    private Long id;

    private Long period;
    private Long readingAmount;
    private String review;
    private Integer star;
    private LocalDate readDate; // 책 읽은 날짜
}
