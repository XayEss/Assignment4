package controller.implementation;

import java.io.IOException;
import java.io.InputStream;

import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;
import view.intefraces.Output;

/**
 * An implementation of the controller for image manipulation.
 */
public class ControllerImpl implements Controller {

  private final ImageHandler imageHandler;
  protected ImageInput imageInput;
  protected ImageSaver imageSaver;
  protected Input input;
  protected Output output;


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
    input.setController(this);
    input.startCommandReading();
  }

  @Override
  public void separateImageChannel(String name, String resultName, int channel) {
    try {
      imageHandler.getChannel(name, channel, resultName);
      printInfo("Successfully created a new image with a separate channel");

    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void createFlippedImage(String name, String resultName, boolean horizontal) {
    try {
      imageHandler.flipImage(name, horizontal, resultName);
      printInfo("Image successfully flipped");

    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void createValueImage(String name, String resultName) {
    try {
      imageHandler.getValue(name, resultName);
      printInfo("Successfully created a value image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void createIntensityImage(String name, String resultName) {
    try {
      imageHandler.getIntensity(name, resultName);
      printInfo("Successfully created a intensity image");

    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void createLumaImage(String name, String resultName) {
    try {
      imageHandler.getLuma(name, resultName);
      printInfo("Successfully created a luma image");

    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void alterImageBrightness(String name, String resultName, int value) {
    try {
      imageHandler.alterBrightness(name, value, resultName);
      printInfo("Successfully created an image with changed brightness");

    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void createGreyScaleImage(String name, String resultName) {
    try {
      imageHandler.getGreyscale(name, resultName);
      printInfo("Successfully created a greyscale image");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void splitImageChannels(String name, String redResultName,
                                 String greenResultName, String blueResultName) {
    try {
      imageHandler.getSplitChannels(name, redResultName, greenResultName, blueResultName);
      printInfo("Successfully split a image into r, g, b components");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                     String resultName) {
    try {
      imageHandler.combineGreyScaleImages(redName, greenName, blueName, resultName);
      printInfo("Successfully combined greyscale images");
    } catch (NoSuchImageException e) {
      printInfo(e.getMessage());
    }
  }


  @Override
  public void loadImage(String path, String name) {
    InputStream image = null;
    try {
      image = imageInput.readFile(path);
    } catch (IOException e) {
      printInfo("Could not read the file");
    }
    if (image != null) {
      try {
        imageHandler.importImage(name, image);
        printInfo("Loaded the image");
        // showImage(name);
      } catch (IOException e) {
        printInfo("Error reading image stream");
      } 
    } else {
      printInfo("No file with path " + path + " found");
    }
  }

  @Override
  public void saveImage(String path, String name) {
    try {
      imageSaver.save(path, imageHandler.exportImage(name));
      printInfo("Successfully saved image");
    } catch (NoSuchImageException | IOException e) {
      printInfo(e.getMessage());
    }
  }

  protected void showImage(String name) throws IOException, NoSuchImageException {
    output.show(imageHandler.exportImage(name));
  }
  
  protected void printInfo(String info){
    output.print(info);
  }

  @Override
  public void runScript(String path) {
    //System.out.println("in runScript!");
    //new VisualizeAscii().show(imageHandler.getByName(path));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered
    // (imageHandler.getByName(path)));

  }
}
