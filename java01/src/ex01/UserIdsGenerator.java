public class UserIdsGenerator {

  public int generateId() { return id_++; }

  public static UserIdsGenerator getInstance() {
    if (instance_ == null) {
      instance_ = new UserIdsGenerator();
    }

    return instance_;
  }

  public int getId() { return id_; }

  private static UserIdsGenerator instance_ = null;
  private static int id_ = 0;
}