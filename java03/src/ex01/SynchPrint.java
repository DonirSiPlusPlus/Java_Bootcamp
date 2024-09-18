public class SynchPrint {
  public synchronized void print(String str) {
    if (str.equals(currStr)) {
      try {
        wait();
      } catch (InterruptedException ex) {
        System.out.println("InterruptedException");
      }
    }

    currStr = str;
    System.out.println(str);
    notify();
  }

  private String currStr = "Hen";
}