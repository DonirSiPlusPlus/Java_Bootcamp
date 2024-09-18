package edu.school21.chat.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.lang.Object;
import java.util.Objects;

public class User {
  public User(Long id, String login, String password) {
    id_ = id;
    login_ = login;
    password_ = password;
  }
  public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> usersRooms) {
    id_ = id;
    login_ = login;
    password_ = password;
    createdRooms_ = createdRooms;
    usersRooms_ = usersRooms;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    User user = (User) obj;

    if (id_ == user.id_
            && login_.equals(user.login_)
            && password_.equals(user.password_)
            && createdRooms_.equals(user.createdRooms_)
            && usersRooms_.equals(user.usersRooms_)
      ) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_, login_, password_, createdRooms_, usersRooms_);
  }

  @Override
  public String toString() {
    String retval =
            String.format("User={id=%d, login=\"%s\", password=\"%s\", createdRooms=%s, usersRooms=%s}",
            id_, login_, password_, createdRooms_, usersRooms_);

    return retval;
  }

  public boolean isExist (Connection connection) throws SQLException {
    String checkString = "SELECT * FROM chat.users WHERE id = ? AND login = ?";

    PreparedStatement check = connection.prepareStatement(checkString);

    check.setLong(1, id_);
    check.setString(2, login_);

    return check.executeQuery().next();
  }

  public Long getId() { return id_; }

  public String getLogin() { return login_; }

  public String getPassword() { return password_; }

  public List<Chatroom> getCreatedRooms() { return createdRooms_; }

  public List<Chatroom> getUsersRooms() { return usersRooms_; }

  private Long id_;
  private String login_;
  private String password_;
  private List<Chatroom> createdRooms_;
  private List<Chatroom> usersRooms_;
}