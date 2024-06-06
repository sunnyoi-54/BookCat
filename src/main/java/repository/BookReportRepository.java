package repository;

import domain.BookReport;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BookReportRepository {
    private static final Map<Long, BookReport> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public BookReport save(BookReport bookReport) {
        bookReport.setId(sequence.incrementAndGet());
        store.put(bookReport.getId(), bookReport);
        return bookReport;
    }

    public void update(Long id, BookReport updatedBookReport) {
        if (store.containsKey(id)) {
            store.put(id, updatedBookReport);
        } else {
            throw new NoSuchElementException("Invalid book report ID: " + id);
        }
    }

    public void delete(Long id) {
        store.remove(id);
    }

    public Optional<BookReport> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<BookReport> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<BookReport> findByMemberId(Long memberId) {
        return store.values().stream()
                .filter(bookReport -> bookReport.getMember().getId().equals(memberId))
                .collect(Collectors.toList());
    }
}
