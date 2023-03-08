package model.interfaces;

/**
 * Interface that represents a single pixel. The type of pixel depends on the implementation.
 */
public interface Pixel {

  /**
   * Returns the value of a certain channel.
   * @param channel the channel to be returned.
   * @return integer value of the chosen channel.
   */
  int getChannel(int channel);

  /**
   * Sets the channel value to the given value.
   * @param channel the channel to set.
   * @param value the value to be set.
   */
  void setChannel(int channel, int value);

  /**
   * Change the value of a chosen channel by the given amount.
   * @param channel the channel to change.
   * @param value the value by which to change the channel.
   */
  void alterValue(int channel, int value);

  /**
   * Change the brightness of the pixel. Negative values decrease the brightness, positive
   * - increase.
   * @param value the value by which to change the brightness.
   */
  void alterBrightness(int value);

  /**
   * Gets the value(the highest channel value) of the pixel.
   * @return the value.
   */
  int getValue();

  /**
   * Calculate and return the intensity of the pixel.
   * @return the intensity of the pixel.
   */
  int getIntensity();

  /**
   * Calculates and returns the Luma parameter of the pixel.
   * @return the luma parameter of the pixel.
   */
  int getLuma();

  /**
   * Calculates and returns the grayscale version of the pixel.
   * @return the grayscale int parameter of the pixel.
   */
  int getGreyScale();
}
