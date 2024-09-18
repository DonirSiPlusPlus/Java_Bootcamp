package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.util.Optional;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessageRepositoryJdbclmpl;

public class Program {
  public static void main(String[] args) {
    HikariDataSource ds = new HikariDataSource();

    ds.setJdbcUrl(url_);
    ds.setUsername(username_);
    ds.setPassword(password_);

    runSqlBase(ds, schema_);
    runSqlBase(ds, data_);

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a Message ID:\n-> ");

    MessageRepositoryJdbclmpl mrj = new MessageRepositoryJdbclmpl(ds);
    Long id = sc.nextLong();

    try {
      Optional<Message> opt = mrj.findById(id);

      opt.ifPresent(System.out::print);
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }

    sc.close();
  }

  private static void runSqlBase(HikariDataSource ds, String filename) {
    try {
      Scanner sc = new Scanner(new File(filename)).useDelimiter(";");
      Connection connection = ds.getConnection();

      while(sc.hasNext()) {
        connection.createStatement().execute(sc.next().trim());
      }

      connection.close();
      sc.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static String url_ = "jdbc:postgresql://localhost/postgres";
  private static String username_ = "postgres";
  private static String password_ = "";
  private static String schema_ = "src/main/resources/schema.sql";
  private static String data_ = "src/main/resources/data.sql";
}
