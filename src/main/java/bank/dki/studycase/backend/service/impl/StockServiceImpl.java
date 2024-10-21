package bank.dki.studycase.backend.service.impl;

import bank.dki.studycase.backend.dto.CreateUpdateStockDTO;
import bank.dki.studycase.backend.dto.StockDTO;
import bank.dki.studycase.backend.dto.StockDetailDTO;
import bank.dki.studycase.backend.exception.NotFoundException;
import bank.dki.studycase.backend.model.Stock;
import bank.dki.studycase.backend.repository.StockRepository;
import bank.dki.studycase.backend.service.StockService;
import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bank.dki.studycase.backend.constant.MimeTypeConstant.ACCEPTED_MIME_TYPES;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<StockDTO> getStocks() {
        return stockRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Void createStock(CreateUpdateStockDTO stockDTO, MultipartFile file) {
        validateInput(stockDTO, file);
        Stock stock = convertToEntity(stockDTO, file);
        stockRepository.save(stock);
        return null;
    }

    @Override
    public StockDetailDTO getStockDetail(long id) {
        Stock stockById = stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        return convertToDetailDTO(stockById);
    }

    @Override
    public StockDTO deleteStock(long id) {
        Stock stockById = stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        stockRepository.delete(stockById);
        return convertToDTO(stockById);
    }

    @Override
    public StockDTO updateStocks(long id, CreateUpdateStockDTO stockDTO, MultipartFile file) {
        Stock stockById = stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        stockById.setName(stockDTO.getName());
        stockById.setUpdatedBy(stockDTO.getUpdatedBy());
        stockById.setQuantity(stockDTO.getQuantity());
        stockById.setAdditionalInfo(stockDTO.getAdditionalInfo());
        stockById.setItemSerialNumber(stockDTO.getItemSerialNumber());
        if (Objects.nonNull(file)) stockById.setImagePath(file.getOriginalFilename());
        stockRepository.save(stockById);
        return null;
    }

    private void validateInput(CreateUpdateStockDTO stockDTO, MultipartFile file) {
        Objects.requireNonNull(stockDTO, "dto is null");
        validateMimeType(file);
    }

    @SneakyThrows
    private void validateMimeType(MultipartFile image) {
        File convFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(image.getBytes() );
        fos.close();
        Tika tika = new Tika();
        String mimeType = tika.detect(convFile);
        if (!ACCEPTED_MIME_TYPES.contains(mimeType)) {
            throw new TikaException("Unsupported image type: " + mimeType);
        }
    }

    private Stock convertToEntity(CreateUpdateStockDTO stockDTO, MultipartFile file) throws ParseException {
        Stock stock = modelMapper.map(stockDTO, Stock.class);
        stock.setImagePath(file.getOriginalFilename());
        return stock;
    }

    private StockDTO convertToDTO(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }

    private StockDetailDTO convertToDetailDTO(Stock stock) {
        return modelMapper.map(stock, StockDetailDTO.class);
    }
}
