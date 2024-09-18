import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int count = 0;
    int number = sc.nextInt();

    while (number != 42) {
      if (CheckSimple(number) == true) {
        ++count;
      }
      number = sc.nextInt();
    }

    System.out.println("Count of coffee-request – " + count);
    sc.close();
  }

  private static boolean CheckSimple(int number) {
    int sum = 0;

    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }

    for (int i = 2; i <= my_sqrt(sum); ++i) {
      if (sum % i == 0) {
        return false;
      }
    }

    return true;
  }

  private static int my_sqrt(int value) {
    int root = 1;
    int sq = 0;

    while (sq <= value) {
      ++root;
      sq = root * root;
    }

    return root;
  }
}