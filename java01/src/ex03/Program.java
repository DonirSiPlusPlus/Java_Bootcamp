public class Program {
  public static void main(String[] args) {
    User ivan = new User("Ivan", 1000);
    User petr = new User("Petr", 1000);
    User alex = new User("Alex", 1000);
    User ilia = new User("Ilia", 1000);
    User igor = new User("Igor", 1000);

    System.out.println("Ivan's balance: " + ivan.getBalance());
    System.out.println("Igor's balance: " + igor.getBalance());

    Transaction tr1 = new Transaction(petr, ivan, 500);
    Transaction tr2 = new Transaction(alex, petr, 500);
    Transaction tr3 = new Transaction(ilia, alex, 500);
    Transaction tr4 = new Transaction(igor, ilia, 500);

    ivan.addTransaction(tr1);
    System.out.println("\nIvan's id transaction: " + ivan.getTransactions().toArray()[0].getId());
    System.out.println("Ivan's balance: " + ivan.getBalance());
    System.out.println("Igor's balance: " + igor.getBalance() + "\n");

    try {
      System.out.println("Ivan's count of transactions: " + ivan.getTransactions().toArray().length);
      System.out.println("Deleting of transaction");
      ivan.getTransactions().deleteTransaction(tr1.getId());
      System.out.println("Ivan's count of transactions: " + ivan.getTransactions().toArray().length);
    } catch(TransactionNotFoundException e) {
      e.printStackTrace();
    }
  }
}