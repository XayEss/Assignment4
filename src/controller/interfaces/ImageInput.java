package controller.interfaces;

import model.interfaces.Image;

public interface ImageInput {
  Image readFile(String filename);
}
