public interface UsersList {

  public void addUser(User user);

  public User getUser(int id) throws UserNotFoundException;

  public User findUser(int index) throws UserNotFoundException;

  public int getNumOfUsers();
}
