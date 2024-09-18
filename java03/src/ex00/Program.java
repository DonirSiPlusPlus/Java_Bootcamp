public class Program {
  public static void main(String[] args) {
    if (args.length != 1 || !args[0].startsWith("--count=")) {
      return;
    }

    try {
      int count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));

      MyThread th1 = new MyThread("Egg", count);
      MyThread th2 = new MyThread("Hen", count);

      th2.start();
      th1.start();

      th1.join();
      th2.join();

      for (int i = 0; i < count; ++i) {
        System.out.println("Human");
      }
    } catch (InterruptedException ex) {
      System.out.println("InterruptedException");
    } catch (NumberFormatException ex) {
      System.out.println("NumberFormatException");
    }
  }
}