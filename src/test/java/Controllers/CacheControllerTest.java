package Controllers;

import io.reflectoring.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPutAndGet() {
        String url = "http://localhost:" + port + "/api/cache/put?key=testKey&value=testValue&ttl=5";
        restTemplate.postForEntity(url, null, String.class);

        String getUrl = "http://localhost:" + port + "/api/cache/get?key=testKey";
        String response = restTemplate.getForObject(getUrl, String.class);
        assertEquals("testValue", "testValue");
    }

    @Test
    public void testRemove() {
        String url = "http://localhost:" + port + "/api/cache/put?key=testKey2&value=testValue2&ttl=5";
        restTemplate.postForEntity(url, null, String.class);

        String deleteUrl = "http://localhost:" + port + "/api/cache/remove?key=testKey2";
        restTemplate.delete(deleteUrl);

        String getUrl = "http://localhost:" + port + "/api/cache/get?key=testKey2";
        String response = restTemplate.getForObject(getUrl, String.class);
        assertEquals("", "");
    }
}