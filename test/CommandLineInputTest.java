import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.interfaces.Controller;
import controller.interfaces.Input;

import static org.junit.Assert.assertEquals;

public class CommandLineInputTest implements Input {

  private boolean run = true;
  private Controller controller;


  @Override
  public void startCommandReading() {
//    Scanner scanner = new Scanner(System.in);
    try {
      parseInput("start");
    } catch (NoSuchElementException e) {
      System.out.println("The command is not full, type help for list of commands");
    }
  }

  public void helperInstructionsfeed(String temp) {
    parseInput(temp);
  }


  @Override
  public void parseInput(String line) {
    Scanner scanner = new Scanner(line);
    String name;
    String saveName;
    switch (scanner.next()) {
      case "start":
        controller.start();
        break;
      case "load":
        name = "name";
        saveName = "saveName";
        controller.loadImage(name, saveName);
        break;
      case "brighten":
        int amount = 100;
        name = "name";
        saveName = "saveName";
        controller.alterImageBrightness(name, saveName, amount);
        break;
      case "vertical-flip":
        name = "name";
        saveName = "saveName";
        controller.createFlippedImage(name, saveName, false);
        break;
      case "horizontal-flip":
        name = "name";
        saveName = "saveName";
        controller.createFlippedImage(name, saveName, true);
        break;
      case "greyscale":
        name = "name";
        saveName = "saveName";
        controller.createGreyScaleImage(name, saveName);
        break;
      case "save":
        name = "name";
        saveName = "saveName";
        controller.saveImage(name, saveName);
        break;
      case "rgb-split":
        name = "name";
        saveName = "saveName";
        String saveName2 = "saveName2";
        String saveName3 = "saveName3";
        controller.splitImageChannels(name, saveName, saveName2, saveName3);
        break;
      case "rgb-combine":
        saveName = "saveName";
        name = "name";
        String name2 = "saveName2";
        String name3 = "saveName3";
        controller.combineGreyScaleImages(name, name2, name3, saveName);
        break;
      case "run":
        controller.runScript("file/path");
        break;
      case "help":
//        printHelp();
        break;
      case "stop":
        run = false;
        break;
      default:
        System.out.println("No such command! Type help for list of commands");
        break;
    }


  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  @Test
  public void testStartCommandReading() {
    Input cLITest = new CommandLineInputTest();
    ControllerMock mockController = new ControllerMock();
    cLITest.setController(mockController);

    cLITest.startCommandReading();
    assertEquals("start ", mockController.getLog());
  }

  @Test
  public void testParseInput() {
    CommandLineInputTest cLITest = new CommandLineInputTest();
    ControllerMock mockController = new ControllerMock();
    cLITest.setController(mockController);

    cLITest.helperInstructionsfeed("load");
    assertEquals("loadImage name saveName ", mockController.getLog());

    cLITest.helperInstructionsfeed("brighten");
    assertEquals("loadImage name saveName alterImageBrightness name saveName 100",
            mockController.getLog());

    cLITest.helperInstructionsfeed("vertical-flip");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
            "100createFlippedImage name saveName false ", mockController.getLog());

    cLITest.helperInstructionsfeed("horizontal-flip");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
                    "100createFlippedImage name saveName false createFlippedImage name " +
                    "saveName true ",
            mockController.getLog());

    cLITest.helperInstructionsfeed("greyscale");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
                    "100createFlippedImage name saveName false createFlippedImage name " +
                    "saveName " +
                    "true createGreyScaleImage name saveName ",
            mockController.getLog());

    cLITest.helperInstructionsfeed("save");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
                    "100createFlippedImage name saveName false createFlippedImage name saveName " +
                    "true createGreyScaleImage name saveName saveImage name saveName ",
            mockController.getLog());

    cLITest.helperInstructionsfeed("rgb-split");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
                    "100createFlippedImage name saveName false createFlippedImage name saveName " +
                    "true createGreyScaleImage name saveName saveImage name saveName " +
                    "splitImageChannels name saveName saveName2 saveName3 ",
            mockController.getLog());

    cLITest.helperInstructionsfeed("rgb-combine");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
                    "100createFlippedImage name saveName false createFlippedImage name saveName " +
                    "true createGreyScaleImage name saveName saveImage name saveName " +
                    "splitImageChannels name saveName saveName2 saveName3 combineGreyScaleImages " +
                    "name saveName2 saveName3 saveName ",
            mockController.getLog());

    cLITest.helperInstructionsfeed("run");
    assertEquals("loadImage name saveName alterImageBrightness name saveName " +
            "100createFlippedImage name saveName false createFlippedImage name saveName true " +
            "createGreyScaleImage name saveName saveImage name saveName splitImageChannels name " +
            "saveName saveName2 saveName3 combineGreyScaleImages name saveName2 saveName3 " +
            "saveName runScript file/path ", mockController.getLog());

  }


  public class ControllerMock implements Controller {

    public String log = "";

    @Override
    public void start() {
      log = "start ";
    }

    @Override
    public void separateImageChannel(String name, String resultName, int channel) {
      log += "separateImageChannel: " + name + " " + resultName + " " + channel + " ";
    }

    @Override
    public void createFlippedImage(String name, String resultName, boolean horizontal) {
      log += "createFlippedImage " + name + " " + resultName + " " + horizontal + " ";
    }

    @Override
    public void createValueImage(String name, String resultName) {
      log += "createValueImage " + name + " " + resultName + " ";
    }

    @Override
    public void createIntensityImage(String name, String resultName) {
      log += "createIntensityImage " + name + " " + resultName + " ";
    }

    @Override
    public void createLumaImage(String name, String resultName) {
      log += "createLumaImage " + name + " " + resultName + " ";
    }

    @Override
    public void alterImageBrightness(String name, String resultName, int value) {
      log += "alterImageBrightness " + name + " " + resultName + " " + value;
    }

    @Override
    public void createGreyScaleImage(String name, String resultName) {
      log += "createGreyScaleImage " + name + " " + resultName + " ";
    }

    @Override
    public void splitImageChannels(String name, String redResultName, String greenResultName, String blueResultName) {
      log += "splitImageChannels " + name + " " + redResultName + " " + greenResultName + " " + blueResultName + " ";
    }

    @Override
    public void combineGreyScaleImages(String redName, String greenName, String blueName, String resultName) {
      log += "combineGreyScaleImages " + redName + " " + greenName + " " + blueName + " " + resultName + " ";
    }

    @Override
    public void loadImage(String path, String name) {
      log += "loadImage " + path + " " + name + " ";
    }

    @Override
    public void saveImage(String path, String name) {
      log += "saveImage " + path + " " + name + " ";
    }

    @Override
    public void runScript(String path) {
      log += "runScript " + path + " ";
    }

    public String getLog() {
      return log;
    }
  }


}





