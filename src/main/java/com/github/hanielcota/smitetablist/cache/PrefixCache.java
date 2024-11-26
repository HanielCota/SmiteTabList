package com.github.hanielcota.smitetablist.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

public class PrefixCache {

    private final Cache<String, String> cache;

    public PrefixCache() {
        this.cache =
                Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();
    }

    public String get(String key) {
        return cache.getIfPresent(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public void invalidate(String key) {
        cache.invalidate(key);
    }

    public void clear() {
        cache.invalidateAll();
    }
}
