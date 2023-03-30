package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        final int MAX_CODE = 65535;

        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        int[] charArr = new int[MAX_CODE];
        char[] strArr = inputStr.toCharArray();

        for (int i = 0; i< inputStr.length(); i++) {
            charArr[strArr[i]]++;
        }

        char[] resList = new char[10];
        int[] countCharList = new int[10];
        char maxChar = ' ';
        int maxCount = 0;
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < MAX_CODE; j++) {
                if (charArr[j] > maxCount) {
                    maxCount = charArr[j];
                    maxChar = (char)j;
                    index = j;
                }
            }
            countCharList[i] = charArr[index];
            resList[i] = maxChar;
            charArr[index] = 0;
            maxChar = ' ';
            maxCount = 0;
        }
        printRes(resList, countCharList);
        scanner.close();
    }

    public static void printRes(char[] resList, int[] countCharList) {
        int maxCount = countCharList[0];

        System.out.println();

        for (int i = 0; i < 10; i++) {
            if (countCharList[i] == maxCount) {
                System.out.print(countCharList[i] + "\t");
            }
        }

        System.out.println();

        for (int i = 10; i > 0; --i) {
            for (int j = 0; j < 10; ++j) {
                if (maxCount > 0) {
                    if (countCharList[j] * 10 / maxCount >= i)
                        System.out.print("#\t");
                    if (countCharList[j] * 10 / maxCount == i - 1) {
                        if (countCharList[j] != 0) {
                            System.out.print(countCharList[j] + "\t");
                        }
                    }
                } else {
                    System.err.print("Wrong input");
                    System.exit(0);
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(resList[i] + "\t");
        }
    }
}
