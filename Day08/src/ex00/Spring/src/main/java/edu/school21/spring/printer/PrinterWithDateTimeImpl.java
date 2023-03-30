package edu.school21.spring.printer;

import edu.school21.spring.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        renderer.renderMsg("[" + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "] " + str);
    }
}
