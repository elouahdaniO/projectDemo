package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheServiceTest {

    private CacheService<String, String> cacheService;

    @BeforeEach
    public void setUp() {
        cacheService = new CacheService<>();
    }

    @Test
    public void testPutAndGet() {
        cacheService.put("key1", "value1", 5);
        assertEquals("value1", cacheService.get("key1"));
    }

    @Test
    public void testExpiredEntry() throws InterruptedException {
        cacheService.put("key2", "value2", 1);
        Thread.sleep(2000);
        assertNull(cacheService.get("key2"));
    }

    @Test
    public void testRemoveEntry() {
        cacheService.put("key3", "value3", 5);
        cacheService.remove("key3");
        assertNull(cacheService.get("key3"));
    }
}
