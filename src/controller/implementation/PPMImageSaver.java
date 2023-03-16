package controller.implementation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import controller.interfaces.ImageSaver;
import model.interfaces.Image;

/**
 * This class implements the ImageSaver interface and provides functionality for saving PPM images
 * to a specified file path.
 * It utilizes a PrintWriter instance to write the image data to the file in PPM format.
 */
public class PPMImageSaver implements ImageSaver {

  @Override
  public void save(String path, Image image) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(path);
    writer.println("P3");
    writer.println(image.getWidth() + " " + image.getHeight());
    writer.println("255");
    int k = 0;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        writer.println(image.getPixelChannel(i, j, 0));
        writer.println(image.getPixelChannel(i, j, 1));
        writer.println(image.getPixelChannel(i, j, 2));
      }
    }
    writer.close();
  }
}
