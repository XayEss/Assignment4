package model.interfaces;

public interface ImageHandler {

  /**
   * Gets a one channeled picture by its name.
   * @param name the name of the image.
   * @param channel the channel to get from the picture.
   * @return a new picture with only the chosen channel.
   */
  Image getChannel(String name, int channel);

  /**
   * Flips the image horizontally if horizontal is true, otherwise vertical.
   * @param name name of the image to flip.
   * @param horizontal the axis to flip.
   * @return a new image, obtained by flipping the given one.
   */
  Image flipImage(String name, boolean horizontal);

  /**
   * Gets an image that visualizes the Values of each pixel.
   * @param name name of the image to get the value image from.
   * @return a new image which visualizes the Value.
   */
  Image getValue(String name);

  /**
   * Calculates and returns an image that visualizes the intensity of the image.
   * @param name name of the image from which the visualization of intensity will be made.
   * @return a new image visualization of intensity.
   */
  Image getIntensity(String name);

  /**
   Calculates and returns an image that visualizes the luma of the image.
   * @param name name of the image from which the visualization of luma will be made.
   * @return a new image visualization of luma.
   */
  Image getLuma(String name);

  /**
   * Changes the brightness of the image by a given amount.
   * @param name name of image which will be changed.
   * @param value the amount to change.
   * @return a new image with the altered brightness.
   */
  Image alterBrightness(String name, int value);

  /**
   * Splits an image into all it's separate channels.
   * @param name name of the image to split.
   * @return a list or images, which are the channels of the given image.
   */
  Image[] getSplitChannels(String name);

  /**
   * Combines three greyscale images into one three channeled image.
   * @param redName name of the first image to combine.
   * @param greenName name of the second image to combine.
   * @param blueName name of the third image to combine.
   * @return a new three channeled image
   */
  Image combineGreyScaleImages(String redName, String greenName, String blueName);

  Image getByName(String name);

  void saveWithName(String name, Image image);

}
