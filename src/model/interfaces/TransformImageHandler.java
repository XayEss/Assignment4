package model.interfaces;

import model.implementation.NoSuchImageException;

/**
 * Model class for handling transformations along with previous features.
 */
public interface TransformImageHandler extends ImageHandler{

  /**
   * Transforms the image into a sephia toned image and saves to the model.
   * @param name of image to transform.
   * @param saveName the new name to save under.
   */
  void sepiaToneImage(String name, String saveName) throws NoSuchImageException;

  /**
   * Dithers the image with name and saves under saveName.
   * @param name the name of the image to change.
   * @param saveName the name to save the new image under.
   * @throws NoSuchImageException when an image with the name can't be found.
   */
  void ditherImage(String name, String saveName) throws NoSuchImageException;

}
