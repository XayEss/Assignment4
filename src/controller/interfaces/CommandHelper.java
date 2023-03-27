package controller.interfaces;

import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

/**
 * A Command Design Pattern to represent a command.
 */
public interface CommandHelper {

  void execute(String name, String saveName, ImageHandler handler) throws NoSuchImageException;


}
