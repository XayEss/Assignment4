package view.intefraces;

import controller.interfaces.TransformationController;
import java.io.IOException;
import java.io.InputStream;
import model.interfaces.Image;

/**
 * The output interface defines methods for the view.
 */
public interface Output {

  void show(InputStream image) throws IOException;

  void print(String string);

  void setController(TransformationController controller);
}
