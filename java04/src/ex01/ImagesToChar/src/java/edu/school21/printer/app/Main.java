package edu.school21.printer.app;

import edu.school21.printer.logic.ImgToChar;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Must be 2 arguments");
      return;
    }

    try {
      String imagePath = "/resources/image.bmp";
      char whiteSymb = args[0].charAt(0);
      char blackSymb = args[1].charAt(0);

      BufferedImage buffImage = ImageIO.read(Main.class.getResource(imagePath));

      if (buffImage == null) {
        return;
      }

      ImgToChar printer = new ImgToChar(blackSymb, whiteSymb, buffImage);
      printer.printImage();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}