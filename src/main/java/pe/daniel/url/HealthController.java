package pe.daniel.url;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HealthController {

    private final UrlService urlService;

    public HealthController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>("Servicio en linea", HttpStatus.OK);
    }

    @GetMapping("/env/test")
    public String printEnv() {
        return System.getenv("DATABASE_URL");
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirectToOriginalUrl(@PathVariable(value = "shortCode") String shortCode){
        Url urlDB = urlService.getShortUrl(shortCode);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlDB.getUrl());
        return redirectView;
    }
}
