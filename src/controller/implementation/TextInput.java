package controller.implementation;

import java.io.InputStream;
import java.util.Scanner;

import controller.interfaces.Controller;
import controller.interfaces.TransformationController;

/**
 * This class contains functionality to process text input from the user.
 */
public class TextInput extends CommandLineInput {

  private TransformationController controller;

  public TextInput(InputStream input) {
    super(input);
  }

  @Override
  public void parseInput(String line) {
    Scanner scanner = new Scanner(line);
    switch (scanner.next()) {
      case "dither":
        controller.ditherImage(scanner.next(), scanner.next());
        break;
      case "sepia":
        controller.createSepiaImage(scanner.next(), scanner.next());
        break;
      case "blur":
        controller.blurImage(scanner.next(), scanner.next());
        break;
      case "sharpen":
        controller.sharpenImage(scanner.next(), scanner.next());
        break;
      default:
        super.parseInput(line);
    }
  }

  @Override
  public void setController(Controller controller) {
    super.setController(controller);
    this.controller = (TransformationController) controller;
  }
}
