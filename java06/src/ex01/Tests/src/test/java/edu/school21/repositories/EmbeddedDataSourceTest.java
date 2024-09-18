package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

  @BeforeEach
  void init() {
    EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
    ds = edb.addScript("/schema.sql")
            .addScript("/data.sql")
            .build();
  }

  @Test
  void testConnection() throws SQLException {
    Assertions.assertNotNull(ds.getConnection());
  }

  private DataSource ds;
}