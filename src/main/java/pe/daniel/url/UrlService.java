package pe.daniel.url;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createShortUrl(String url) {
        String shortCode = generateShortCode();
        Url urlEntity = new Url();
        urlEntity.setUrl(url);
        urlEntity.setShortCode(shortCode);
        urlEntity.setCreatedAt(new Date());
        urlEntity.setUpdatedAt(new Date());
        return urlRepository.save(urlEntity);
    }

    public Url updateShortUrl(String shortCode, String url) {
        Url existingShortUrl = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));
        existingShortUrl.setUrl(url);
        existingShortUrl.setUpdatedAt(new Date());
        return urlRepository.save(existingShortUrl);
    }

    public Url getShortUrl(String shortCode) {
        Url existingUrl = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));
        existingUrl.setAccessCount(existingUrl.getAccessCount() + 1);
        urlRepository.save(existingUrl);
        return existingUrl;
    }

    public Url getStatsShortUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));
    }

    public void deleteShortUrl(String shortCode) {
        Url existingUrl = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));
        urlRepository.delete(existingUrl);
    }

    private String generateShortCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit +1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
