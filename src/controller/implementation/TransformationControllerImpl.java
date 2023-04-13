package controller.implementation;

import java.io.IOException;

import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import controller.interfaces.TransformationController;
import java.io.InputStream;
import model.implementation.NoSuchImageException;
import model.interfaces.TransformImageHandler;
import view.intefraces.Output;

/**
 * This class is responsible for coordinating the functionality of the image processing application.
 */
public class TransformationControllerImpl extends ControllerImpl implements
        TransformationController {
  private final TransformImageHandler imageHandler;

  /**
   * This is the constructor for the ControllerImpl class, which is responsible for coordinating the
   * functionality of the image processing application. It takes in instances of ImageInput,
   * ImageSaver, Input, ImageHandler, and Output as parameters, which are used to load, save,
   * manipulate, and display images as required.
   *
   * @param imageInput   an instance of ImageInput used to load images
   * @param imageSaver   an instance of ImageSaver used to save images
   * @param input        an instance of Input used to receive user input
   * @param imageHandler an instance of ImageHandler used to manipulate images
   * @param output       an instance of Output used to display images
   */

  public TransformationControllerImpl(ImageInput imageInput, ImageSaver imageSaver,
                                      Input input, TransformImageHandler imageHandler,
                                      Output output) {
    super(imageInput, imageSaver, input, imageHandler, output);
    this.imageHandler = imageHandler;
    output.setController(this);
  }

  @Override
  public void createSepiaImage(String name, String saveName) {
    try {
      imageHandler.sepiaToneImage(name, saveName);
      printInfo("Successfully applied sepia to the image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void ditherImage(String name, String saveName) {
    try {
      imageHandler.ditherImage(name, saveName);
      printInfo("Successfully dithered the image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void blurImage(String name, String saveName) {
    try {
      imageHandler.blurImage(name, saveName);
      printInfo("Successfully blurred the image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void sharpenImage(String name, String saveName) {
    try {
      imageHandler.sharpenImage(name, saveName);
      printInfo("Successfully sharpened the image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  protected InputStream getImageStream(String name){
    try {
      return imageHandler.exportImage(name);
    }catch (NoSuchImageException | IOException e){
      printInfo(e.getMessage());
    }
    return null;
  }
}
