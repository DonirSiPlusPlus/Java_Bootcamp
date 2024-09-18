package edu.school21.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.lang.Object;
import java.util.Objects;


public class Product {
  public Product(String name, int price) {
    this.name = name;
    this.price = Math.max(price, 0);
  }

  public Product(Long id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = Math.max(price, 0);
  }

  public boolean isExist(Connection connection) throws SQLException {
    return connection
            .prepareStatement(
                    "SELECT *" +
                            "FROM products " +
                            "WHERE id = '" + id + "';"
    ).executeQuery().next();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Product product = (Product) obj;

    return Objects.equals(product.id, this.id)
            && product.name.equals(this.name)
            && product.price == this.price;
  }

  public Long getId() { return id; }

  public String getName() { return name; }

  public int getPrice() { return price; }

  public void setId(Long id) { this.id = id; }

  public void setPrice(int new_price) { price = new_price; }

  private Long id;
  private final String name;
  private int price;
}