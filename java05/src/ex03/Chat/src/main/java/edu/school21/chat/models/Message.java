package edu.school21.chat.models;

import edu.school21.chat.exceptions.NotSavedSubEntityException;

import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.time.LocalDateTime;

public class Message {
  public Message(Long id, User author, Chatroom room, String message, LocalDateTime date) {
    id_ = id;
    author_ = author;
    messageRoom_ = room;
    message_ = message;
    dateTime_ = date;
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
            && dateTime_.equals(message.dateTime_)
    ) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_, author_, messageRoom_, message_, dateTime_);
  }

  @Override
  public String toString() {
    return "Message : {" +
            " \n  id=" + id_ +
            ",\n  " + author_ +
            ",\n  " + messageRoom_ +
            ",\n  text=\"" + message_ + '\"' +
            ",\n  dateTime=" + dateTime_ +
            "\n}";
  }

  public boolean isExist(Connection connection) throws SQLException {
    PreparedStatement check = connection.prepareStatement(
            "SELECT * FROM chat.messages WHERE id = ?"
    );
    check.setLong(1, id_);

    return check.executeQuery().next();
  }

  public Long getId() { return id_; }

  public User getAuthor() { return author_; }

  public Chatroom getChatroom() { return messageRoom_; }

  public String getMessage() { return message_; }

  public LocalDateTime getDateTime() { return dateTime_; }

  public void setId(Long id) throws NotSavedSubEntityException {
    if (id_ != null) {
      throw new NotSavedSubEntityException();
    }

    id_ = id;
  }

  public void setAuthor(User author) { author_ = author; }

  public void setMessageRoom(Chatroom room) { messageRoom_ = room; }

  public void setMessage(String message) { message_ = message; }

  public void setDateTime(LocalDateTime dateTime) { dateTime_ = dateTime; }

  private Long id_;
  private User author_;
  private Chatroom messageRoom_;
  private String message_;
  private LocalDateTime dateTime_;
}