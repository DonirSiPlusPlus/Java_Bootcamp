package edu.school21.models;

import java.util.Objects;

public class User {
  public User(String login, String password, Long id) {
    this.login = login;
    this.password = password;
    this.id = id;
    authentication = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return authentication == user.authentication && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, id, authentication);
  }

  @Override
  public String toString() {
    return "User{" +
            "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", id=" + id +
            ", authentication=" + authentication +
            '}';
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public Long getId() {
    return id;
  }

  public boolean isAuthentication() {
    return authentication;
  }

  public void setAuthentication(boolean authentication) {
    this.authentication = authentication;
  }

  private String login;
  private String password;
  private Long id;
  private boolean authentication;
}