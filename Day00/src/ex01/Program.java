package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int num;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
            if (!subZero(num)) {
                checkNum(num);
            }
        }
        scanner.close();
    }

    public static boolean subZero(int num) {
        if (num <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        return false;
    }

    public static void checkNum(int num) {
        int i = 2;
        boolean prime = true;
        while (i * i <= num) {
            if (num % i == 0) {
                prime = false;
                break;
            }
            ++i;
        }
        System.out.println(prime + " " + (i - 1));
    }
}
