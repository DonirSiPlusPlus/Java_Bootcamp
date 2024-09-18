package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
  public UsersServiceImpl(UsersRepository userRep) {
    this.userRep = userRep;
  }

  public boolean authenticate(String login, String password) {
    User user = userRep.findByLogin(login);

    if (user.isAuthentication()) {
      throw new AlreadyAuthenticatedException();
    }

    if (password.equals(user.getPassword())
            && login.equals(user.getLogin())) {
      user.setAuthentication(true);
      return true;
    }

    user.setAuthentication(false);
    return false;
  }

  private UsersRepository userRep;
}
