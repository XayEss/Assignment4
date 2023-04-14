package controller.implementation;

import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import java.io.IOException;
import model.implementation.HistogramCreator;
import model.implementation.NoSuchImageException;
import model.interfaces.TransformImageHandler;
import view.intefraces.GUIOutput;
import view.intefraces.Output;

/**
 * This class is responsible for coordinating the functionality of the image processing application.
 */
public class GUIController extends TransformationControllerImpl {
  private String imageName;
  private GUIOutput output;
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
                       TransformImageHandler imageHandler, GUIOutput output) {
    super(imageInput, imageSaver, input, imageHandler, output);
    output.setController(this);
    this.output = output;
  }

  @Override
  public void start() {

  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    setImageName(resultName);
    super.separateImageChannel(name, resultName, channel);
  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    setImageName(resultName);
    super.createFlippedImage(name, resultName, horizontal);
  }

  @Override
  public void createValueImage(String name, String resultName) {
    setImageName(resultName);
    super.createValueImage(name, resultName);
  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    setImageName(resultName);
    super.createIntensityImage(name, resultName);
  }

  @Override
  public void createLumaImage(String name, String resultName) {
    setImageName(resultName);
    super.createLumaImage(name, resultName);
  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    setImageName(resultName);
    super.alterImageBrightness(name, resultName, value);
  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    setImageName(resultName);
    super.createGreyScaleImage(name, resultName);
  }

  @Override
  public void splitImageChannels(String name, String redResultName,
                                 String greenResultName, String blueResultName) {
    setImageName(name);
    super.splitImageChannels(name, redResultName, greenResultName, blueResultName);
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    setImageName(resultName);
    super.combineGreyScaleImages(redName, greenName, blueName, resultName);
  }


  @Override
  public void loadImage(String path, String name) {
    setImageName(name);
    super.loadImage(path, name);
    //printInfo("youve been pranked");

  }

  @Override
  public void saveImage(String path, String name) {
    super.saveImage(path, name);
  }

  @Override
  public void createSepiaImage(String name, String saveName) {
    super.createSepiaImage(name, saveName);
    setImageName(saveName);
  }

  @Override
  public void ditherImage(String name, String saveName) {
    super.ditherImage(name, saveName);
    setImageName(saveName);
  }

  @Override
  public void blurImage(String name, String saveName) {
    super.blurImage(name, saveName);
    setImageName(saveName);
  }

  @Override
  public void sharpenImage(String name, String saveName) {
    super.sharpenImage(name, saveName);
    setImageName(saveName);
  }

  @Override
  protected void printInfo(String info) {
    super.printInfo(info);
    try {
      output.goToMainView();
      showImage(imageName);
      output.showHistogram(new HistogramCreator().getRGBHistograms(getImageStream(imageName)));
    }catch (NoSuchImageException | IOException e){
      output.print(e.getMessage());
    }
  }

  private void setImageName(String name) {
    imageName = name;
  }

}
