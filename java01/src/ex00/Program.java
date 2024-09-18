public class Program {
  public static void main(String[] args) {
    User oleg = new User(1, "Oleg", 350);
    User ivan = new User(2, "Ivan", 150);

    System.out.println(oleg.getId() + ") " + oleg.getName() + "'s Balance: " + oleg.getBalance());
    System.out.println(ivan.getId() + ") " + ivan.getName() + "'s Balance: " + ivan.getBalance());


    Transaction tr1 = new Transaction(ivan, oleg, 50);
    Transaction tr2 = new Transaction(ivan, oleg, -50);

    System.out.println("\nRecepient: " + tr1.getRecipient().getName());
    System.out.println("Sender: " + tr1.getSender().getName());
    System.out.println("Transfer amount: " + tr1.getTransfAmount() + "\n");

    System.out.println(oleg.getId() + ") " + oleg.getName() + "'s Balance: " + oleg.getBalance());
    System.out.println(ivan.getId() + ") " + ivan.getName() + "'s Balance: " + ivan.getBalance());
  }
}