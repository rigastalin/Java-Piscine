package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

public class App {
    public static void main(String[] args) {
        if (args.length < 2 || args[0].length() > 1 || args[1].length() > 1) {
            System.err.println("Wrong input");
            System.exit(-1);
        }

        char WHITE = args[0].charAt(0);
        char BLACK = args[1].charAt(0);
        new Logic(WHITE, BLACK).draw();
    }
}
