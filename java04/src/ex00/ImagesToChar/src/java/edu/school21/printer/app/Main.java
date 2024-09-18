package edu.school21.printer.app;

import edu.school21.printer.logic.ImgToChar;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
public class Main {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Must be 3 arguments");
      return;
    }

    try {
      char blackSymb = args[0].charAt(0);
      char whiteSymb = args[1].charAt(0);
      java.io.File image = new java.io.File(args[2]);
      BufferedImage buffImage = ImageIO.read(image);

      if (buffImage == null) {
        return;
      }

      ImgToChar printer = new ImgToChar(blackSymb, whiteSymb, buffImage);
      printer.printImage();

    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
}