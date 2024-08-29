package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.CacheService;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @Autowired
    private CacheService<String, Object> cacheService;

    @PostMapping("/put")
    public void put(@RequestParam String key, @RequestParam Object value, @RequestParam long ttl) {
        cacheService.put(key, value, ttl);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return cacheService.get(key);
    }

    @DeleteMapping("/remove")
    public void remove(@RequestParam String key) {
        cacheService.remove(key);
    }

    @GetMapping("/contains")
    public boolean contains(@RequestParam String key) {
        return cacheService.containsKey(key);
    }

}
