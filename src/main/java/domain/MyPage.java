package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class MyPage {
    @Id
    private Long id;

    @OneToOne
    private MyPageProfile myPageProfile;

    private String goal;
    private String genre;

    @OneToOne
    private Member member;

    @OneToMany
    private List<BookReport> bookReports; // 책 기록 리스트

    // 캘린더 형식으로 책 읽은 날짜를 반환하는 메서드
    public Map<LocalDate, List<BookReport>> getReadDatesCalendar() {
        return bookReports.stream()
                .collect(Collectors.groupingBy(report -> report.getBookMemberData().getReadDate())); // 수정된 람다 표현식
    }
}