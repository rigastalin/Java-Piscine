package ex03;

import java.io.*;
import java.util.HashMap;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Program {


    public static void main(String[] args) {
        HashMap<Integer, URL> filesURL = setFilesURL("/opt/goinfre/cflossie/Java_Bootcamp._Day03-3/src/ex03/files_urls.txt");
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.err.println("Wrong input");
            System.exit(-1);
        }

        int threadsNum = Integer.parseInt(args[0].substring("--threadsCount=".length()));
        if (threadsNum <= 0) {
            System.err.println("Number must be bigger than 0");
            System.exit(-1);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);
        for (Map.Entry<Integer, URL> entry : filesURL.entrySet()) {
            executorService.submit(() -> downloadFilesFromURL(entry.getKey(), entry.getValue()));
        }
        executorService.shutdown();
    }

    public static HashMap<Integer, URL> setFilesURL(String filePath) {
        HashMap<Integer, URL> urlHashMap = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while (bufferedReader.ready()) {
                String[] split = bufferedReader.readLine().split(" ");
                if (split.length == 2) {
                    urlHashMap.put(Integer.parseInt(split[0]), new URL(split[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return urlHashMap;
    }


    public static void downloadFilesFromURL(Integer number, URL url) {
        final String GREEN_COLOR = "\u001b[32m";
        final String RED_COLOR = "\u001b[31m";
        final String YELLOW_COLOR = " \u001b[33m";

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(number.toString());
            byte[] data = new byte[4096];
            int byteData;
            System.out.println(GREEN_COLOR + "Thread-" + Thread.currentThread().getName().split("-")[3] +
                    " start download file number " + number);
            while ((byteData = bufferedInputStream.read(data, 0, 4096)) != -1) {
                fileOutputStream.write(data, 0, byteData);
            }
            System.out.println(RED_COLOR + "Thread-" + Thread.currentThread().getName().split("-")[3] +
                    " finish download file number " + number);
        } catch (Exception e) {
            System.err.println(YELLOW_COLOR + "ATTENTION! FILE WITH NUMBER - " + number + " CANT BE DOWNLOADED");
        }
    }
}
