package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

public class BrightenImage implements CommandHelper {
  private final int amount;

  public BrightenImage(int amount) {
    this.amount = amount;
  }

  @Override
  public void execute(String name, String saveName, ImageHandler handler)
          throws NoSuchImageException {
    handler.alterBrightness(name, amount, saveName);
  }

}
