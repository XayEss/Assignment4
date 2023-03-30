package view.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import model.interfaces.Image;
import view.intefraces.Output;

/**
 * The VisualizeAscii class represents an Output that displays an image in ASCII art.
 */
public class CommandLineOutput implements Output {

  private final OutputStream outputStream;
  PrintWriter writer;
  public CommandLineOutput(OutputStream outputStream) {
    this.outputStream = outputStream;
    writer = new PrintWriter(outputStream);
  }

  @Override
  public void show(Image image) {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        line.append(getAsciiChar(image.getPixel(i, j).getGreyScale()));
      }
      writer.println(line);
      line.delete(0, line.length());
    }
    writer.flush();
  }

  @Override
  public void print(String string) {
    //PrintWriter writer = new PrintWriter(outputStream);
    writer.println(string);
    writer.flush();
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
