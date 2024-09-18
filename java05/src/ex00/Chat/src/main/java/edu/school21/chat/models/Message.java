package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.lang.Object;
import java.util.Objects;

public class Message {
  public Message(int id, User author, Chatroom room, String message, LocalDateTime date) {
    id_ = id;
    author_ = author;
    messageRoom_ = room;
    message_ = message;
    dateTime = date;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Message message = (Message) obj;

    if (id_ == message.id_
            && author_.equals(message.author_)
            && messageRoom_.equals(message.messageRoom_)
            && message_.equals(message.message_)
            && dateTime.equals(message.dateTime)
    ) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_, author_, messageRoom_, message_, dateTime);
  }

  @Override
  public String toString() {
    String retval =
            String.format("Message{id='%d', author='%s', messageRoom='%s', message='%s', dateTime='%s'}",
            id_, author_, messageRoom_, message_, dateTime);

    return retval;
  }

  public int getId_() { return id_; }

  public User getAuthor_() { return author_; }

  public Chatroom getMessageRoom_() { return messageRoom_; }

  public String getMessage_() { return message_; }

  public LocalDateTime getDateTime() { return dateTime; }

  private int id_;
  private User author_;
  private Chatroom messageRoom_;
  private String message_;
  private LocalDateTime dateTime;
}