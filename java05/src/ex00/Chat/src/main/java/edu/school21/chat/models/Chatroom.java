package edu.school21.chat.models;

import java.util.List;
import java.lang.Object;
import java.util.Objects;

public class Chatroom {
  public Chatroom(int id, String roomName, User owner) {
    id_ = id;
    roomName_ = roomName;
    owner_ = owner;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Chatroom chatroom = (Chatroom) obj;

    if (id_ == chatroom.id_
            && roomName_.equals(chatroom.roomName_)
            && owner_.equals(chatroom.owner_)
            && roomMessages.equals(chatroom.roomMessages)
    ) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_, roomName_, owner_, roomMessages);
  }

  @Override
  public String toString() {
    String retval =
            String.format("Chatroom{id='%d', roomName='%s', owner='%s', roomMessages='%s'}",
            id_, roomName_, owner_, roomMessages);

    return retval;
  }

  public int getId_() { return id_; }

  public String getRoomName_() { return roomName_; }

  public User getOwner_() { return owner_; }

  public List<Message> getRoomMessages() { return roomMessages; }

  private int id_;
  private String roomName_;
  private User owner_;
  private List<Message> roomMessages;
}