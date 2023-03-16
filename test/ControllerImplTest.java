import java.io.FileNotFoundException;

import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;
import view.intefraces.Output;

public class ControllerImplTest implements Controller {
  public String logOutput;
  ImageInput imageInput;
  ImageSaver imageSaver;
  Input input;
  Output output;
  ImageHandler imageHandler;

  public ControllerImplTest(ImageInput imageInput, ImageSaver imageSaver, Input input,
                            ImageHandler imageHandler, Output output) {
    this.imageInput = imageInput;
    this.imageSaver = imageSaver;
    this.input = input;
    this.imageHandler = imageHandler;
    this.output = output;
  }

  @Override
  public void start() {
    logOutput = "start";
    input.startCommandReading();
  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    try {
      imageHandler.getChannel(name, channel, resultName);
      logOutput = "rgb-split";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    try {
      imageHandler.flipImage(name, horizontal, resultName);
      logOutput = "flip";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void createValueImage(String name, String resultName) {
    try {
      imageHandler.getValue(name, resultName);
      logOutput = "value";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    try {
      imageHandler.getIntensity(name, resultName);
      logOutput = "intensity";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void createLumaImage(String name, String resultName) {
    try {
      imageHandler.getLuma(name, resultName);
      logOutput = "luma";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    try {
      imageHandler.alterBrightness(name, value, resultName);
      logOutput = "alter-brightness";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    try {
      imageHandler.getGreyscale(name, resultName);
      logOutput = "greyscale";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void splitImageChannels(String name, String redResultName,
                                 String greenResultName, String blueResultName) {
    try {
      imageHandler.getSplitChannels(name, redResultName, greenResultName, blueResultName);
      logOutput = "split";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    try {
      imageHandler.combineGreyScaleImages(redName, greenName, blueName, resultName);
      logOutput = "rgb-combine";
    } catch (NoSuchImageException e) {
      output.print(e.getMessage());
    }
  }

  @Override
  public void loadImage(String path, String name) {
    imageHandler.saveWithName(name, imageInput.readFile(path));
    logOutput = "load";
  }

  @Override
  public void saveImage(String path, String name) {
    try {
      imageSaver.save(path, imageHandler.getByName(name));
      logOutput = "save";
    } catch (NoSuchImageException | FileNotFoundException e) {
      output.print(e.getMessage());
    }
  }


  @Override
  public void runScript(String path) {
    logOutput = "script";
    //new VisualizeAscii().show(imageHandler.getByName(path));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(imageHandler.getByName(path)));

  }
}