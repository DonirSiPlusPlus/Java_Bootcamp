import java.util.List;
import java.util.ArrayList;

public class Program {
  public static void main(String[] args) {
    int arraySize = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
    int threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf("=") + 1));

    if (arraySize > 2000000 || threadsCount > arraySize) {
      return;
    }

    List<Integer> arr = new ArrayList<>(arraySize);
    int sum = 0;
    for (int i = 0; i < arraySize; ++i) {
      arr.add((int)(Math.random() * 1000));
      sum += arr.get(i);
    }

    int range = Math.ceilDiv(arraySize, threadsCount);
    List<MyThread> runnables = new ArrayList<>();
    int index = 0;

    for (int i = 0; index < arr.size(); ++i) {
      runnables.add(new MyThread(i + 1, arr, index, range));
      index += range;
    }

    System.out.println("Sum: " + sum);

    try {
      for (MyThread i : runnables) {
        Thread thread = new Thread(i);
        thread.start();
        thread.join();
      }
    } catch (InterruptedException ex) {
      System.out.println("InterruptedException");
    }

    System.out.println("Sum by threads: " + runnables.get(0).getTotalSum());
  }
}
