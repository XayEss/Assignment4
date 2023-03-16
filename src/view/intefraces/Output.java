package view.intefraces;

import model.interfaces.Image;

/**
 * The output interface defines methods for the view.
 */
public interface Output {

  void show(Image image);

  void print(String string);
}
