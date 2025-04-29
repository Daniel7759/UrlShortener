package pe.daniel.url;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {

    @MongoId
    @JsonView(JsonViews.Default.class)
    private String id;

    @NotBlank(message = "Debe ingresar una URL valida")
    @JsonView(JsonViews.Default.class)
    private String url;

    @JsonView(JsonViews.Default.class)
    private String shortCode;
    @JsonView(JsonViews.Default.class)
    private Date createdAt;
    @JsonView(JsonViews.Default.class)
    private Date updatedAt;
    @JsonView(JsonViews.Stats.class)
    private Integer accessCount = 0;
}
