public class Program {
  public static void main(String[] args) {
    User ivan = new User("Ivan", 100);
    User petr = new User("Petr", 200);
    User alex = new User("Alex", 300);
    User ilia = new User("Ilia", 400);
    User igor = new User("Igor", 500);
    User ivan2 = new User("Ivan2", 100);
    User petr2 = new User("Petr2", 200);
    User alex2 = new User("Alex2", 300);
    User ilia2 = new User("Ilia2", 400);
    User igor2 = new User("Igor2", 500);
    User ivan3 = new User("Ivan3", 100);

    UsersArrayList arr = new UsersArrayList();

    arr.addUser(ivan);
    arr.addUser(petr);
    arr.addUser(alex);
    arr.addUser(ilia);
    arr.addUser(igor);
    arr.addUser(ivan2);
    arr.addUser(petr2);
    arr.addUser(alex2);
    arr.addUser(ilia2);
    arr.addUser(igor2);
    arr.addUser(ivan3);

    try {
      System.out.println("Пользователь с id 3: " + arr.getUser(3).getName());
      System.out.println("Пользователь под индексом 4: " + arr.findUser(4).getName());
      System.out.println("Количество пользователей: " + arr.getNumOfUsers());
      System.out.println("Бросок исключения");
      System.out.print(arr.getUser(15));
    } catch(UserNotFoundException e) {
      e.printStackTrace();
    }

  }
}