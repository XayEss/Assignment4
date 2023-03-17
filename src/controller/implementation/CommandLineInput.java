package controller.implementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.interfaces.Controller;
import controller.interfaces.Input;

/**
 * This class implements the Input interface and provides command-line input functionality for the
 * image processing application. It allows the user to enter commands to load, alter, save and
 * display images, as well as run scripts. It utilizes a Controller instance to perform the
 * necessary operations on the images.
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
      try {
        parseInput(scanner.nextLine());
      } catch (NoSuchElementException e) {
        System.out.println("The command is not full, type help for list of commands");
      }
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
      case "help":
        printHelp();
        break;
      case "stop":
        run = false;
        break;
      default:
        System.out.println("No such command! Type help for list of commands");
        break;
    }

  }

  private void runScript(String path) {
    try {
      List<String> lines = Files.readAllLines(Path.of(path));
      for (String line : lines) {
        parseInput(line);
      }
    } catch (IOException e) {
      System.out.println("File not found!");
    }

  }

  private void printHelp() {
    String help =
            "load 'path' 'name' - loads an image with the given path and " +
                    "saves it with name.\n"
                    + "save 'path' 'name' - save an image with name to the given path.\n"
                    + "brighten [amount] 'name' 'save name' - brightens image with the " +
                    "name by a amount"
                    + " and saves under save name.\n"
                    + "vertical-flip 'name' 'save name' - vertically flips image with the " +
                    "name and saves under "
                    + "save name\n"
                    + "horizontal-flip 'name' 'save name' - horizontally flips image with " +
                    "the name and saves"
                    + " under save name\n"
                    + "greyscale 'name' 'save name' - makes a greyscale version of the image " +
                    "with the name"
                    + " and saves under save name\n"
                    + "rgb-split 'name' 'name-red' 'name-green' 'name-blue' - splits image " +
                    "with name into three "
                    + "rgb component images, red component is saved under name-red, green - " +
                    "name-green, "
                    + "blue - name-blue.\n"
                    + "rgb-combine 'save name' 'name-red' 'name-green' 'name-blue' - combines " +
                    "three images into"
                    + " one, where name-red is the name of the image with the red component, " +
                    "name-green - green"
                    + " component, name-blue - blue component. Saves the image with save name.\n"
                    + "run 'path' runs all commands from a file with the give path. Each " +
                    "command should start "
                    + "from a new line\n"
                    + "stop - stops the program.";
    System.out.println(help);
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }


}
