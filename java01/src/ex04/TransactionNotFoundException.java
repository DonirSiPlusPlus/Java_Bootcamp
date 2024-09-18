public class TransactionNotFoundException extends Exception {
  TransactionNotFoundException() {
    super("Transaction not found");
  }
}