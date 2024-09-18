package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

import java.time.LocalDateTime;

public class MessageRepositoryJdbclmpl implements MessageRepository {
  public MessageRepositoryJdbclmpl(DataSource ds) {
    ds_ = ds;
  }

  @Override
  public Optional<Message> findById(Long id) throws SQLException {
    Connection connection = ds_.getConnection();

    PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM chat.messages WHERE id = ?"
    );
    statement.setLong(1, id);

    ResultSet queryResult = statement.executeQuery();
    queryResult.next();

    User user = findUser(connection, queryResult.getString("author"));
    Chatroom room = findChatroom(connection, queryResult.getString("chatroom"));
    String message = queryResult.getString("message");
    Timestamp date = queryResult.getTimestamp("date");

    queryResult.close();
    statement.close();
    connection.close();

    return Optional.of(new Message(id, user, room, message, date.toLocalDateTime()));
  }

  @Override
  public void save(Message message) throws NotSavedSubEntityException, SQLException {
    Connection connection = ds_.getConnection();

    if (!message.getAuthor().isExist(connection)
            || !message.getChatroom().isExist(connection)) {
      throw new NotSavedSubEntityException();
    }

    PreparedStatement insertQuery = connection.prepareStatement(
            "INSERT INTO chat.messages " +
                    "(author, chatroom, message, date) " +
                    "VALUES (?, ?, ?, ?) RETURNING *"
    );

    insertQuery.setString(1, message.getAuthor().getLogin());
    insertQuery.setString(2, message.getChatroom().getRoomName());
    insertQuery.setString(3, message.getMessage());
    insertQuery.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));

    ResultSet ids = insertQuery.executeQuery();
    if (ids.next()) { message.setId(ids.getLong(1)); }

    ids.close();
    insertQuery.close();
    connection.close();
  }

  @Override
  public void update(Message message) throws NotSavedSubEntityException, SQLException {
    Connection connection = ds_.getConnection();

    if (!message.isExist(connection)) {
      throw new NotSavedSubEntityException();
    }

    PreparedStatement updateQuery = connection.prepareStatement(
            "UPDATE chat.messages " +
                    "SET author = ?, " +
                    "chatroom = ?, " +
                    "message = ?, " +
                    "date = ? " +
                    "WHERE id = ?;"
    );

    String new_author = (message.getAuthor() == null) ?
            null : message.getAuthor().getLogin();

    String new_room = (message.getChatroom() == null) ?
            null : message.getChatroom().getRoomName();

    Timestamp dateTime = (message.getDateTime() == null) ?
            null : Timestamp.valueOf(message.getDateTime());

    updateQuery.setString(1, new_author);
    updateQuery.setString(2, new_room);
    updateQuery.setString(3, message.getMessage());
    updateQuery.setTimestamp(4, dateTime);
    updateQuery.setLong(5, message.getId());
    updateQuery.execute();

    updateQuery.close();
    connection.close();
  }

  private User findUser(Connection connection, String username) throws SQLException {
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

  private Chatroom findChatroom(Connection connection, String roomname) throws SQLException {
    String sqlQuery = "SELECT * FROM chat.chatrooms WHERE name = ?";

    PreparedStatement statement = connection.prepareStatement(sqlQuery);
    statement.setString(1, roomname);

    ResultSet queryResult = statement.executeQuery();
    queryResult.next();

    Long id = queryResult.getLong("id");
    String name = queryResult.getString("name");
    User owner = findUser(connection, queryResult.getString("owner"));

    return new Chatroom(id, name, owner);
  }

  DataSource ds_;
}