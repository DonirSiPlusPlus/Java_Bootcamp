package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
public class ProductRepositoryJdbclmpl implements ProductRepository {
  ProductRepositoryJdbclmpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Product> findAll() {
    List<Product> products = new LinkedList<>();

    try {
      ResultSet table = connection
              .prepareStatement("SELECT * FROM products;")
              .executeQuery();

      while (table.next()) {
//        System.out.println(table.getLong(1) +" " + table.getString(2)+" " + table.getInt(3));
        products.add(
                new Product(
                        table.getLong("id"),
                        table.getString("name"),
                        table.getInt("price")
                )
        );
      }

      table.close();
    } catch(SQLException e ) {
      System.out.println("Find all error:" + e.getMessage());
    }

    return products;
  }

  @Override
  public Optional<Product> findById(Long id) {
    Product found = null;
    try {
      PreparedStatement query = connection.prepareStatement(
              "SELECT * FROM products WHERE id = ?;");
      query.setLong(1, id);
      ResultSet table = query.executeQuery();

      if (table.next()) {
        String name = table.getString(2);
        int price = table.getInt(3);
        found = new Product(id, name, price);
      }

      query.close();
      table.close();
    } catch(SQLException e) {
      System.out.println("Find by id error:" + e.getMessage());
    }

    return Optional.ofNullable(found);
  }

  @Override
  public void update(Product product) {
    try {
//      if (!product.isExist(connection)) {
        PreparedStatement query = connection.prepareStatement(
                "UPDATE products " +
                        "SET name = ?, " +
                        "price = ? " +
                        "WHERE id = ?;"
        );


        query.setString(1, product.getName());
        query.setInt(2, product.getPrice());
        query.setLong(3, product.getId());
        query.execute();
        query.close();
//      }
    } catch(SQLException e) {
      System.out.println("Update error: " + e.getMessage());
    }
  }

  @Override
  public void save(Product product) {
    try {
      PreparedStatement query = connection.prepareStatement(
              "INSERT INTO products " +
                      "(name, price) " +
                      "VALUES (?, ?);");

      query.setString(1, product.getName());
      query.setLong(2, product.getPrice());
      query.execute();
      query.close();
    } catch(SQLException e) {
      System.out.println("Insert error: " + e.getMessage());
    }
  }

  @Override
  public void delete(Long id) {
    try {
      connection
              .prepareStatement(
                      "DELETE FROM products " +
                              "WHERE id = '" + id + "';")
              .executeUpdate();
    } catch (SQLException e) {
      System.out.println("Delete error:" + e.getMessage());
    }
  }

  private final Connection connection;
}