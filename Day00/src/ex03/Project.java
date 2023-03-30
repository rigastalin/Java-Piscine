package ex03;

import java.util.Scanner;

public class Project {
    public static void main(String[] args) {
        final int MAX_WEEK = 18;

        int week = 1;
        long grade;
        long grades = 0;

        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.next();

        while(!inputStr.equals("42") && week <= MAX_WEEK) {
            int weekNum = scanner.nextInt();
            if (!inputStr.equals("Week") || weekNum != week) {
                wrongInput();
            }
            grades = gradesFunc(grades, week, scanner);
            scanner.nextLine();
            inputStr = scanner.next();
            week++;
        }

        for (int i = 1; i < week; i++) {
            System.out.print("Week " + i + ' ');
            grade = grades % 10;
            grades = grades / 10;
            for (int j = 0; j < grade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
        scanner.close();
    }

    public static void wrongInput() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    public static long gradesFunc(long grades, int week, Scanner scanner) {
        int min;
        long res;
        long pow = 1;

        for (int i = 1; i < week; i++) {
            pow *= 10;
        }
        min = minimal(scanner);
        res = grades + (min * pow);
        return res;
    }

    public static int minimal(Scanner scanner) {
        int min;
        int counter = 0;
        int num;

        final int NUM_OF_GRADES = 4;

        if (scanner.hasNextInt()) {
            min = scanner.nextInt();
            if (min < 1 || min > 9) wrongInput();
            while (counter < NUM_OF_GRADES) {
                if (scanner.hasNextInt()) {
                    num = scanner.nextInt();
                    if (min < 1 || min > 9) wrongInput();
                    if (num < min) min = num;
                } else {
                    wrongInput();
                }
                counter++;
            }
            if (min < 1 || min > 9) wrongInput();
            return min;
        } else {
            wrongInput();
        }
        return 0;
    }
}
