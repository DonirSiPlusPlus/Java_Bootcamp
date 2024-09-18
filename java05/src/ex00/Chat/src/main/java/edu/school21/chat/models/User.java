package edu.school21.chat.models;

import java.util.List;
import java.lang.Object;
import java.util.Objects;

public class User {
  public User(int id, String login, String password) {
    id_ = id;
    login_ = login;
    password_ = password;
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
            String.format("User{id='%d', login='%s', password='%s', createdRooms='%s', usersRooms='%s'}",
            id_, login_, password_, createdRooms_, usersRooms_);

    return retval;
  }

  public int getId_() { return id_; }

  public String getLogin_() { return login_; }

  public String getPassword_() { return password_; }

  public List<Chatroom> getCreatedRooms_() { return createdRooms_; }

  public List<Chatroom> getUsersRooms_() { return usersRooms_; }

  private int id_;
  private String login_;
  private String password_;
  private List<Chatroom> createdRooms_;
  private List<Chatroom> usersRooms_;
}