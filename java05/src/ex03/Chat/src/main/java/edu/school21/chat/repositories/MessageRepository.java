package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.exceptions.NotSavedSubEntityException;

import java.sql.SQLException;
import java.util.Optional;

public interface MessageRepository {
  Optional<Message> findById(Long id) throws SQLException;
  void save(Message message) throws NotSavedSubEntityException, SQLException;

  void update(Message message) throws NotSavedSubEntityException, SQLException;
}