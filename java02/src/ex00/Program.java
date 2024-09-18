import java.util.Scanner;
import java.io.*;
import java.util.Map;

public class Program {
  public static void main(String[] args) {
    try {
      Signatures signatures = new Signatures();
      signatures.ReadSignatures("signatures.txt");

      File result = new File("result.txt");
      if (result.exists()) {
        result.delete();
      }
      result.createNewFile();

      Scanner sc = new Scanner(System.in);
      String input = sc.nextLine();

      while (!input.equals("42")) {
        signatures.checkSignatureFile(input);
        input = sc.nextLine();
      }

      sc.close();
    } catch(FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}