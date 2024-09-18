import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.StringTokenizer;

public class Program {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Must to be 2 args");
      return;
    }

    try {
      Map<String, List<Integer>> dictionary = new TreeMap<>();
      List<String> text1 = readFile(args[0], dictionary);
      List<String> text2 = readFile(args[1], dictionary);

      if (text1.isEmpty() || text2.isEmpty()) {
        System.out.println("File(s) is empty");
        return;
      }
      if (new File(args[0]).length() > 10e7 || new File(args[1]).length() > 10e7) {
        System.out.println("File too big");
        return;
      }

      createDictionary(dictionary);

      int numerator = 0;
      double A = 0;
      double B = 0;

      for (Map.Entry<String, List<Integer>> entry : dictionary.entrySet()) {
        int count1 = 0;
        int count2 = 0;

        dictionary.put(entry.getKey(), new ArrayList<>(2));
        entry.getValue().add(0, 0);
        entry.getValue().add(1, 0);

        for (int i = 0; i < Math.max(text1.size(), text2.size()); ++i) {
          if (i < text1.size()) {
            if (entry.getKey().equals(text1.get(i))) {
              entry.getValue().set(0, ++count1);
            }
          }
          if (i < text2.size()) {
            if (entry.getKey().equals(text2.get(i))) {
              entry.getValue().set(1, ++count2);
            }
          }
        }
        numerator += entry.getValue().get(0) * entry.getValue().get(1);
        A += Math.pow(entry.getValue().get(0), 2);
        B += Math.pow(entry.getValue().get(1), 2);
      }

      double denominator = Math.floor(Math.sqrt(A) * Math.sqrt(B) * 1e6) / 1e6;
      double similarity = numerator / denominator;
      System.out.printf("Similarity = %.2f\n", Math.floor(similarity * 100) / 100);
    } catch(FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }

   public static List<String> readFile(String file_name, Map<String, List<Integer>> dictionary) throws IOException, FileNotFoundException {
     List<String> text = new ArrayList<>();
     FileReader fr = new FileReader(file_name);
     BufferedReader reader = new BufferedReader(fr);
     String line = reader.readLine();

     while (line != null) {
       StringTokenizer tokenizer = new StringTokenizer(line, " ");
       while (tokenizer.hasMoreTokens()) {
         String token = tokenizer.nextToken();
         text.add(token);
         dictionary.put(token, new ArrayList<>());
       }
       line = reader.readLine();;
     }

     return text;
   }

   public static void createDictionary(Map<String, List<Integer>> dictionary) throws IOException {
     File dict_file = new File("dictionary.txt");
     if (dict_file.exists()) {
       dict_file.delete();
     }
     dict_file.createNewFile();

     FileWriter writer = new FileWriter("dictionary.txt");

     for (Map.Entry<String, List<Integer>> entry : dictionary.entrySet()) {
       writer.write(entry.getKey() + " ", 0, entry.getKey().length() + 1);
     }
     writer.close();
   }
}