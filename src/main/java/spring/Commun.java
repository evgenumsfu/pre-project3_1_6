package spring;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.models.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Commun {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Commun(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllUser() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> allUser = responseEntity.getBody();
        String sessionId = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(responseEntity.getBody());
        System.out.println(sessionId);
        return sessionId;
    }

    public String addUser(String sessionId, User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "James");
        map.put("lastName", "Brown");
        map.put("age", 23);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(URL, entity, String.class).getBody();
    }

    public String updateUser(String sessionId) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cookie", sessionId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "Thomas");
        map.put("lastName", "Shelby");
        map.put("age", 23);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        //HttpEntity<String> responseEntity =
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
    }

    public String deleteUser(String sessionId, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
