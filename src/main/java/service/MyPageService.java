package service;

import domain.BookReport;
import domain.MyPage;
import repository.MyPageRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MyPageService {
    private final MyPageRepository myPageRepository;

    // 외부에서 MyPageRepository를 주입받도록 변경
    public MyPageService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    // MyPage 객체를 저장하고, 저장된 객체의 ID를 반환
    public Long upload(MyPage myPage) {
        myPageRepository.save(myPage);
        return myPage.getId();
    }

    // 주어진 ID에 해당하는 페이지를 새로운 내용으로 업데이트
    public void modify(Long myPageId, MyPage newPage) {
        myPageRepository.update(myPageId, newPage); // 업데이트된 내용으로 저장
    }

    // 캘린더 형식으로 책 읽은 날짜를 반환하는 메서드
    public Map<LocalDate, List<BookReport>> getReadDatesCalendar(Long myPageId) {
        MyPage myPage = myPageRepository.findById(myPageId)
                .orElseThrow(() -> new IllegalArgumentException("The page with the given ID does not exist: " + myPageId));
        return myPage.getReadDatesCalendar();
    }

    // 주어진 ID에 해당하는 페이지를 찾아 Optional<MyPage> 형태로 반환
    public Optional<MyPage> findOne(Long id) {
        return myPageRepository.findById(id);
    }
}
