package ex00.ImagesToChar.src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {
    private final char WHITE;
    private final char BLACK;
    private final String PATH;

    public Logic(char WHITE, char BLACK, String PATH) {
        this.WHITE = WHITE;
        this.BLACK = BLACK;
        this.PATH = PATH;
    }

    public void draw() {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = bufferedImage.getRGB(x, y);
                if (color == Color.WHITE.getRGB()) {
                    System.out.print(WHITE);
                } else if (color == Color.BLACK.getRGB()) {
                    System.out.print(BLACK);
                }
            }
            System.out.println();
        }
    }
}
