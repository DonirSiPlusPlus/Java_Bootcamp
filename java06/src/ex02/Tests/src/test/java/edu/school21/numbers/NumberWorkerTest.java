package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
  @ParameterizedTest
  @ValueSource(ints = {3, 17, 157, 911, 7177, 39916801})
  public void isPrimeForPrimes(int number) {
    Assertions.assertTrue(new NumberWorker().isPrime(number));
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 18, 121, 910, 7178, 39916802})
  public void isPrimeForNotPrimes(int number) {
    Assertions.assertFalse(() -> new NumberWorker().isPrime(number));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 0, -100, -5039, -999999})
  public void isPrimeForIncorrectNumbers(int number) {
    Assertions
            .assertThrows(RuntimeException.class,
                    () -> new NumberWorker().isPrime(number), "Illegal number");
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
  public void sumDigitsTest(int number, int sum) {
    Assertions.assertEquals(sum, new NumberWorker().digitsSum(number));
  }
}

