public class Program {
  public static void main(String[] args) {
    User ivan = new User("Ivan", 100);
    User petr = new User("Petr", 200);
    User alex = new User("Alex", 300);
    User ilia = new User("Ilia", 400);
    User igor = new User("Igor", 500);

    System.out.println(ivan.getId() + ") " + ivan.getName() + "'s Balance: " + ivan.getBalance());
    System.out.println(petr.getId() + ") " + ivan.getName() + "'s Balance: " + petr.getBalance());
    System.out.println(alex.getId() + ") " + ivan.getName() + "'s Balance: " + alex.getBalance());
    System.out.println(ilia.getId() + ") " + ivan.getName() + "'s Balance: " + ilia.getBalance());
    System.out.println(igor.getId() + ") " + ivan.getName() + "'s Balance: " + igor.getBalance());

  }
}