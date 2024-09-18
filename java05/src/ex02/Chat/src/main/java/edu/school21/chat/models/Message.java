package edu.school21.chat.models;

import java.lang.Object;
import java.util.Objects;
import java.time.LocalDateTime;

public class Message {
  public Message(Long id, User author, Chatroom room, String message, LocalDateTime date) {
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
    return "Message : {" +
            " \n  id=" + id_ +
            ",\n  " + author_ +
            ",\n  " + messageRoom_ +
            ",\n  text=\"" + message_ + '\"' +
            ",\n  dateTime=" + dateTime +
            "\n}";
  }

  public Long getId() { return id_; }

  public User getAuthor() { return author_; }

  public Chatroom getChatroom() { return messageRoom_; }

  public String getMessage() { return message_; }

  public LocalDateTime getDateTime() { return dateTime; }

  public void setId(Long id) { id_ = id; }

  private Long id_;
  private User author_;
  private Chatroom messageRoom_;
  private String message_;
  private LocalDateTime dateTime;
}