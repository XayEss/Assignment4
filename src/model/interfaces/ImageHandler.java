package model.interfaces;

public interface ImageHandler {

  /**
   * Gets a one channeled picture by its name.
   * @param name the name of the image.
   * @param channel the channel to get from the picture.
   */
  void getChannel(String name, int channel, String saveName);

  /**
   * Flips the image horizontally if horizontal is true, otherwise vertical.
   * @param name name of the image to flip.
   * @param horizontal the axis to flip.
   */
  void flipImage(String name, boolean horizontal, String saveName);

  /**
   * Gets an image that visualizes the Values of each pixel.
   * @param name name of the image to get the value image from.
   */
  void getValue(String name, String saveName);

  /**
   * Calculates and returns an image that visualizes the intensity of the image.
   * @param name name of the image from which the visualization of intensity will be made.
   */
  void getIntensity(String name, String saveName);

  /**
   Calculates and returns an image that visualizes the luma of the image.
   * @param name name of the image from which the visualization of luma will be made.
   */
  void getLuma(String name, String saveName);

  /**
   * Changes the brightness of the image by a given amount.
   * @param name name of image which will be changed.
   * @param value the amount to change.
   */
  void alterBrightness(String name, int value, String saveName);

  /**
   * Splits an image into all it's separate channels.
   * @param name name of the image to split.
   */
  void getSplitChannels(String name, String redSaveName, String greenSaveName, String blueSaveName);

  /**
   * Combines three greyscale images into one three channeled image.
   * @param redName name of the first image to combine.
   * @param greenName name of the second image to combine.
   * @param blueName name of the third image to combine.
   */
  void combineGreyScaleImages(String redName, String greenName, String blueName, String saveName);

  Image getByName(String name);

  void saveWithName(String name, Image image);

}
