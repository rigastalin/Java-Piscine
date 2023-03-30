package ex02;

import java.util.Scanner;

public class Project {
    public static void main(String[] args) {
        int coffeeCounter = 0;
        int sumDigit = 0;
        int num;

        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();

        while (num != 42) {
            sumDigit = digitSum(num);
            if (digitPrime(sumDigit)) {
                coffeeCounter++;
            }
            num = scanner.nextInt();
        }
        System.out.println("Count of coffee-request - " + coffeeCounter);
        scanner.close();
    }

    public static int digitSum(int num) {
        int res = 0;
        res += num % 10;
        while (num > 10) {
            num /= 10;
            res += num % 10;
        }
        return res;
    }

    public static boolean digitPrime(int num) {
        int i = 2;
        boolean prime = true;
        while (i * i <= num) {
            if (num % i == 0) {
                prime = false;
                break;
            }
            i++;
        }
        return prime;
    }
}
