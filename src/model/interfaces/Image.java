package model.interfaces;

/**
 * An interface that provides operations with an image.
 */
public interface Image {

  /**
   * Returns a one channeled image.
   * @param channel the channel to get from the image.
   * @return a new image made from the specified channel.
   */
  Image getImageChannel(int channel);

  /**
   * Flips the image horizontally if horizontal is true, otherwise vertical.
   * @param horizontal the axis to flip.
   * @return a new image, obtained by flipping the given one.
   */
  Image flipImage(boolean horizontal);

  /**
   * Gets an image that visualizes the Values of each pixel.
   * @return a new image which visualizes the Value.
   */
  Image getValueImage();

  /**
   * Calculates and returns an image that visualizes the intensity of the image.
   * @return a new image visualization of intensity.
   */
  Image getIntensityImage();

  /**
   Calculates and returns an image that visualizes the luma of the image.
   * @return a new image visualization of luma.
   */
  Image getLumaImage();

  /**
   * Changes the brightness of the image by a given amount.
   * @param increase increase or decrease.
   * @param value the amount to change.
   * @return a new image with the altered brightness.
   */
  Image alterBrightness(boolean increase, int value);

  /**
   * Returns a pixel channel value in a given row and column.
   * @param row the row of the pixel.
   * @param column the column of the pixel.
   * @param channel the channel to be returned.
   * @return A new pixel with the chosen channel.
   */
  Pixel getPixel(int row, int column, int channel);

  /**
   * Returns the number of channels of the image.
   * @return the int number of channel.
   */
  int getNumberOfChannels();

  /**
   * Returns the height of the image.
   * @return int height of the image.
   */
  int getHeight();

  /**
   * Returns the width of the image.
   * @return int width of the image.
   */
  int getWidth();

  /**
   * ???????
   * @return
   */
  String getSize();

  /**
   * Sets a pixel channel value to the value given.
   * @param row the row of the pixel.
   * @param column the column of the pixel.
   * @param channel the channel to change.
   * @param value the value to be set.
   */
  void setPixel(int row, int column, int channel, int value);

}
