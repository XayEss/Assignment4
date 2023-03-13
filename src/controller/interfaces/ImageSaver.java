package controller.interfaces;

import model.interfaces.Image;

public interface ImageSaver {

  void save(String path, Image image);

}
