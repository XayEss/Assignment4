package model.interfaces;

/**
 * An interface that processes images and does operation on them.
 */
public interface ImageProcessor {
  /**
   * Returns a one channeled image.
   *
   * @param image   the image to get one channel from.
   * @param channel the channel to get from the image.
   * @return a new image made from the specified channel.
   */
  Image getChannel(Image image, int channel);

  /**
   * Flips the image horizontally if horizontal is true, otherwise vertical.
   *
   * @param image      the image to flip.
   * @param horizontal the axis to flip.
   * @return a new image, obtained by flipping the given one.
   */
  Image flipImage(Image image, boolean horizontal);

  /**
   * Gets an image that visualizes the Values of each pixel.
   *
   * @param image the image to get the value image from.
   * @return a new image which visualizes the Value.
   */
  Image getValue(Image image);

  /**
   * Calculates and returns an image that visualizes the intensity of the image.
   *
   * @param image the image from which the visualization of intensity will be made.
   * @return a new image visualization of intensity.
   */
  Image getIntensity(Image image);

  /**
   * Calculates and returns an image that visualizes the luma of the image.
   *
   * @param image the image from which the visualization of luma will be made.
   * @return a new image visualization of luma.
   */
  Image getLuma(Image image);

  /**
   * Changes the brightness of the image by a given amount.
   *
   * @param image image which will be changed.
   * @param value the amount to change.
   * @return a new image with the altered brightness.
   */
  Image alterBrightness(Image image, int value);

  /**
   * Creates a new greyscale image of the given image.
   *
   * @param image the image to make a greyscale from.
   * @return a new greyscale image.
   */
  Image getGreyscale(Image image);

  /**
   * Splits an image into all it's separate channels.
   *
   * @param image the image to split.
   * @return a list or images, which are the channels of the given image.
   */
  Image[] getSplitChannels(Image image);

  /**
   * Combines three greyscale images into one three channeled image.
   *
   * @param first  the first image to combine.
   * @param second the second image to combine.
   * @param third  the third image to combine.
   * @return a new three channeled image
   */
  Image combineGreyScaleImages(Image first, Image second, Image third);

  Image sepiaTone(Image image);

  /**
   * Applies a filter to the image.
   *
   * @param image  the image to apply the filter to.
   * @param sizeX  the width of the filter matrix.
   * @param sizeY  the height of the filter matrix.
   * @param filter the filter matrix.
   * @return a new image with the filter applied.
   */
  Image applyFilter(Image image, int sizeX, int sizeY, float[][] filter);

  /**
   * Applies a blur effect to the given image using a Gaussian blur filter.
   *
   * @param image the image to which the blur effect will be applied.
   * @return a new Image with the blur effect applied.
   */
  Image applyBlur(Image image);

  /**
   * Applies a sharpening effect to the given image using a custom sharpening filter.
   *
   * @param image the image to which the sharpening effect will be applied.
   * @return a new Image with the sharpening effect applied.
   */
  Image applySharpening(Image image);
}
