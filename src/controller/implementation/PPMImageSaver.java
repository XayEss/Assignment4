package controller.implementation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import controller.interfaces.ImageSaver;
import model.implementation.ImageConverter;
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

  @Override
  public void save(String path, InputStream stream) throws IOException {
    save(path, ImageConverter.convertFromBytes(stream));
    //    PrintWriter writer = new PrintWriter(path);
    //    byte[] heightBytes = new byte[4];
    //    byte[] widthBytes = new byte[4];
    //    stream.read(widthBytes);
    //    stream.read(heightBytes);
    //    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    //    buffer.put(heightBytes);
    //    buffer.rewind();
    //    int height = buffer.getInt();
    //    buffer.clear();
    //    buffer.put(widthBytes);
    //    buffer.rewind();
    //    int width = buffer.getInt();
    //    writer.println("P3");
    //    writer.println(width + " " + height);
    //    writer.println("255");

  }
}
