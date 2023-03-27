package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

public class FlipImage implements CommandHelper {
  private boolean horizontal;

  public FlipImage(boolean horizontal) {
    this.horizontal = horizontal;
  }

  @Override
  public void execute(String name, String saveName, ImageHandler handler)
      throws NoSuchImageException {
    handler.flipImage(name, horizontal, saveName);
  }
}
