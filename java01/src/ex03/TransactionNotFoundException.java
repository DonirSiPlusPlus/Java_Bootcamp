public class TransactionNotFoundException extends Exception {
  TransactionNotFoundException() {
    super("User not found");
  }
}