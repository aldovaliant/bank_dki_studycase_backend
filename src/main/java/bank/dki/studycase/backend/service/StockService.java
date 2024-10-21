package bank.dki.studycase.backend.service;

import bank.dki.studycase.backend.dto.CreateUpdateStockDTO;
import bank.dki.studycase.backend.dto.StockDTO;
import bank.dki.studycase.backend.dto.StockDetailDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StockService {
    List<StockDTO> getStocks();

    Void createStock(CreateUpdateStockDTO stockDTO, MultipartFile file);

    StockDetailDTO getStockDetail(long id);

    StockDTO deleteStock(long id);

    StockDTO updateStocks(long id, CreateUpdateStockDTO stockDTO, MultipartFile file);
}
