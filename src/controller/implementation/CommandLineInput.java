package controller.implementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import controller.interfaces.Controller;
import controller.interfaces.Input;

/**
 * This class implements the Input interface and provides command-line input functionality for
 * the image processing application.
 * It allows the user to enter commands to load, alter, save and display images,
 * as well as run scripts.
 * It utilizes a Controller instance to perform the necessary operations on the images.
 */
public class CommandLineInput implements Input {
  private boolean run;
  private Controller controller;
  public CommandLineInput() {
    run = true;
  }




  @Override
  public void startCommandReading() {
    Scanner scanner = new Scanner(System.in);
    while (run) {
      parseInput(scanner.nextLine());
    }
  }


  @Override
  public void parseInput(String line) {
    Scanner scanner = new Scanner(line);
    String name;
    String saveName;
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
          System.out.println("No such command!");
          break;
      }

  }

  @Override
  public void runScript(String path) {
    try {
      List<String> lines = Files.readAllLines(Path.of(path));
      for(String line : lines){
        parseInput(line);
      }
    } catch (IOException e) {
      System.out.println("File not found!");
    }

  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }


}
