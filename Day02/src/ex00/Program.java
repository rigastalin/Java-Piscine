package ex00;

import java.util.Scanner;
import java.util.Map;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Map<String, String> signature = FindSignature.parser();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("42")) {
            ParseSignature.parseInput(str, signature);
            str = scanner.nextLine();
        }
        scanner.close();
    }
}
