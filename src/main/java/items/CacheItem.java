package items;

import lombok.Getter;

import java.time.LocalDateTime;

public class CacheItem<T> {
    @Getter
    private T value;
    private LocalDateTime expiryTime;

    public CacheItem(T value, long ttl) {
        this.value = value;
        this.expiryTime = LocalDateTime.now().plusSeconds(ttl);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}

