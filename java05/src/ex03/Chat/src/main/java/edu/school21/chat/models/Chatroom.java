package edu.school21.chat.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.lang.Object;
import java.util.Objects;

public class Chatroom {
  public Chatroom(Long id, String roomName, User owner) {
    id_ = id;
    roomName_ = roomName;
    owner_ = owner;
  }
  public Chatroom(Long id, String roomName, User owner, List<Message> roomMessages) {
    id_ = id;
    roomName_ = roomName;
    owner_ = owner;
    roomMessages_ = roomMessages;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Chatroom chatroom = (Chatroom) obj;

    if (this.id_ == chatroom.id_
            && roomName_.equals(chatroom.roomName_)
            && owner_.equals(chatroom.owner_)
            && roomMessages_.equals(chatroom.roomMessages_)
    ) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_, roomName_, owner_, roomMessages_);
  }

  @Override
  public String toString() {
    String retval =
            String.format("Chatroom={id=%d, name=\"%s\", creator=\"%s\", messages=%s}",
            id_, roomName_, owner_.getLogin(), roomMessages_);

    return retval;
  }

  public boolean isExist(Connection connection) throws SQLException {
    PreparedStatement check = connection.prepareStatement(
            "SELECT * FROM chat.chatrooms WHERE id = ? AND name = ?"
    );
    check.setLong(1, id_);
    check.setString(2, roomName_);

    return check.executeQuery().next();
  }

  public Long getId() { return id_; }

  public String getRoomName() { return roomName_; }

  public User getOwner() { return owner_; }

  public List<Message> getRoomMessages() { return roomMessages_; }

  private Long id_;
  private String roomName_;
  private User owner_;
  private List<Message> roomMessages_;
}