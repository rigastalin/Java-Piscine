package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.*;

public class App {
    public static void main(String[] args) {
        Args args1 = new Args();
        JCommander jCommander = new JCommander(args1);
        jCommander.parse(args);
        Logic logic = new Logic(args1);
    }
}
