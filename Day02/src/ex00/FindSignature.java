package ex00;

import java.io.*;
import java.util.*;

public class FindSignature {
    private static final String FILE = "/opt/goinfre/cflossie/Java_Bootcamp._Day02-3/src/ex00/signature.txt";
    public static Map<String, String> parser() throws IOException{
        Map<String, String> signature = new HashMap<String, String>();

        FileInputStream fileInputStream = new FileInputStream(FILE);
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arr = str.split(",");
            signature.put(arr[0], arr[1].replaceAll("\\s+", ""));
        }
        scanner.close();
        fileInputStream.close();
        return signature;
    }
}
