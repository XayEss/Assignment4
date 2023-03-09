package model.implementation;

import model.interfaces.Pixel;

public class PixelGreyscale implements Pixel {
  private int greyscale;

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
  public void alterValue(int channel, int value) {
    greyscale = calculateChangeValue(channel, value);
  }

  @Override
  public void alterBrightness(int value) {
    greyscale = calculateChangeValue(greyscale, value);
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

  private void checkChannelArgument(int channel) throws IllegalArgumentException{
    if(channel > 0){
      throw new IllegalArgumentException("Channel can not be more than 0");
    }
  }

  private void checkBounds(int channel) throws IllegalArgumentException{
    if(channel > 0){
      throw new IllegalArgumentException("Channel can not be more than 0");
    }
  }

  private int calculateChangeValue(int initialValue, int value){
    int returnValue;
    if (initialValue + value > 255){
      returnValue = 255;
    }else if(initialValue + value < 0){
      returnValue = 0;
    }else{
      returnValue = initialValue + value;
    }
    return returnValue;
  }
}
