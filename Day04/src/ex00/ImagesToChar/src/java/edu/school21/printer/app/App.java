package ex00.ImagesToChar.src.java.edu.school21.printer.app;

import ex00.ImagesToChar.src.java.edu.school21.printer.logic.Logic;

public class App {
    public static void main(String[] args) {
        if (args.length < 3 || args[0].length() > 1 || args[1].length() > 1) {
            System.err.println("Wrong input");
            System.exit(-1);
        }

        char WHITE = args[0].charAt(0);
        char BLACK = args[1].charAt(0);
        String pathToFile = args[2];
        new Logic(WHITE, BLACK, pathToFile).draw();
    }
}
