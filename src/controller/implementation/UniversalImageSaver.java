package controller.implementation;

import controller.interfaces.ImageSaver;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;

public class UniversalImageSaver implements ImageSaver {

  @Override
  public void save(String path, Image image) throws FileNotFoundException {

  }

  @Override
  public void save(String path, InputStream stream) throws IOException {
    BufferedImage image = ImageToBufferedImageService.toBuffered(stream);
    String format = path.substring(path.lastIndexOf('.')+1);
    ImageIO.write(image, format, new File(path));
  }
}
