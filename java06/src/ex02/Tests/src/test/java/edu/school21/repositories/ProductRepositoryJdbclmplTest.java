package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ProductRepositoryJdbclmplTest {
  @BeforeEach
  void init() throws SQLException {
    ds = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("/schema.sql")
            .addScript("/data.sql")
            .build();
    repository = new ProductRepositoryJdbclmpl(ds.getConnection());
  }

  @Test
  public void testConnection() {
    try {
      Assertions.assertNotNull(ds.getConnection());
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void isFindAll() {
    List<Product> result = repository.findAll();
    Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, result);
  }

  @Test
  public void isFindById() {
    Product result = repository.findById(2L).orElse(null);
    Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, result);
  }

  @Test
  public void isUpdated() {
    repository.update(new Product(0L, "RTX 4080", 91000));
    Product result = repository.findById(0L).orElse(null);
    Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, result);
  }

  @Test
  public void isInserted() {
    repository.save(new Product("ASUS 4K", 35000));
    Product result = repository.findById(6L).orElse(null);
    Assertions.assertEquals(EXPECTED_INSERT_PRODUCT, result);
  }

  @Test
  public void isDeleted() {
    repository.delete(5L);
    Product result = repository.findById(6L).orElse(null);
    Assertions.assertNull(result);
  }

  final List<Product> EXPECTED_FIND_ALL_PRODUCTS = List.of(
          new Product(0L, "RTX 4060", 30000),
          new Product(1L, "SSD Samsung", 8000),
          new Product(2L, "i5 10400f", 10000),
          new Product(3L, "RAM Corsair", 5000),
          new Product(4L, "MSI B650", 5000),
          new Product(5L, "Chieftec 1000W", 7000));
  final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "i5 10400f", 10000);
  final Product EXPECTED_UPDATED_PRODUCT = new Product(0L, "RTX 4080", 91000);
  final Product EXPECTED_INSERT_PRODUCT = new Product(6L, "ASUS 4K", 35000);
  private DataSource ds;
  private ProductRepository repository;
}