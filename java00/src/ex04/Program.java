import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String input = sc.nextLine();

    if (input.length() >= 999) {
      System.err.println("Too Many Arguments");
      sc.close();
      System.exit(-1);
    }

    char[] symbols = readSymbols(input.toCharArray());
    int[] counts = symbolsCounting(symbols, input.toCharArray());

    printStat(counts, symbols);
    sc.close();
  }

  private static char[] readSymbols(char[] char_input) {
    String symbols = "" + char_input[0];

    for (int i = 0; i < char_input.length; ++i) {
      for (int j = i + 1; j < char_input.length; ++j) {
        boolean flag = false;
        if (char_input[i] != char_input[j]) {
          for (int c = 0; c < symbols.length(); ++c) {
            if (char_input[j] == symbols.toCharArray()[c]) {
              flag = true;
              break;
            }
          }
          if (flag == true) {
            continue;
          }
          symbols += char_input[j];
        }
      }
    }

    return symbols.toCharArray();
  }

  private static int[] symbolsCounting(char[] symbols, char[] char_input) {
    int[] counts = new int[symbols.length];

    for (char i : char_input) {
      for (int j = 0; j < counts.length; ++j) {
        if (i == symbols[j]) {
          ++counts[j];
        }
      }
    }

    for (int i = 0; i < counts.length; ++i) {
      for (int j = i; j > 0 && counts[j - 1] <= counts[j]; --j) {
        int temp = counts[j];
        counts[j] = counts[j - 1];
        counts[j - 1] = temp;

        if ((counts[j - 1] == counts[j]) && (symbols[j - 1] < symbols[j])) {
          continue;
        }
        char temp2 = symbols[j];
        symbols[j] = symbols[j - 1];
        symbols[j - 1] = temp2;
      }
    }

    return counts;
  }

  private static void printStat(int[] counts, char[] symbols) {
    for (int i = 0; i < counts.length && i < 10; i++) {
      if (i != 0 && counts[i] < counts[i - 1]) {
        float val_of_div = (float)counts[0] / 10;
        float step = counts[i] / val_of_div;
        float prev_step = counts[i - 1] / val_of_div;

        int count_of_line = (int)(prev_step) - (int)(step);
        for (int f = 0; f < count_of_line; ++f) {
          System.out.println();
          for (int j = 0; j < i ; ++j) {
            System.out.print("  # ");
          }
        }
      }

      if (counts[i] < 10) {
        System.out.print("  " + counts[i] + " ");
      } else if (counts[i] < 100) {
        System.out.print(" " + counts[i] + " ");
      } else  {
        System.out.print(counts[i] + " ");
      }
    }
    System.out.println();
    for (int i = 0; i < symbols.length && i < 10; i++) {
      System.out.print("  " + symbols[i] + " ");
    }
  }
}
