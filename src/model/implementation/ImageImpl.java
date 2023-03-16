package model.implementation;

import java.util.Arrays;
import java.util.function.Function;

import model.interfaces.Image;
import model.interfaces.Pixel;

/**
 * Implementation of the Image interface that represents an image as a 2D array of Pixels.
 */
public class ImageImpl implements Image {
  private final Pixel[][] pixels;

  public ImageImpl(Pixel[][] pixels) {
    this.pixels = pixels;
  }

  public ImageImpl(int height, int width) {
    pixels = new Pixel[height][width];
  }


  @Override
  public Image getImageChannel(int channel) {
    return manipulationHelper(p -> p.getOneChanneledPixel(channel));
  }

  @Override
  public Image flipImage(boolean horizontal) {
    Pixel[][] flippedPixels = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        if (horizontal) {
          flippedPixels[i][j] = pixels[i][getWidth() - 1 - j];
        } else {
          flippedPixels[i][j] = pixels[getHeight() - 1 - i][j];
        }
      }
    }
    return new ImageImpl(flippedPixels);
  }

  @Override
  public Image getValueImage() {
    return manipulationHelper(p -> new PixelRGB(p.getValue()));
  }

  @Override
  public Image getIntensityImage() {
    return manipulationHelper((p) -> new PixelRGB(p.getIntensity()));
  }

  @Override
  public Image getLumaImage() {
    return manipulationHelper((p) -> new PixelRGB(p.getLuma()));
  }

  @Override
  public Image alterBrightness(int value) {
    return manipulationHelper(p -> p.alterBrightness(value));
  }

  private Image manipulationHelper(Function<Pixel, Pixel> function) {
    Pixel[][] changedPixels = new Pixel[getHeight()][getWidth()];
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        changedPixels[i][j] = function.apply(pixels[i][j]);
      }
    }
    return new ImageImpl(changedPixels);
  }

  @Override
  public Pixel getPixel(int row, int column) {
    return new PixelRGB(pixels[row][column]);
  }

  @Override
  public int getPixelChannel(int row, int column, int channel) {
    return pixels[row][column].getChannel(channel);
  }

  @Override
  public int getNumberOfChannels() {
    return pixels[0][0].getNumberOfChannels();
  }

  @Override
  public int getHeight() {
    return pixels.length;
  }

  @Override
  public int getWidth() {
    return pixels[0].length;
  }

  @Override
  public String getSize() {
    return "" + this.getWidth() + " x " + this.getHeight();
  }

  @Override
  public void setPixel(int row, int column, int channel, int value) {
    pixels[row][column].setChannel(channel, value);
  }

  @Override
  public Image getGreyscaleImage() {
    return manipulationHelper(p -> new PixelRGB(p.getGreyScale()));
  }

  @Override
  public byte[] getBytes() {
    byte[] byteImage = new byte[getHeight() * getWidth() * 3];
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        byteImage[i * getWidth() * 3 + j * 3] = (byte) pixels[i][j].getChannel(0);
        byteImage[i * getWidth() * 3 + j * 3 + 1] = (byte) pixels[i][j].getChannel(1);
        byteImage[i * getWidth() * 3 + j * 3 + 2] = (byte) pixels[i][j].getChannel(2);
      }
    }
    return byteImage;
  }


  @Override
  public String toString() {
    return Arrays.deepToString(pixels);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageImpl image = (ImageImpl) o;
    return Arrays.deepEquals(pixels, image.pixels);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(pixels);
  }


}
