public class Program {
  public static void main(String[] args) {
    if (args.length != 1 || !args[0].startsWith("--count=")) {
      return;
    }

    try {
      int count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
      SynchPrint printer = new SynchPrint();

      MyThread th1 = new MyThread("Egg", count, printer);
      MyThread th2 = new MyThread("Hen", count, printer);

      th1.start();
      th2.start();
    } catch (NumberFormatException ex) {
      System.out.println("NumberFormatException");
    }
  }
}