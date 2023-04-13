package controller.interfaces;

import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

/**
 * This interface represents a command in the Command Design Pattern. It provides a method to execute a command on an image.
 * The execute method takes in the name of the image to be processed, the name to save the processed image as, and an {@link ImageHandler} object
 * which contains methods to process the image.
 * <p>
 * The implementation of this interface is responsible for carrying out the specific command on the given image using the provided {@link ImageHandler}.
 * If the image with the given name does not exist, a {@link NoSuchImageException} is thrown.
 */
public interface CommandHelper {

  /**
   * Executes a command on an image with the given name, and saves the processed image with the provided save name.
   *
   * @param name     the name of the image to be processed
   * @param saveName the name to save the processed image as
   * @param handler  the {@link ImageHandler} object containing methods to process the image
   * @throws NoSuchImageException if an image with the given name does not exist
   */
  void execute(String name, String saveName, ImageHandler handler) throws NoSuchImageException;


}
