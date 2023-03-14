package controller.interfaces;

import java.io.FileNotFoundException;
import model.interfaces.Image;

public interface ImageSaver {

  void save(String path, Image image) throws FileNotFoundException;

}
