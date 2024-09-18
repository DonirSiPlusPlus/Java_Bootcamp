import java.util.UUID;

public class Program {
  public static void main(String[] args) {
    User ivan = new User("Ivan", 1000);
    User petr = new User("Petr", 2000);
    User alex = new User("Alex", 3000);
    User ilia = new User("Ilia", 4000);
    User igor = new User("Igor", 5000);

    TransactionsService facade = new TransactionsService();

    facade.addUser(ivan);
    facade.addUser(petr);
    facade.addUser(alex);
    facade.addUser(ilia);
    facade.addUser(igor);

    try {
      System.out.println("Ivan's balance: " + facade.getBalance(0));
      System.out.println("Petr's balance: " + facade.getBalance(1));
      System.out.println("Alex's balance: " + facade.getBalance(2));
      System.out.println("Ilia's balance: " + facade.getBalance(3));
      System.out.println("Igor's balance: " + facade.getBalance(4));

      System.out.println("\n400 From Ivan to Igor\n");
      facade.makeTransaction(1, 0, 400);
      facade.makeTransaction(2, 1, 400);
      facade.makeTransaction(3, 2, 400);
      facade.makeTransaction(4, 3, 400);
      System.out.println("Ivan's balance: " + facade.getBalance(0));
      System.out.println("Petr's balance: " + facade.getBalance(1));
      System.out.println("Alex's balance: " + facade.getBalance(2));
      System.out.println("Ilia's balance: " + facade.getBalance(3));
      System.out.println("Igor's balance: " + facade.getBalance(4));

      System.out.println("\nIvan's count of transactions: " + facade.getTransArray(ivan).length);
      System.out.println("Deleting of transaction");
      facade.deleteTransaction(facade.getTransArray(ivan)[0].getId(), 0);
      System.out.println("Ivan's count of transactions: " + facade.getTransArray(ivan).length);

      System.out.println("\nPetr's count of transactions: "+ facade.getTransArray(petr).length);
      System.out.println("Petr's transaction №1");
      System.out.print(facade.getTransArray(petr)[0].getSender().getName() + " -> ");
      System.out.print(facade.getTransArray(petr)[0].getRecipient().getName() + ", ");
      System.out.print(facade.getTransArray(petr)[0].getTransfAmount() + ", ");
      System.out.print(facade.getTransArray(petr)[0].getTransfType() + ", ");
      System.out.println(facade.getTransArray(petr)[0].getId());
      System.out.println("Petr's transaction №2");
      System.out.print(facade.getTransArray(petr)[1].getSender().getName() + " -> ");
      System.out.print(facade.getTransArray(petr)[1].getRecipient().getName() + ", ");
      System.out.print(facade.getTransArray(petr)[1].getTransfAmount() + ", ");
      System.out.print(facade.getTransArray(petr)[1].getTransfType() + ", ");
      System.out.println(facade.getTransArray(petr)[1].getId());

      Transaction[] invalid = facade.checkValidArray();
      System.out.println("\nCount of invalid transactions: " + invalid.length);

      System.out.println("\nБросок исключения отсутствия пользователя");
      facade.getBalance(23);
    } catch(UserNotFoundException e) {
      System.out.println(e);
    } catch (IllegalTransactionException e) {
      System.out.println(e);
    } catch(TransactionNotFoundException e) {
      System.out.println(e);
    }

    try {
      System.out.println("\nБросок исключения невозможной транзакции");
      facade.makeTransaction(1, 0, 9999999);
    } catch (IllegalTransactionException e) {
      System.out.println(e);
    } catch(UserNotFoundException e) {
      System.out.println(e);
    }

    try {
      UUID id = UUID.randomUUID();
      System.out.println("\nБросок исключения отсутствия транзакции");
      facade.deleteTransaction(id, 0);
    } catch(TransactionNotFoundException e) {
      System.out.println(e);
    } catch(UserNotFoundException e) {
      System.out.println(e);
    }

  }
}