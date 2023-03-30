package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    NumberWorker numberWorker;

    @BeforeEach
    void setNumberWorker() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
                        83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173})
    void isPrimeForPrimes(int num) {
        Assertions.assertTrue(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10, 9, 12, 14, 15, 16, 18, 20, 21})
    void isPrimeForNotPrimes(int num) {
        Assertions.assertFalse(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-4, -6, -8, -10, -9, -12, -14, -15, -16, -18, -20, -21, 0})
    void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void CheckDigitSum(int x, int y) {
        Assertions.assertEquals(numberWorker.digitSum(x), y);
    }
}
