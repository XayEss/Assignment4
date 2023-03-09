package model.implementation;

import model.interfaces.Image;
import model.interfaces.Pixel;

public class ImageImpl implements Image {

  @Override
  public Image getImageChannel(int channel) {
    return null;
  }

  @Override
  public Image flipImage(boolean horizontal) {
    return null;
  }

  @Override
  public Image getValueImage() {
    return null;
  }

  @Override
  public Image getIntensityImage() {
    return null;
  }

  @Override
  public Image getLumaImage() {
    return null;
  }

  @Override
  public Image alterBrightness(int value) {
    return null;
  }

  @Override
  public Pixel getPixel(int row, int column, int channel) {
    return null;
  }

  @Override
  public int getNumberOfChannels() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public String getSize() {
    return null;
  }

  @Override
  public void setPixel(int row, int column, int channel, int value) {

  }
}
