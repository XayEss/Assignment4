package controller.implementation;

import controller.interfaces.Controller;
import controller.interfaces.Input;
import java.util.Scanner;
import model.implementation.ImageToBufferedImageService;
import view.impl.VisualizeImage;

public class CommandLineInput implements Input {

  private Controller controller;


  @Override
  public void startCommandReading() {
    boolean run = true;
    Scanner scanner = new Scanner(System.in);
    String name;
    String saveName;
    while (run) {
      switch (scanner.next()) {
        case "load":
          name = scanner.next();
          saveName = scanner.next();
          controller.loadImage(name, saveName);
          break;
        case "brighten":
          int amount = scanner.nextInt();
          name = scanner.next();
          saveName = scanner.next();
          controller.alterImageBrightness(name, saveName, amount);
          break;
        case "vertical-flip":
          name = scanner.next();
          saveName = scanner.next();
          controller.createFlippedImage(name, saveName, false);
          break;
        case "horizontal-flip":
          name = scanner.next();
          saveName = scanner.next();
          controller.createFlippedImage(name, saveName, true);
          break;
        case "greyscale":
          name = scanner.next();
          saveName = scanner.next();
          controller.createGreyScaleImage(name, saveName);
          break;
        case "save":
          name = scanner.next();
          saveName = scanner.next();
          controller.saveImage(name, saveName);
          break;
        case "rgb-split":
          name = scanner.next();
          saveName = scanner.next();
          String saveName2 = scanner.next();
          String saveName3 = scanner.next();
          controller.splitImageChannels(name, saveName, saveName2, saveName3);
          break;
        case "rgb-combine":
          saveName = scanner.next();
          name = scanner.next();
          String name2 = scanner.next();
          String name3 = scanner.next();
          controller.combineGreyScaleImages(name, name2, name3, saveName);
          break;
        case "run":
          runScript(scanner.next());
          break;
        case "show":
          name = scanner.next();
          break;
        case "stop":
          run = false;
          break;
        default:
          break;
      }
    }
  }

  @Override
  public void parseInput(String line) {
    //String[] tokens = line.split("/s+");

  }

  @Override
  public void runScript(String path) {
    controller.runScript(path);
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }


}
