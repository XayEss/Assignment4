package model.implementation;

import java.util.Arrays;
import java.util.function.Function;
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
//    Pixel[][] intensityPixels = new Pixel[getHeight()][getWidth()];
//    for(int i = 0; i < getHeight(); i++) {
//      for (int j = 0; j < getWidth(); j++) {
//        //intensityPixels[i][j] = new PixelGreyscale(pixels[i][j].getIntensity());
//        int intensity = pixels[i][j].getLuma();
//        //lumaPixels[i][j] = new PixelGreyscale(pixels[i][j].getLuma());
//        intensityPixels[i][j] = new PixelRGB(intensity,intensity,intensity);
//      }
//    }
//    return new ImageImpl(intensityPixels);
    return higherOrderFunction((p)->new PixelRGB(p.getIntensity()));
  }

  @Override
  public Image getLumaImage() {
    Pixel[][] lumaPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        int luma = pixels[i][j].getLuma();
        //lumaPixels[i][j] = new PixelGreyscale(pixels[i][j].getLuma());
        lumaPixels[i][j] = new PixelRGB(luma,luma,luma);
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

  private Image higherOrderFunction(Function<Pixel, Pixel> function){
    Pixel[][] lumaPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        int luma = pixels[i][j].getLuma();
        //lumaPixels[i][j] = new PixelGreyscale(pixels[i][j].getLuma());
        lumaPixels[i][j] = function.apply(pixels[i][j]);
      }
    }
    return new ImageImpl(lumaPixels);
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
    return null;
  }

  @Override
  public void setPixel(int row, int column, int channel, int value) {

  }

  @Override
  public Image getGreyscaleImage() {
    Pixel[][] greyPixels = new Pixel[getHeight()][getWidth()];
    for(int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        int grey = pixels[i][j].getGreyScale();
        greyPixels[i][j] = new PixelRGB(grey, grey, grey);
      }
    }
    return new ImageImpl(greyPixels);
  }

  @Override
  public byte[] getBytes() {
    byte[] byteImage = new byte[getHeight()*getWidth()*3];
    for(int i = 0; i < getHeight(); i++){
      for(int j = 0; j < getWidth(); j++){
        byteImage[i*getWidth()*3+j*3] = (byte)pixels[i][j].getChannel(0);
        byteImage[i*getWidth()*3+j*3+1] = (byte)pixels[i][j].getChannel(1);
        byteImage[i*getWidth()*3+j*3+2] = (byte)pixels[i][j].getChannel(2);
      }
    }
    return byteImage;
  }

  @Override
  public String toString() {
    return Arrays.deepToString(pixels);
  }
}