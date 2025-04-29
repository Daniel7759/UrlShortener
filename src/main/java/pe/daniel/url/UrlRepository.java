package pe.daniel.url;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url,String> {

    Optional<Url> findByShortCode(String shortCode);
}
