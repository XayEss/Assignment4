package model.implementation;

import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;

import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.ImageProcessor;

/**
 * Implementation of the ImageHandler interface.
 * This class handles image processing operations such as flipping, splitting channels,
 * and combining grayscale images.
 * It uses an ImageProcessor object to perform the actual processing on images.
 */
public class ImageHandlerImpl implements ImageHandler {
  private final Map<String, Image> nameToImage;
  private ImageProcessor imageProcessor;

  public ImageHandlerImpl(ImageProcessor processor) {
    nameToImage = new HashMap<>();
    imageProcessor = processor;
  }

  @Override
  public void getChannel(String name, int channel, String saveName) {
    saveWithName(saveName, imageProcessor.getChannel(getByName(name), channel));
  }

  @Override
  public void flipImage(String name, boolean horizontal, String saveName) {
    saveWithName(saveName, imageProcessor.flipImage(getByName(name), horizontal));
  }

  @Override
  public void getValue(String name, String saveName) {
    saveWithName(saveName, imageProcessor.getValue(getByName(name)));
  }

  @Override
  public void getIntensity(String name, String saveName) {
    saveWithName(saveName, imageProcessor.getIntensity(getByName(name)));
  }

  @Override
  public void getLuma(String name, String saveName) {
    saveWithName(saveName, imageProcessor.getLuma(getByName(name)));
  }

  @Override
  public void alterBrightness(String name, int value, String saveName) {
    saveWithName(saveName, imageProcessor.alterBrightness(getByName(name), value));
  }

  @Override
  public void getGreyscale(String name, String saveName) {
    saveWithName(saveName, imageProcessor.getGreyscale(getByName(name)));
  }

  @Override
  public void getSplitChannels(String name, String redSaveName,
                               String greenSaveName, String blueSaveName) {
    Image[] split = imageProcessor.getSplitChannels(getByName(name));
    saveWithName(redSaveName, split[0]);
    saveWithName(greenSaveName, split[1]);
    saveWithName(blueSaveName, split[2]);
  }

  @Override
  public void combineGreyScaleImages(String redName, String greenName,
                                     String blueName, String saveName) {
    saveWithName(saveName, imageProcessor.combineGreyScaleImages(getByName(redName),
            getByName(greenName), getByName(blueName)));
  }

  @Override
  public Image getByName(String name) {
    if (!nameToImage.containsKey(name)) {
      System.out.println("No such file name");
//      throw new NoSuchObjectException("no image under name " + name);
    }
    return nameToImage.get(name);
  }

  @Override
  public void saveWithName(String name, Image image) {
    nameToImage.put(name, image);
  }
}
