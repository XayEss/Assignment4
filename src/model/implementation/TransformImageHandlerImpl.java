package model.implementation;

import model.interfaces.ImageProcessor;
import model.interfaces.TransformImageHandler;

/**
 * An image handler implementation with transformation support.
 */
public class TransformImageHandlerImpl extends ImageHandlerImpl implements TransformImageHandler {

  public TransformImageHandlerImpl(ImageProcessor processor) {
    super(processor);
  }

  @Override
  public void sepiaToneImage(String name, String saveName) throws NoSuchImageException {
    saveWithName(saveName, imageProcessor.sepiaTone(getByName(name)));
  }

  @Override
  public void ditherImage(String name, String saveName) throws NoSuchImageException {
    saveWithName(saveName, imageProcessor.dither(getByName(name).dither()));
  }

  @Override
  public void blurImage(String name, String saveName) throws NoSuchImageException {
    saveWithName(saveName, imageProcessor.applyBlur(getByName(name)));
  }

  @Override
  public void sharpenImage(String name, String saveName) throws NoSuchImageException {
    saveWithName(saveName, imageProcessor.applySharpening(getByName(name)));
  }
}
