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
        // try cartoon illustration first
        String url = buildUrl(keyword + " cartoon", "illustration");
        String result = fetch(url);
        if (result != null) return result;

        // fallback — try without cartoon
        url = buildUrl(keyword, "illustration");
        result = fetch(url);
        if (result != null) return result;

        // last fallback — any image type
        url = buildUrl(keyword, "all");
        return fetch(url);
    }

    private String buildUrl(String keyword, String imageType) {
        return baseUrl + "?key=" + apiKey
                + "&q=" + keyword.replace(" ", "+")
                + "&image_type=" + imageType
                + "&safesearch=true"
                + "&per_page=5"
                + "&min_width=300";
    }

    private String fetch(String url) {
        try {
            Map response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("hits")) {
                List<Map> hits = (List<Map>) response.get("hits");
                if (!hits.isEmpty()) {
                    return (String) hits.get(0).get("webformatURL");
                }
            }
        } catch (Exception e) {
            System.err.println("Pixabay fetch failed: " + e.getMessage());
        }
        return null;
    }
}
