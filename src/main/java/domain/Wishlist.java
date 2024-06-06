package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Wishlist {
    @Id
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private String genre;

    @OneToOne
    private Member member;
}
