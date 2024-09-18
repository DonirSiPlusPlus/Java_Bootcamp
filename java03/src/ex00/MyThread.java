public class MyThread extends Thread {
  MyThread(String output, int count) {
    this.output_ = output;
    this.count_ = count;
  }

  public void run() {
    for (int i = 0; i < count_; ++i) {
      System.out.println(output_);
      try {
        sleep(10);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private String output_;
  private int count_;
}