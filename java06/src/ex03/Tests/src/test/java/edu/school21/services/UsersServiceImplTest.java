package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {
  @Test
  public void isAllCorrect() {
    UsersRepository usersRep = Mockito.mock(UsersRepository.class);
    User user = new User("admin", "admin", 1L);

    Mockito.when(usersRep.findByLogin(user.getLogin())).thenReturn(user);

    UsersServiceImpl usi = new UsersServiceImpl(usersRep);
    Mockito.verify(usersRep, Mockito.never()).update(user);
    Assertions.assertTrue(usi.authenticate("admin", "admin"));
  }

  @Test
  public void isAlreadyAuthenticated() {
    UsersRepository usersRep = Mockito.mock(UsersRepository.class);
    User user = new User("admin", "admin", 1L);
    user.setAuthentication(true);

    Mockito.when(usersRep.findByLogin(user.getLogin())).thenReturn(user);

    UsersServiceImpl usi = new UsersServiceImpl(usersRep);

    Assertions.assertThrows(AlreadyAuthenticatedException.class,
            () -> usi.authenticate("admin", "admin"));
  }

  @Test
  public void passwordIsIncorrect() {
    UsersRepository usersRepMock = Mockito.mock(UsersRepository.class);
    User user = new User("admin", "admin", 1L);

    Mockito.when(usersRepMock.findByLogin(user.getLogin())).thenReturn(user);

    UsersServiceImpl usi = new UsersServiceImpl(usersRepMock);

    Assertions.assertFalse(usi.authenticate("admin", "123"));
  }

  @Test
  public void loginIsIncorrect() {
    UsersRepository usersRepMock = Mockito.mock(UsersRepository.class);

    Mockito.when(usersRepMock.findByLogin("invalid"))
            .thenThrow(new EntityNotFoundException());

    UsersServiceImpl usi = new UsersServiceImpl(usersRepMock);

    Assertions.assertThrows(EntityNotFoundException.class,
            () -> usi.authenticate("invalid", "123"));
  }
}
