package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.*;

import java.time.LocalDateTime;
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

    MessageRepositoryJdbclmpl mrj = new MessageRepositoryJdbclmpl(ds);
    try {
      Optional<Message> optMes = mrj.findById(4L);

      if (optMes.isPresent()) {
        optMes.get().setMessageRoom(null);
        optMes.get().setAuthor(null);
        optMes.get().setMessage("What happened?");
        optMes.get().setDateTime(null);
        mrj.update(optMes.get());
      }
    } catch(NotSavedSubEntityException e) {
      System.out.println(e.getMessage());
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }

    ds.close();
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

  private static final String url_ = "jdbc:postgresql://localhost/postgres";
  private static final String username_ = "postgres";
  private static final String password_ = "";
  private static final String schema_ = "src/main/resources/schema.sql";
  private static final String data_ = "src/main/resources/data.sql";
}
