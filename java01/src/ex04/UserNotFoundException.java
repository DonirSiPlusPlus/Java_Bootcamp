public class UserNotFoundException extends Exception {
  UserNotFoundException() {
    super("User not found exception");
  }
}