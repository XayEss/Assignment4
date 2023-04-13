package controller.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.interfaces.Image;

/**
 * This interface represents functionality for saving images to a file.
 */
public interface ImageSaver {

  /**
   * Saves the provided {@link Image} object to the file at the given path.
   *
   * @param path  the path to save the image to
   * @param image the {@link Image} object representing the image to be saved
   * @throws FileNotFoundException if the file cannot be found or created
   */
  void save(String path, Image image) throws FileNotFoundException;

  /**
   * Saves the provided {@link InputStream} object as an image to the file at the given path.
   *
   * @param path   the path to save the image to
   * @param stream the {@link InputStream} object representing the contents of the image to be saved
   * @throws IOException if an I/O error occurs while writing to the file
   */
  void save(String path, InputStream stream) throws IOException;

}
