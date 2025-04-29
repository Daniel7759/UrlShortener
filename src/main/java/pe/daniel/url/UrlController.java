package pe.daniel.url;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shorten")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @JsonView(JsonViews.Default.class)
    public ResponseEntity<?> createShortUrl(@Valid @RequestBody Url url){
        try{
            Url urlDB = urlService.createShortUrl(url.getUrl());
            return new ResponseEntity<>(urlDB, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shortCode}")
    @JsonView(JsonViews.Default.class)
    public ResponseEntity<?> retrieveOriginalUrl(@PathVariable(value = "shortCode") String shortCode){
        try {
            Url urlDB = urlService.getShortUrl(shortCode);
            return new ResponseEntity<>(urlDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{shortCode}")
    @JsonView(JsonViews.Default.class)
    public ResponseEntity<?> updateShortUrl(@PathVariable(value = "shortCode") String shortCode, @Valid @RequestBody Url url){
        try {
            Url urlDB = urlService.updateShortUrl(shortCode, url.getUrl());
            return new ResponseEntity<>(urlDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<?> deleteShortUrl(@PathVariable(value = "shortCode") String shortCode){
        try {
            urlService.deleteShortUrl(shortCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{shortCode}/stats")
    @JsonView(JsonViews.Stats.class)
    public ResponseEntity<?> getShortUrlStats(@PathVariable(value = "shortCode") String shortCode){
        try {
            Url urlDB = urlService.getStatsShortUrl(shortCode);
            return new ResponseEntity<>(urlDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
