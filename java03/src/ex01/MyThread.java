public class MyThread extends Thread {
  MyThread(String str, int count, SynchPrint print) {
    this.str_ = str;
    this.count_ = count;
    this.printer_ = print;
  }

  public void run() {
    for (int i = 0; i < count_; ++i) {
      printer_.print(str_);
    }
  }

  private String str_;
  private int count_;
  private SynchPrint printer_;
}
