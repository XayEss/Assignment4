package controller.implementation;

import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import controller.interfaces.TransformationController;
import model.interfaces.ImageHandler;
import model.interfaces.TransformImageHandler;
import view.intefraces.Output;

public class TextInputController extends CommandLineInput implements TransformationController {
  ImageInput imageInput;
  ImageSaver imageSaver;
  Input input;
  Output output;
  TransformImageHandler imageHandler;


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
  public TextInputController(ImageInput imageInput, ImageSaver imageSaver, Input input,
      TransformImageHandler imageHandler, Output output) {
    super();
    this.imageInput = imageInput;
    this.imageSaver = imageSaver;
    this.input = input;
    this.imageHandler = imageHandler;
    this.output = output;
    input.setController(this);
  }

  @Override
  public void createSepiaImage(String name, String saveName) {

  }

  @Override
  public void ditherImage(String name, String saveName) {

  }

  @Override
  public void blurImage(String name, String saveName) {

  }

  @Override
  public void sharpenImage(String name, String saveImage) {

  }
}
