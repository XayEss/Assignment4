package controller.interfaces;

import java.io.IOException;
import java.io.InputStream;
import model.interfaces.Image;

/**
 * This interface represents functionality for reading image files.
 */
public interface ImageInput {
  InputStream readFile(String filename) throws IOException;
}
