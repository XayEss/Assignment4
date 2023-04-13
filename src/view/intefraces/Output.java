package view.intefraces;

import java.io.IOException;
import java.io.InputStream;

import controller.interfaces.TransformationController;

/**
 * The output interface defines methods for the view.
 */
public interface Output {

  /**
   * Shows the image on the given view.
   * @param image the inputstream with an image written to it.
   * @throws IOException if the data is not the written by the image format.
   */
  void show(InputStream image) throws IOException;

  /**
   * Prints a message to the view.
   * @param string
   */
  void print(String string);

  /**
   * Sets the controller of the view
   *
   * @param controller the controller the view will send the inputs.
   */
  void setController(TransformationController controller);

  /**
   * Displays the histogram to the view.
   * @param histogram
   */
  void showHistogram(int[][] histogram);
}
