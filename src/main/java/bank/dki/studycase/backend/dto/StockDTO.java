package bank.dki.studycase.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class StockDTO {
    private Long id;
    private String name;
    private int quantity;
    private String itemSerialNumber;
    private JsonNode additionalInfo;
    private String imagePath;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private int updatedBy;
}
