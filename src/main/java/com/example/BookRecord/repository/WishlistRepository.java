package com.example.BookRecord.repository;

import com.example.BookRecord.domain.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WishlistRepository {
    private static Map<Long, Wishlist> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public synchronized Wishlist save(Wishlist wishlist) {
        wishlist.setId(++sequence);
        store.put(wishlist.getId(), wishlist);
        return wishlist;
    }

    public void delete(Long id) {
        store.remove(id);
    }

    public Optional<Wishlist> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Wishlist> findAll() {
        return new ArrayList<>(store.values());
    }
}
