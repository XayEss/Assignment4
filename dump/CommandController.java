package controller.implementation;

import controller.implementation.commands.FlipImage;
import controller.interfaces.CommandHelper;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import controller.interfaces.TransformationController;
import model.interfaces.ImageHandler;
import model.interfaces.TransformImageHandler;
import view.intefraces.Output;

/**
 * This class is responsible for coordinating the functionality of
 * the image processing applications.
 */
public class CommandController implements TransformationController {

  private final ImageInput imageInput;
  private final ImageSaver imageSaver;
  private final Input input;
  private final Output output;
  private final ImageHandler imageHandler;


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
  public CommandController(ImageInput imageInput, ImageSaver imageSaver, Input input,
                           TransformImageHandler imageHandler, Output output) {
    this.imageInput = imageInput;
    this.imageSaver = imageSaver;
    this.input = input;
    this.imageHandler = imageHandler;
    this.output = output;
    input.setController(this);
  }

  @Override
  public void start() {
    // Future Scope
  }

  @Override
  public void createSepiaImage(String name, String saveName) {
    // Future Scope

  }

  @Override
  public void ditherImage(String name, String saveName) {
    // Future Scope

  }

  @Override
  public void blurImage(String name, String saveName) {
    // Future Scope

  }

  @Override
  public void sharpenImage(String name, String saveImage) {
    // Future Scope

  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    // Future Scope

  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    CommandHelper command = new FlipImage(horizontal);
    //command.execute(name, resultName, imageHandler);
  }

  @Override
  public void createValueImage(String name, String resultName) {
    // Future Scope

  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    // Future Scope

  }

  @Override
  public void createLumaImage(String name, String resultName) {
    // Future Scope

  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    // Future Scope

  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    // Future Scope

  }

  @Override
  public void splitImageChannels(String name, String redResultName, String greenResultName,
                                 String blueResultName) {
    // Future Scope

  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    // Future Scope

  }

  @Override
  public void loadImage(String path, String name) {
    // Future Scope

  }

  @Override
  public void saveImage(String path, String name) {
    // Future Scope

  }

  @Override
  public void runScript(String path) {
    // Future Scope

  }
}
