package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

public class SplitImage implements CommandHelper {
  String secondName;
  String thirdName;

  public SplitImage(String secondName, String thirdName) {
    this.secondName = secondName;
    this.thirdName = thirdName;
  }

  @Override
  public void execute(String name, String saveName, ImageHandler handler)
          throws NoSuchImageException {
    handler.getSplitChannels(name, saveName, secondName, thirdName);
  }
}
