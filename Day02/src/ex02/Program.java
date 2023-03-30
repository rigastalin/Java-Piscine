package ex02;

import java.nio.file.*;
import java.util.Scanner;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;

public class Program {
    private static Path path;
    public static void main(String[] args) throws IOException {
        path = Paths.get(args[0]);
        System.out.println(path);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            linUtils(scanner);
        }
    }

    private static void linUtils(Scanner scanner) throws IOException {
        String str = scanner.nextLine();
        String[] arr = str.split(" ");

        if (arr.length == 1 && arr[0].equals("exit")) {
            scanner.close();
            System.exit(0);
        } else if (arr.length == 1 && arr[0].equals("ls")) {
            lsFunc();
        } else if (arr.length == 2 && arr[0].equals("cd")) {
            cdFunc(arr[1]);
        } else if (arr.length == 3 && arr[0].equals("mv")) {
            mvFunc(arr[1], arr[2]);
        }
    }

    private static void lsFunc() throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path pth : stream) {
            long size;
            if (Files.isDirectory(pth)) {
                size = directorySize(pth);
            } else {
                size = Files.size(pth);
            }
            System.out.println(pth.getFileName() + " " + size/1000 + " KB");
        }
     }

     private static long directorySize(Path path) throws IOException {
        long size = 0;
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path p : stream) {
            if (Files.isDirectory(p)) {
                size += directorySize(p);
            } else {
                size = Files.size(p);
            }
        }
        return size;
     }

    private static void cdFunc(String str) throws IOException {
        Path newPath = Paths.get(str);
        newPath = path.resolve(newPath).normalize();
        if (Files.isDirectory(newPath)) {
            path = newPath;
        } else {
            System.out.println(str + " No such directory");
        }
    }


    private static void mvFunc(String arg1, String arg2) throws IOException {
        Path src = null;
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path pth : stream) {
            if (pth.getFileName().toString().equals(arg1) && Files.isRegularFile(pth)) {
                src = pth;
                break;
            }
        }
        Path dest = Paths.get(arg2);
        dest = path.resolve(dest).normalize();
        if (Files.isDirectory(dest) && src != null) {
            Files.move(src, dest.resolve(src.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } else if (src != null) {
            Files.move(src, src.resolveSibling(dest));
        }
    }
}
