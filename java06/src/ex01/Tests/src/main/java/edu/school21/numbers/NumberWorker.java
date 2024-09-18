package edu.school21.numbers;

public class NumberWorker {
  public boolean isPrime(int number) {
    if (number < 2) {
      throw new IllegalNumberException();
    }

    long root_number = Math.round(Math.sqrt(number));

    for (int i = 2; i <= root_number; ++i) {
      if (number % i == 0) {
        return false;
      }
    }

    return true;
  }

  public int digitsSum(int number) {
    number = Math.abs(number);
    int sum = 0;

    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }

    return sum;
  }

  static class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
      super("Illegal number");
    }
  }
}