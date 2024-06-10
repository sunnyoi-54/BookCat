package com.example.BookRecord.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MyPageProfile {
    @Id
    private Long id;

    private String name;
    private Long birthday;
    private String email;
}
