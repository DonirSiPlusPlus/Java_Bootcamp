package edu.school21.printer.app;

import edu.school21.printer.logic.ImgToChar;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Main {
  @Parameter(
          names = {"--white"},
          description = "Color for white pixel",
          required = true
  )
  static String white;
  @Parameter(
          names = {"--black"},
          description = "Color for black pixel",
          required = true
  )
  static String black;

  public static void main(String ... argv) {
    JCommander.newBuilder()
            .addObject(new Main())
            .build()
            .parse(argv);

//    System.out.printf("%s %s", white, black);

    try {
      String imagePath = "/resources/image.bmp";
      BufferedImage buffImage = ImageIO.read(Main.class.getResource(imagePath));

      if (buffImage == null) {
        return;
      }

      ImgToChar printer = new ImgToChar(white, black, buffImage);
      printer.printImage();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}