package bank.dki.studycase.backend.dto;

import java.time.ZonedDateTime;

public class StockDetailDTO extends StockDTO{
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private int updatedBy;
}
