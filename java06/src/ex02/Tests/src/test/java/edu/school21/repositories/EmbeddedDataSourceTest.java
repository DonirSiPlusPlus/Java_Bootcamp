package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

  @BeforeEach
  public void init() {
    EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
    ds = edb.addScript("/schema.sql")
            .addScript("/data.sql")
            .build();
  }

  @Test
  public void testConnection() {
    try {
      Assertions.assertNotNull(ds.getConnection());
    } catch(SQLException e) {
//      System.out.println(e.getMessage());
    }
  }

  private DataSource ds;
}