package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

public class LumaImage implements CommandHelper {

  @Override
  public void execute(String name, String saveName, ImageHandler handler)
      throws NoSuchImageException {

  }
}
