package controller.interfaces;

import model.interfaces.ImageHandler;

/**
 * A Command Design Pattern to represent a command.
 */
public interface CommandHelper {

  void execute(String name);

  void setHandler(ImageHandler handler);

}
