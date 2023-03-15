package view.impl;

import model.interfaces.Image;
import view.intefraces.Output;

/**
 * The VisualizeAscii class represents an Output that displays an image in ASCII art.
 */
public class VisualizeAscii implements Output {

  @Override
  public void show(Image image) {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        line.append(getAsciiChar(image.getPixel(i, j).getGreyScale()));
      }
      System.out.println(line.toString());
      line.delete(0, line.length());
    }
  }

  private char getAsciiChar(int character) {
    final char str;
    if (character >= 230.0) {
      str = ' ';
    } else if (character >= 200.0) {
      str = '.';
    } else if (character >= 180.0) {
      str = '*';
    } else if (character >= 160.0) {
      str = ':';
    } else if (character >= 130.0) {
      str = 'o';
    } else if (character >= 100.0) {
      str = '&';
    } else if (character >= 70.0) {
      str = '8';
    } else if (character >= 50.0) {
      str = '#';
    } else {
      str = '@';
    }
    return str;
  }

}
