package com.example.BookRecord.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookReport {
    @Id
    private Long id;

    @OneToOne
    private BookData bookData;

    @OneToOne
    private BookMemberData bookMemberData;

    @ManyToOne
    private Member member;
}
