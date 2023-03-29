package controller.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.interfaces.Image;

/**
 * This interface represents functionality for saving images to a file.
 */
public interface ImageSaver {

  void save(String path, Image image) throws FileNotFoundException;

  void save(String path, InputStream stream) throws IOException;

}
