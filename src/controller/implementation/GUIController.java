package controller.implementation;

import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.interfaces.TransformImageHandler;
import view.intefraces.Output;

/**
 * This class is responsible for coordinating the functionality of the image processing application.
 */
public class GUIController extends TransformationControllerImpl {

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
  public GUIController(ImageInput imageInput,
                       ImageSaver imageSaver, Input input,
                       TransformImageHandler imageHandler, Output output) {
    super(imageInput, imageSaver, input, imageHandler, output);
  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    super.separateImageChannel(name, resultName, channel);
  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    super.createFlippedImage(name, resultName, horizontal);
  }

  @Override
  public void createValueImage(String name, String resultName) {
    super.createValueImage(name, resultName);
  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    super.createIntensityImage(name, resultName);
  }

  @Override
  public void createLumaImage(String name, String resultName) {
    super.createLumaImage(name, resultName);
  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    super.alterImageBrightness(name, resultName, value);
  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    super.createGreyScaleImage(name, resultName);
  }

  @Override
  public void splitImageChannels(String name, String redResultName,
                                 String greenResultName, String blueResultName) {
    super.splitImageChannels(name, redResultName, greenResultName, blueResultName);
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    super.combineGreyScaleImages(redName, greenName, blueName, resultName);
  }


  @Override
  public void loadImage(String path, String name) {
    super.loadImage(path, name);
  }

  @Override
  public void saveImage(String path, String name) {
    super.saveImage(path, name);
  }

  @Override
  public void createSepiaImage(String name, String saveName) {
    super.createSepiaImage(name, saveName);
  }

  @Override
  public void ditherImage(String name, String saveName) {
    super.ditherImage(name, saveName);
  }

  @Override
  public void blurImage(String name, String saveName) {
    super.blurImage(name, saveName);
  }

  @Override
  public void sharpenImage(String name, String saveName) {
    super.sharpenImage(name, saveName);
  }


  @Override
  public void runScript(String path) {
    //System.out.println("in runScript!");
    //new VisualizeAscii().show(imageHandler.getByName(path));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered
    // (imageHandler.getByName(path)));
  }
}
