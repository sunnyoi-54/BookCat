package com.example.BookRecord.repository;

import com.example.BookRecord.domain.MyPage;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MyPageRepository {
    private static final Map<Long, MyPage> store = new ConcurrentHashMap<>();

    public MyPage save(MyPage myPage) {
        myPage.setId(myPage.getMember().getId());
        store.put(myPage.getId(), myPage);
        return myPage;
    }

    public void update(Long myPageId, MyPage updatedPage) {
        store.put(myPageId, updatedPage);
    }

    public Optional<MyPage> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
