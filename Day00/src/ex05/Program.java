package ex05;

import java.util.Scanner;

public class Program {
    private static final int MAX_LESSONS = 10;
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_LENGTH_NAMES = 10;
    private static final int WEEK = 7;
    private static final String[] DAY_WEEKS = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
    private static final int START_LESSON = 1;
    private static final int FINISH_LESSON = 6;
    private static final int[][] week = new int[WEEK][MAX_LESSONS];
    private static final String[] studentsName = new String[MAX_STUDENTS];
    private static final String[][][][] schedule = new String[10][31][10][1];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        studentsFunc();
        timeFunc();
        scheduleFunc();
        printScheduleFunc();
    }

    public static void wrongInput() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    public static void studentsFunc() {
        System.out.println("Input names of students (max 10): ");
        for (int i = 0; i < MAX_STUDENTS; i++) {
            String student = scanner.nextLine();
            if (student.equals(".")) {
                break;
            }
            if (student.length() > MAX_LENGTH_NAMES) {
                student = student.substring(0, MAX_LENGTH_NAMES);
            }
            studentsName[i] = student;
        }
    }

    public static void timeFunc() {
        System.out.println("Input time and date of the week: ");
        String inputStr = scanner.nextLine();

        while (!inputStr.equals(".")) {
            int time = inputStr.toCharArray()[0] - 48;
            if (time < START_LESSON || time > FINISH_LESSON) wrongInput();

            String day = inputStr.substring(2);

            int count = 0;
            while (true) {
                int index = getDay(day);
                if (index < WEEK) {
                    if (week[index][count] == 0) {
                        week[index][count] = time;
                        break;
                    }
                    count++;
                } else {
                    wrongInput();
                }
            }
            inputStr = scanner.nextLine();
        }
        for (int i = 0; i < week.length; ++i) {
            int j = 0;
            for (; week[i][j] != 0; ++j) {}
            sortFunc(week[i], 0, j - 1);
        }
    }

    public static int getDay(String day) {
        int i = 0;
        for (; i < 7; i++) {
            if (day.equals(DAY_WEEKS[i])) break;
        }
        return i;
    }

    public static void scheduleFunc() {
        System.out.println("Input schedule: ");

        for (int i = 0; i < studentsName.length; i++) {
            for (int j = 1; j < 31; j++) {
                for (int k = 0; k < 10; k++) {
                    schedule[i][j][k][0] = "0";
                }
            }
        }

        String inputStr = scanner.nextLine();
        while(!inputStr.equals(".")) {
            String[] space = inputStr.split(" ");
            for (int i = 0; i < studentsName.length; i++) {
                if (space[0].equals(studentsName[i])) {
                    for (int j = 1; j < 31; j++) {
                        if (space[2].equals(j + "")) {
                            for (int k = 0; k < 10; k++) {
                                if (space[1].equals(k + "")) {
                                    if (space[3].equals("HERE")) {
                                        schedule[i][j][k][0] = "1";
                                    } else if (space[3].equals("NOT_HERE")) {
                                        schedule[i][j][k][0] = "-1";
                                    }
                                }
                            }
                        }
                    }
                }
            }
            inputStr = scanner.nextLine();
        }
    }

    public static void printScheduleFunc() {
        System.out.print("          ");

        for (int j = 1; j < 31; j++) {
            int temp = (j % 7);
            if (week[temp][0] != 0) {
                for (int k = 0; k < week[temp].length && week[temp][k] != 0; ++k) {
                    System.out.print(week[temp][k] + ":00 ");
                    System.out.print(DAY_WEEKS[temp] + " ");
                    System.out.print(j + "|");
                }
            }
        }
        System.out.println();
        for (int i = 0; i < studentsName.length; ++i) {
            if (studentsName[i] != null) {
                System.out.printf("%10s", studentsName[i]);
                for (int j = 1; j < 31; ++j) {
                    int temp = (j % 7);
                    if (week[temp][0] != 0) {
                        for (int k = 0; k < week[temp].length && week[temp][k] != 0; ++k) {
                            if (j > 9) System.out.print(" ");
                            if (schedule[i][j][week[temp][k]][0].equals("0")) {
                                System.out.print("         |");
                            } else {
                                System.out.printf("%9s|", schedule[i][j][week[temp][k]][0]);
                            }
                        }
                    }
                }
            } else {
                break;
            }
            System.out.println();
        }
    }

    public static void sortFunc(int[] arr, int from, int to) {
        if (arr.length == 0 || from >= to) return;

        int middle = from + (to - from) / 2;
        int border = arr[middle];

        int i = from;
        int j = to;
        while (i <= j) {
            while (arr[i] < border) i++;
            while (arr[j] > border) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (from < j) sortFunc(arr, from, j);
        if (from < j) sortFunc(arr, i, to);
    }
}
