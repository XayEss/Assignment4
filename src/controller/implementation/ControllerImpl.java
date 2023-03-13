package controller.implementation;

import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.interfaces.Image;
import model.interfaces.ImageHandler;

/**
 * An implementation of the controller for image manipulation.
 */
public class ControllerImpl implements Controller {
  ImageInput imageInput;
  ImageSaver imageSaver;
  Input input;
  //Output output;
  ImageHandler imageHandler;

  public ControllerImpl(ImageInput imageInput) {
    this.imageInput = imageInput;
  }

  public ControllerImpl(ImageInput imageInput, ImageSaver imageSaver, Input input,
      ImageHandler imageHandler) {
    this.imageInput = imageInput;
    this.imageSaver = imageSaver;
    this.input = input;
    this.imageHandler = imageHandler;
  }


  @Override
  public void start() {

  }

  @Override
  public void getChannel(String name, String resultName, int channel) {
    imageHandler.getChannel(name, channel, resultName);
  }

  @Override
  public void flipImage(String name, String resultName, boolean horizontal) {
    imageHandler.flipImage(name, horizontal, resultName);
  }

  @Override
  public void getValue(String name, String resultName) {
    imageHandler.getValue(name, resultName);
  }

  @Override
  public void getIntensity(String name, String resultName) {
    imageHandler.getIntensity(name, resultName);
  }

  @Override
  public void getLuma(String name, String resultName) {
    imageHandler.getLuma(name, resultName);
  }

  @Override
  public void alterBrightness(String name, String resultName, int value) {
    imageHandler.alterBrightness(name, value, resultName);
  }

  @Override
  public void getSplitChannels(String name, String resultName) {
    ;
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
      String resultName) {
    imageHandler.combineGreyScaleImages(redName, greenName, blueName, resultName);
  }

  @Override
  public void loadImage(String path, String name) {

  }

  @Override
  public void saveImage(String path, String name, String saveName) {

  }

  @Override
  public void runScript(String path) {

  }
}
