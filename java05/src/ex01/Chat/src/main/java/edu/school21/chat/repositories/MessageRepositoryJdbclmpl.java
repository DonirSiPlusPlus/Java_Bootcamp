package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessageRepositoryJdbclmpl implements MessageRepository {

  public MessageRepositoryJdbclmpl(DataSource ds) {
    ds_ = ds;
  }

  @Override
  public Optional<Message> findById(Long id) throws SQLException {
    Connection connection = ds_.getConnection();
    String sqlQuery = "SELECT * FROM chat.messages WHERE id = ?";

    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setLong(1, id);

    ResultSet queryResult = statement.executeQuery();
    queryResult.next();

    User user = getUser(connection, queryResult.getString("author"));
    Chatroom room = getChatroom(connection, queryResult.getString("chatroom"));
    String message = queryResult.getString("message");
    String date = queryResult.getString("date");

    connection.close();
    statement.close();
    queryResult.close();

    return Optional.of(new Message(id, user, room, message, date));
  }

  private User getUser(Connection connection, String username) throws SQLException {
    String sqlQuery = "SELECT * FROM chat.users WHERE login = ?";

    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1, username);

    ResultSet queryResult = statement.executeQuery();
    queryResult.next();

    Long id = queryResult.getLong("id");
    String login = queryResult.getString("login");
    String password = queryResult.getString("password");

    return new User(id, login, password);
  }

  private Chatroom getChatroom(Connection connection, String roomname) throws SQLException {
    String sqlQuery = "SELECT * FROM chat.chatrooms WHERE name = ?";

    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1, roomname);

    ResultSet queryResult = statement.executeQuery();
    queryResult.next();

    Long id = queryResult.getLong("id");
    String name = queryResult.getString("name");
    User owner = getUser(connection, queryResult.getString("owner"));

    return new Chatroom(id, name, owner);
  }


  DataSource ds_;
}