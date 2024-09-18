import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    int min_grades = 0;
    int pow = 1;
    int weeksCount = 0;

    String input = sc.nextLine();

    while (!input.equals("42") && ++weeksCount < 19) {
      if (!input.equals("Week " + weeksCount)) {
        System.err.println("Illegal Argument");
        sc.close();
        System.exit(-1);
      }

      min_grades += checkGrades(sc2) * pow;
      pow *= 10;
      input = sc.nextLine();
    }

    sc2.close();
    sc.close();
    printStat(weeksCount, min_grades);
  }

  private static int checkGrades(Scanner sc2) {
    int min = 9;

    for (int i = 0; i < 5; ++i) {
      int grade = sc2.nextInt();
      min = (grade < min) ? grade : min;

      if ((grade > 9) || (grade < 1)) {
        System.err.println("Illegal Argument");
        sc2.close();
        System.exit(-1);
      }
    }

    return min;
  }

  private static void printStat(int weeksCount, int min_grades) {
    for (int i = 1; i <= weeksCount; ++i) {
      System.out.print("Week " + i + " ");

      for (int j = 0; j < min_grades % 10; ++j) {
        System.out.print("=");
      }

      System.out.println(">");
      min_grades /= 10;
    }
  }
}
