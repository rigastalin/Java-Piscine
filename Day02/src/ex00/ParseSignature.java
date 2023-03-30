package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ParseSignature {
    public static char[] HEX = "0123456789ABCDEF".toCharArray();

    public static void parseInput(String str, Map<String, String> signature) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bytes = new byte[8];
            fileInputStream.read(bytes, 0, 8);
            String str_ = byteToHex(bytes);
            findSignature(str_, signature);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private static String byteToHex(byte[] bytes) {
        char[] arr = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; ++i) {
            int temp = bytes[i] & 0xFF;
            arr[i * 2] = HEX[temp >>> 4];
            arr[i * 2 + 1] = HEX[temp & 0x0F];
        }
        return new String(arr);
    }

    private static void findSignature(String str, Map<String, String> signature) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true);
        for (Map.Entry<String, String> sig : signature.entrySet()) {
            if (str.contains(sig.getValue())) {
                fileOutputStream.write(sig.getKey().getBytes());
                fileOutputStream.write('\n');
                System.out.println("PROCESSED");
                return;
            }
        }
        System.out.println("UNDEFINED");
    }
 }
