package rocks.zipcode.sproutroot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GiphyService {

    @Value("${giphy.api.key}")
    private String apiKey;

    @Value("${giphy.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchGifUrl(String keyword) {
        try {
            String url = baseUrl
                    + "?api_key=" + apiKey
                    + "&q=" + keyword.replace(" ", "+") + "+cartoon"
                    + "&limit=3"
                    + "&rating=g"
                    + "&lang=en";

            Map response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("data")) {
                List<Map> data = (List<Map>) response.get("data");
                if (!data.isEmpty()) {
                    Map images = (Map) data.get(0).get("images");
                    Map fixed = (Map) images.get("fixed_height_small");
                    return (String) fixed.get("url");
                }
            }
        } catch (Exception e) {
            System.err.println("Giphy fetch failed for: " + keyword + " — " + e.getMessage());
        }
        return null;
    }
}