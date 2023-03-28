package model.implementation;

import model.interfaces.Pixel;

/**
 * A class representing a pixel with one color channel.
 */
public class PixelGreyscale implements Pixel {

  private int greyscale;

  public PixelGreyscale(int scale) {
    greyscale = scale;
  }

  @Override
  public int getChannel(int channel) {
    checkChannelArgument(channel);
    return greyscale;
  }

  @Override
  public void setChannel(int channel, int value) {
    checkChannelArgument(channel);
    checkBounds(value);
    greyscale = value;
  }

  @Override
  public Pixel getOneChanneledPixel(int channel) {
    return new PixelGreyscale(greyscale);
  }

  @Override
  public void alterValue(int channel, int value) {
    greyscale = calculateChangeValue(channel, value);
  }

  @Override
  public PixelGreyscale alterBrightness(int value) {
    //greyscale = calculateChangeValue(greyscale, value);
    return null;
  }

  @Override
  public int getValue() {
    return greyscale;
  }

  @Override
  public int getIntensity() {
    return greyscale;
  }

  @Override
  public int getLuma() {
    return greyscale;
  }

  @Override
  public int getGreyScale() {
    return greyscale;
  }

  @Override
  public int getNumberOfChannels() {
    return 1;
  }

  @Override
  public Pixel linearlyTransform(double[][] transformation) {
    return null;
  }

  @Override
  public Pixel dither() {
    return null;
  }

  private void checkChannelArgument(int channel) throws IllegalArgumentException {
    if (channel != 0) {
      throw new IllegalArgumentException("Grayscale pixel has only one channel - 0");
    }
  }

  private void checkBounds(int channel) throws IllegalArgumentException {
    if (channel > 0) {
      throw new IllegalArgumentException("Channel can not be more than 0");
    }
  }

  private int calculateChangeValue(int initialValue, int value) {
    int returnValue;
    if (initialValue + value > 255) {
      returnValue = 255;
    } else if (initialValue + value < 0) {
      returnValue = 0;
    } else {
      returnValue = initialValue + value;
    }
    return returnValue;
  }

  @Override
  public String toString() {
    return "" + greyscale;
  }
}
