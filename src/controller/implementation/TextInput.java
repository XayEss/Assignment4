package controller.implementation;

import java.io.InputStream;
import java.util.Scanner;

import controller.interfaces.TransformationController;

public class TextInput extends CommandLineInput {

  private TransformationController controller;

  public TextInput(InputStream input) {
    super(input);
  }

  @Override
  public void parseInput(String line) {
    Scanner scanner = new Scanner(line);
    switch (line) {
      case "dither":
        controller.ditherImage(scanner.next(), scanner.next());
        break;
      case "sepia":
        controller.createSepiaImage(scanner.next(), scanner.next());
        break;
      case "blur":
        break;
      case "sharpen":
        break;
      default:
        super.parseInput(line);
    }
  }
}
