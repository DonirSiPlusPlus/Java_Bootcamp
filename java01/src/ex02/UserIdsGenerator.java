public class UserIdsGenerator {

  public int generateId() { return id_++; }

  public static UserIdsGenerator getInstance() {
    if (instance_ == null) {
      return new UserIdsGenerator();
    }

    return instance_;
  }

  public int GetId() { return id_; }

  private static UserIdsGenerator instance_ = null;
  private static int id_ = 0;
}