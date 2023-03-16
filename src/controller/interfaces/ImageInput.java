package controller.interfaces;

import model.interfaces.Image;

/**
 * This interface represents functionality for reading image files.
 */
public interface ImageInput {
  Image readFile(String filename);
}
