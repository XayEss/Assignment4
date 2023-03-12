package model.implementation;

import java.util.HashMap;
import java.util.Map;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.ImageProcessor;

public class ImageHandlerImpl implements ImageHandler{
  private final Map<String, Image> nameToImage;
  private ImageProcessor imageProcessor;

  public ImageHandlerImpl(ImageProcessor processor){
    nameToImage = new HashMap<>();
    imageProcessor = processor;
  }

  @Override
  public Image getChannel(String name, int channel) {
    return imageProcessor.getChannel(getByName(name), channel);
  }

  @Override
  public Image flipImage(String name, boolean horizontal) {
    return imageProcessor.flipImage(getByName(name), horizontal);
  }

  @Override
  public Image getValue(String name) {
    return imageProcessor.getValue(getByName(name));
  }

  @Override
  public Image getIntensity(String name) {
    return imageProcessor.getIntensity(getByName(name));
  }

  @Override
  public Image getLuma(String name) {
    return imageProcessor.getLuma(getByName(name));
  }

  @Override
  public Image alterBrightness(String name, int value) {
    return imageProcessor.alterBrightness(getByName(name), value);
  }

  @Override
  public Image[] getSplitChannels(String name) {
    return imageProcessor.getSplitChannels(getByName(name));
  }

  @Override
  public Image combineGreyScaleImages(String redName, String greenName, String blueName) {
    return imageProcessor.combineGreyScaleImages(getByName(redName), getByName(greenName), getByName(blueName));
  }

  @Override
  public Image getByName(String name) {
    return nameToImage.get(name);
  }

  @Override
  public void saveWithName(String name, Image image) {
    nameToImage.put(name, image);
  }
}
