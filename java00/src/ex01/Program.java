import java.util.Scanner;
public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int numb = sc.nextInt();
    boolean flag = true;

    if (numb < 2) {
      System.err.println("Illegal Argument");
      sc.close();
      System.exit(-1);
    } else if (numb == 2) {
      System.out.println(flag + " " + 1);
    } else {
      int count_steps = 0;

      for (int i = 2; i <= my_sqrt(numb); ++i) {
        ++count_steps;
        if (numb % i == 0) {
          flag = false;
          break;
        }
      }

      System.out.println(flag + " " + count_steps);
    }
    sc.close();
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
