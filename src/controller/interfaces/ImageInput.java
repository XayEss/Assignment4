package controller.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * This interface represents functionality for reading image files.
 */
public interface ImageInput {

  /**
   * Reads an image file and returns an {@link InputStream} of the contents of the file.
   *
   * @param filename the name of the image file to be read
   * @return an {@link InputStream} of the contents of the image file
   * @throws IOException if the file cannot be read
   */
  InputStream readFile(String filename) throws IOException;
}
