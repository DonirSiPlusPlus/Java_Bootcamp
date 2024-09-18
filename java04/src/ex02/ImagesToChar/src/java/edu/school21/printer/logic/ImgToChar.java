package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import java.awt.Color;

import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;
import com.diogonunes.jcolor.*;

public class ImgToChar {
  public ImgToChar(String forWhite, String forBlack, BufferedImage image) {
    forWhite_ = forWhite;
    forBlack_ = forBlack;
    img_ = image;
  }

  public void printImage() {
    findColorByName();
    for (int i = 0; i < img_.getHeight(); ++i) {
      for (int j = 0; j < img_.getWidth(); ++j) {
        int pixelColor = img_.getRGB(j, i);
        if (pixelColor == Color.black.getRGB()) {
          System.out.print(Ansi.colorize("█", Attribute.TEXT_COLOR(forWhite_r,forWhite_g,forWhite_b)));
        } else {
          System.out.print(Ansi.colorize("█", Attribute.TEXT_COLOR(forBlack_r,forBlack_g,forBlack_b)));
        }
      }
      System.out.println();
    }
  }

  private void findColorByName() {
    try {
      Color color1 = (Color)Color.class.getField(forWhite_.toUpperCase()).get(null);
      Color color2 = (Color)Color.class.getField(forBlack_.toUpperCase()).get(null);
      forBlack_r = color1.getRed();
      forBlack_g = color1.getGreen();
      forBlack_b = color1.getBlue();
      forWhite_r = color2.getRed();
      forWhite_g = color2.getGreen();
      forWhite_b = color2.getBlue();
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }
  }

  private String forBlack_;
  private String forWhite_;
  private BufferedImage img_;
  private int forBlack_r;
  private int forBlack_g;
  private int forBlack_b;
  private int forWhite_r;
  private int forWhite_g;
  private int forWhite_b;
}