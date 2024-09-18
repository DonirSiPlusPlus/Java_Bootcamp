public class UsersArrayList implements UsersList {
  UsersArrayList() {
    userArrayList = new User[10];
    size = 10;
    curr_index = 0;
  }

  public void addUser(User user) {
    if (userArrayList[size - 1] != null) {
      increaseArray();
    }

    userArrayList[curr_index++] = user;
  }

  public User getUser(int id) throws UserNotFoundException {
    for (int i = 0; userArrayList[i] != null && i < size; ++i) {
      if (userArrayList[i].getId() == id) {
        return userArrayList[i];
      }
    }

    throw new UserNotFoundException();
  }

  public User findUser(int index) throws UserNotFoundException {
    if (index >= size) {
      throw new UserNotFoundException();
    }

    return userArrayList[index];
  }

  public int getNumOfUsers() {
    return curr_index;
  }

  private void increaseArray() {
    User[] newArray = new User[size * 2];

    for (int i = 0; i < size; ++i) {
      newArray[i] = userArrayList[i];
    }

    size *= 2;
    userArrayList = newArray;
  }

  private User[] userArrayList;
  private int size;
  private int curr_index;
}