package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import model.implementation.NoSuchImageException;
import model.interfaces.ImageHandler;

public class CombineImages implements CommandHelper {
  String greenName;
  String blueName;

  public CombineImages(String greenName, String blueName) {
    this.greenName = greenName;
    this.blueName = blueName;
  }

  @Override
  public void execute(String name, String saveName, ImageHandler handler)
      throws NoSuchImageException {
    handler.combineGreyScaleImages(name, greenName, blueName, saveName);
  }
}
