package controller.implementation;

import controller.interfaces.TransformationController;
import java.io.InputStream;
import java.util.Scanner;

public class TextInput extends CommandLineInput {

  private TransformationController controller;

  public TextInput(InputStream input) {
    super(input);
  }

  @Override
  public void parseInput(String line) {
    Scanner scanner = new Scanner(line);
    switch(line){
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
