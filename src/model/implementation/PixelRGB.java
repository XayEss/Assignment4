package model.implementation;

import model.interfaces.Pixel;

public class PixelRGB implements Pixel {
  private int red;
  private int green;
  private int blue;

  public PixelRGB(int r, int g, int b) throws IllegalArgumentException{
    checkBounds(r);
    checkBounds(g);
    checkBounds(b);
    red = r;
    green = g;
    blue = b;
  }
  public PixelRGB(Pixel pixel){
    if(pixel.getNumberOfChannels() == 3){
      red = pixel.getChannel(0);
      green = pixel.getChannel(1);
      blue = pixel.getChannel(2);
    }
    else if(pixel.getNumberOfChannels() == 1) {
      red = pixel.getChannel(0);
      green = pixel.getChannel(0);
      blue = pixel.getChannel(0);
    }
  }

  public PixelRGB(int value){
      checkBounds(value);
      red = value;
      green = value;
      blue = value;

  }

  @Override
  public int getChannel(int channel) {
    return getChannelValue(channel);
  }

  @Override
  public void setChannel(int channel, int value) {
    setChannelValue(channel, value);
  }

  @Override
  public Pixel getOneChanneledPixel(int channel) {
    checkChannelArgument(channel);
    int r = 0;
    int g = 0;
    int b = 0;
    if(channel == 0){
      r = red;
    }else if(channel == 1){
      g = green;
    }else{
      b = blue;
    }
    return new PixelRGB(r, g, b);
  }

  @Override
  public void alterValue(int channel, int value) {
    int val = getChannelValue(channel);
    setChannelValue(channel, val + value);
  }

  @Override
  public Pixel alterBrightness(int value) {
    red = calculateChangeValue(red, value);
    green = calculateChangeValue(green, value);
    blue = calculateChangeValue(blue, value);
    return new PixelRGB(red, green, blue);
  }

  @Override
  public int getValue() {
    return Math.max(red, Math.max(blue, green));
  }

  @Override
  public int getIntensity() {
    return (red + green + blue) / 3;
  }

  @Override
  public int getLuma() {
    return (int)(0.0126 * red + 0.7152 * green + 0.0722 * blue);
  }

  @Override
  public int getGreyScale() {
    //return (int)(0.3 * red + 0.59 * green + 0.11 * blue);
    return (red + green + blue)/3;
  }

  @Override
  public int getNumberOfChannels() {
    return 3;
  }

  @Override
  public String toString() {
    return red + " " + green + " " + blue;
  }

  private void checkBounds(int parameter) throws IllegalArgumentException{
    if(parameter < 0 || parameter > 255){
      throw new IllegalArgumentException("Any RGB component value should be from 0 to 255");
    }
  }

  private void checkChannelArgument(int channel) throws IllegalArgumentException{
    if(channel > 2 || channel < 0){
      throw new IllegalArgumentException("Channel can not be more than 2 and less than 0");
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

  private int getChannelValue(int channel){
    int chan = 0;
    switch (channel){
      case 0:
        chan = red;
        break;
      case 1:
        chan = green;
        break;
      case 2:
        chan = blue;
        break;
    }
    return chan;
  }

  private void setChannelValue(int channel, int value){
    checkBounds(value);
    int chan = 0;
    switch (channel){
      case 0:
        red = value;
        break;
      case 1:
        green = value;
        break;
      case 2:
        blue = value;
        break;
    }
  }
}
