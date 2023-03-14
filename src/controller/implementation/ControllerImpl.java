package controller.implementation;

import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import java.io.FileNotFoundException;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
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

  public ControllerImpl(ImageInput imageInput) {
    this.imageInput = imageInput;
  }

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
  public void splitImageChannels(String name, String redResultName, String greenResultName, String blueResultName) {
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
  public void saveImage(String path, String name, String saveName) {
    try {
      imageSaver.save(path, imageHandler.getByName(name));
    } catch(FileNotFoundException e){

    }
  }

  @Override
  public void runScript(String path) {

  }
}
