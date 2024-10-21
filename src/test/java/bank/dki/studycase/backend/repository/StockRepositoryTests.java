package bank.dki.studycase.backend.repository;

import bank.dki.studycase.backend.model.Stock;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockRepositoryTests {
    @Autowired
    private StockRepository stockRepository;

    @Test
    public void shouldSaveStockWhenSavingStock() {
        Stock stock = Stock.builder()
                .id(1L)
                .name("book")
                .quantity(1)
                .additionalInfo(JsonNodeFactory.instance.objectNode())
                .itemSerialNumber("123")
                .imagePath("image/png")
                .build();
        stockRepository.save(stock);

        Assertions.assertThat(stock).isNotNull();
        Assertions.assertThat(stock.getId()).isGreaterThan(0);
    }
}
