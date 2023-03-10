package model.implementation;

import java.util.Arrays;
import model.interfaces.Image;
import model.interfaces.Pixel;

public class ImageImpl implements Image {
  private Pixel[][] pixels;

  public ImageImpl(Pixel[][] pixels){
    this.pixels = pixels;
  }

  public ImageImpl(int height, int width){
    pixels = new Pixel[height][width];
  }


  @Override
  public Image getImageChannel(int channel) {
    Pixel[][] oneChannel = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++){
      for(int j = 0; j < getWidth(); j++){
        oneChannel[i][j] = pixels[i][j].getOneChanneledPixel(channel);
      }
    }
    return new ImageImpl(oneChannel);
  }

  @Override
  public Image flipImage(boolean horizontal) {
    Pixel[][] flippedPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        if (!horizontal) {
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
    Pixel[][] valuePixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        valuePixels[i][j] = new PixelGreyscale(pixels[i][j].getValue());
      }
    }
    return new ImageImpl(valuePixels);
  }

  @Override
  public Image getIntensityImage() {
    Pixel[][] intensityPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        intensityPixels[i][j] = new PixelGreyscale(pixels[i][j].getIntensity());
      }
    }
    return new ImageImpl(intensityPixels);
  }

  @Override
  public Image getLumaImage() {
    Pixel[][] lumaPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        lumaPixels[i][j] = new PixelGreyscale(pixels[i][j].getLuma());
      }
    }
    return new ImageImpl(lumaPixels);
  }

  @Override
  public Image alterBrightness(int value) {
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        pixels[i][j].alterBrightness(value);
      }
    }
    return this;
  }

  @Override
  public Pixel getPixel(int row, int column, int channel) {
    //Change method signature.
    return null;
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
    return null;
  }

  @Override
  public void setPixel(int row, int column, int channel, int value) {

  }

  @Override
  public String toString() {
    return Arrays.deepToString(pixels);
  }
}
