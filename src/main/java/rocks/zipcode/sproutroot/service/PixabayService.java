package rocks.zipcode.sproutroot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PixabayService {

    @Value("${pixabay.api.key}")
    private String apiKey;

    @Value("${pixabay.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchImageUrl(String keyword) {
        try {
            String url = baseUrl + "?key=" + apiKey
                    + "&q=" + keyword.replace(" ", "+")
                    + "&image_type=photo"
                    + "&safesearch=true"
                    + "&per_page=3"
                    + "&orientation=horizontal";

            Map response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("hits")) {
                List<Map> hits = (List<Map>) response.get("hits");
                if (!hits.isEmpty()) {
                    return (String) hits.get(0).get("webformatURL");
                }
            }
        } catch (Exception e) {
            System.err.println("Pixabay fetch failed for keyword: " + keyword + " — " + e.getMessage());
        }
        return null;
    }
}