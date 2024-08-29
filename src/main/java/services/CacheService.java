package services;

import items.CacheItem;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService<K, V> {

    private final ConcurrentHashMap<K, CacheItem<V>> cacheMap = new ConcurrentHashMap<>();

    public void put(K key, V value, long ttl) {
        cacheMap.put(key, new CacheItem<>(value, ttl));
    }

    public V get(K key) {
        CacheItem<V> cacheItem = cacheMap.get(key);
        if (cacheItem == null || cacheItem.isExpired()) {
            cacheMap.remove(key);
            return null;
        }
        return cacheItem.getValue();
    }

    public void remove(K key) {
        cacheMap.remove(key);
    }

    public boolean containsKey(K key) {
        return cacheMap.containsKey(key) && !cacheMap.get(key).isExpired();
    }

}
