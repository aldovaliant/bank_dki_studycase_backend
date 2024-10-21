package bank.dki.studycase.backend.controller;

import bank.dki.studycase.backend.dto.CreateUpdateStockDTO;
import bank.dki.studycase.backend.dto.StockDTO;
import bank.dki.studycase.backend.dto.StockDetailDTO;
import bank.dki.studycase.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestPart CreateUpdateStockDTO stockDTO, @RequestPart MultipartFile file) {
        return ResponseEntity.ok(stockService.createStock(stockDTO, file));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockService.getStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDetailDTO> getStockDetail(@PathVariable long id) {
        return ResponseEntity.ok(stockService.getStockDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(
            @PathVariable long id,
            @RequestPart CreateUpdateStockDTO stockDTO,
            @RequestPart MultipartFile file) {
        return ResponseEntity.ok(stockService.updateStocks(id, stockDTO, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StockDTO> deleteStock(@PathVariable long id) {
        return ResponseEntity.ok(stockService.deleteStock(id));
    }
}
