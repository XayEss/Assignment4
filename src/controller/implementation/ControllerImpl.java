package controller.implementation;

import java.io.FileNotFoundException;

import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.ImageHandler;
import view.impl.VisualizeAscii;
import view.impl.VisualizeImage;
import view.intefraces.Output;

/**
 * An implementation of the controller for image manipulation.
 */
public class ControllerImpl implements Controller {
  ImageInput imageInput;
  ImageSaver imageSaver;
  Input input;
  Output output;
  ImageHandler imageHandler;


  /**
   * This is the constructor for the ControllerImpl class, which is responsible for coordinating
   * the functionality of the image processing application.
   * It takes in instances of ImageInput, ImageSaver, Input, ImageHandler, and Output as parameters,
   * which are used to load, save, manipulate, and display images as required.
   *
   * @param imageInput   an instance of ImageInput used to load images
   * @param imageSaver   an instance of ImageSaver used to save images
   * @param input        an instance of Input used to receive user input
   * @param imageHandler an instance of ImageHandler used to manipulate images
   * @param output       an instance of Output used to display images
   */
  public ControllerImpl(ImageInput imageInput, ImageSaver imageSaver, Input input,
                        ImageHandler imageHandler, Output output) {
    this.imageInput = imageInput;
    this.imageSaver = imageSaver;
    this.input = input;
    this.imageHandler = imageHandler;
    this.output = output;
  }


  @Override
  public void start() {
    input.startCommandReading();
  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    imageHandler.getChannel(name, channel, resultName);
  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    imageHandler.flipImage(name, horizontal, resultName);
  }

  @Override
  public void createValueImage(String name, String resultName) {
    imageHandler.getValue(name, resultName);
  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    imageHandler.getIntensity(name, resultName);
  }

  @Override
  public void createLumaImage(String name, String resultName) {
    imageHandler.getLuma(name, resultName);
  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    imageHandler.alterBrightness(name, value, resultName);
  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    imageHandler.getGreyscale(name, resultName);
  }

  @Override
  public void splitImageChannels(String name, String redResultName,
                                 String greenResultName, String blueResultName) {
    imageHandler.getSplitChannels(name, redResultName, greenResultName, blueResultName);
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    imageHandler.combineGreyScaleImages(redName, greenName, blueName, resultName);
  }

  @Override
  public void loadImage(String path, String name) {
    imageHandler.saveWithName(name, imageInput.readFile(path));
  }

  @Override
  public void saveImage(String path, String name) {
    try {
      imageSaver.save(path, imageHandler.getByName(name));
    } catch (FileNotFoundException e) {
      System.out.println("Wrong path name");
    }
  }

  @Override
  public void runScript(String path) {
    System.out.println("FUCK!!!");
    new VisualizeAscii().show(imageHandler.getByName(path));
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(imageHandler.getByName(path)));

  }
}
