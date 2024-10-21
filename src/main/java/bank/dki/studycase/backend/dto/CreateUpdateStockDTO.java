package bank.dki.studycase.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class CreateUpdateStockDTO {
    private String name;
    private int quantity;
    private String itemSerialNumber;
    private JsonNode additionalInfo;
    private Integer updatedBy;
}
