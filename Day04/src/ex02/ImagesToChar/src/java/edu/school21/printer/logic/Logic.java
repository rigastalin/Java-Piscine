package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class Logic {
    public Logic(Args args) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(Logic.class.getResource("/resources/it.bmp")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String white = args.getWhite();
        String black = args.getBlack();
        ColoredPrinter printer = new ColoredPrinter();

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = bufferedImage.getRGB(x, y);
                if (color == Color.WHITE.getRGB()) {
                    printer.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else if (color == Color.BLACK.getRGB()) {
                    printer.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }
}
