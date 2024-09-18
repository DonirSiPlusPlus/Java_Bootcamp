import java.util.List;
import java.util.ArrayList;

public class MyThread implements Runnable {
  MyThread(int numb, List<Integer> arr, int i, int range) {
    numb_ = numb;
    arr_ = arr;
    i_ = i;
    range_ = range;
    sum_range = 0;
    total_sum = 0;
  }

  @Override
  public void run() {
    int last_el = i_ + range_;
    int i = i_;

    while (i < arr_.size() && i < last_el) {
      sum_range += arr_.get(i++);
    }

    total_sum += sum_range;
    System.out.println("Thread " + numb_ + ": from " + i_ + " to " + (i - 1) + " sum is " + sum_range);
  }

  public int getTotalSum() {
    return total_sum;
  }

  private int numb_;
  private List<Integer> arr_;
  private int i_;
  private int range_;
  private int sum_range;
  private static int total_sum;
}
