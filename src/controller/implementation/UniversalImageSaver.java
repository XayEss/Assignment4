package controller.implementation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import controller.interfaces.ImageSaver;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;

/**
 * This class contains functionality to save images in many formats.
 */
public class UniversalImageSaver implements ImageSaver {

  @Override
  public void save(String path, Image image) throws FileNotFoundException {
    // Future Scope
  }

  @Override
  public void save(String path, InputStream stream) throws IOException {
    String format = path.substring(path.lastIndexOf('.') + 1);
    if (format.equals("ppm")) {
      new PPMImageSaver().save(path, stream);
    } else {
      saveConventional(path, format, stream);
    }
  }

  private void saveConventional(String path, String format, InputStream stream) throws IOException {
    BufferedImage image = ImageToBufferedImageService.toBuffered(stream);
    ImageIO.write(image, format, new File(path));
  }
}
