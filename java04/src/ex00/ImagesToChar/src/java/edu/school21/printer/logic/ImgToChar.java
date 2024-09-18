package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImgToChar {
  public ImgToChar(char black, char white, BufferedImage image) {
    blackSymb_ = black;
    whiteSymb_ = white;
    img_ = image;
  }

  public void printImage() {
    for (int i = 0; i < img_.getHeight(); ++i) {
      for (int j = 0; j < img_.getWidth(); ++j) {
        int pixelColor = img_.getRGB(j, i);
        if (pixelColor == Color.black.getRGB()) {
          System.out.print(blackSymb_);
        } else {
          System.out.print(whiteSymb_);
        }
      }
      System.out.println();
    }
  }

  private char blackSymb_;
  private char whiteSymb_;
  private BufferedImage img_;
}