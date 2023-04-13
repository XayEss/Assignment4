package view.intefraces;

import java.io.IOException;
import java.io.InputStream;

import controller.interfaces.TransformationController;

/**
 * The output interface defines methods for the view.
 */
public interface Output {

  void show(InputStream image) throws IOException;

  void print(String string);

  void setController(TransformationController controller);
}
