package controller.interfaces;

/**
 * Interface for type which controls the main program flow.
 */
public interface Controller {

  /**
   * Starts the controller's operation.
   */
  void start();

  /**
   * Gets a one channeled picture by its name.
   *
   * @param name    the name of the image.
   * @param channel the channel to get from the picture.
   */
  void separateImageChannel(String name, String resultName, int channel);

  /**
   * Flips the image horizontally if horizontal is true, otherwise vertical.
   *
   * @param name       name of the image to flip.
   * @param horizontal the axis to flip.
   */
  void createFlippedImage(String name, String resultName, boolean horizontal);

  /**
   * Gets an image that visualizes the Values of each pixel.
   *
   * @param name name of the image to get the value image from.
   */
  void createValueImage(String name, String resultName);

  /**
   * Calculates and returns an image that visualizes the intensity of the image.
   *
   * @param name name of the image from which the visualization of intensity will be made.
   */
  void createIntensityImage(String name, String resultName);

  /**
   * Calculates and returns an image that visualizes the luma of the image.
   *
   * @param name name of the image from which the visualization of luma will be made.
   */
  void createLumaImage(String name, String resultName);

  /**
   * Changes the brightness of the image by a given amount.
   *
   * @param name  name of image which will be changed.
   * @param value the amount to change.
   */
  void alterImageBrightness(String name, String resultName, int value);

  /**
   * Commands to create a greyscale image of an image with the given name and save under a new name.
   *
   * @param name       the of the image stored in the program.
   * @param resultName the name under which to save the new image.
   */
  void createGreyScaleImage(String name, String resultName);

  /**
   * Splits an image into all it's separate channels.
   *
   * @param name name of the image to split.
   */
  void splitImageChannels(String name, String redResultName, String greenResultName,
                          String blueResultName);

  /**
   * Combines three greyscale images into one three channeled image.
   *
   * @param redName   name of the first image to combine.
   * @param greenName name of the second image to combine.
   * @param blueName  name of the third image to combine.
   */
  void combineGreyScaleImages(String redName, String greenName, String blueName, String resultName);

  /**
   * Load image with the given path and save within the program with the given name.
   *
   * @param path the path from which to load the image.
   * @param name the name to save in the program.
   */
  void loadImage(String path, String name);

  /**
   * Save image with the given name to the filesystem with the save name.
   *
   * @param path the path where to save the image.
   * @param name the image name in the program.
   */
  void saveImage(String path, String name);

  /**
   * Runs a list of commands from the chosen file.
   *
   * @param path the script to run.
   */
  void runScript(String path);

}
