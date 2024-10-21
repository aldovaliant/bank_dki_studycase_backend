package bank.dki.studycase.backend.model;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonNodeBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.ZonedDateTime;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock", schema = "public")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int quantity;

    private String itemSerialNumber;

    @Type(JsonNodeBinaryType.class)
    @Column(name = "additional_info", columnDefinition = "jsonb")
    private JsonNode additionalInfo;

    private String imagePath;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Integer updatedBy;
}
