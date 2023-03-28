package model.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import model.interfaces.Image;
import model.interfaces.Pixel;

public class ImageConverter {

  public static Image convertFromBytes(InputStream stream) throws IOException {
    Pixel[][] pixels;
    byte[] heightBytes = new byte[4];
    byte[] widthBytes = new byte[4];
    stream.read(widthBytes);
    stream.read(heightBytes);
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.put(heightBytes);
    buffer.rewind();
    int height = buffer.getInt();
    buffer.clear();
    buffer.put(widthBytes);
    buffer.rewind();
    int width = buffer.getInt();
    System.out.println("parameters" + height + " " + width);
    pixels = new PixelRGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new PixelRGB(stream.read(), stream.read(), stream.read());
      }
    }

    return new ImageImpl(pixels);
  }

  public static InputStream convertToBytes(Image image) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Pixel[][] pixels;
    byte[] heightBytes = new byte[4];
    byte[] widthBytes = new byte[4];
    ByteBuffer buffer = ByteBuffer.allocate(4);
    heightBytes = buffer.putInt(image.getHeight()).array();
    buffer.clear();
    widthBytes = buffer.putInt(image.getWidth()).array();
    outputStream.write(widthBytes);
    outputStream.write(heightBytes);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel pixel = image.getPixel(i, j);
        outputStream.write(pixel.getChannel(0));
        outputStream.write(pixel.getChannel(1));
        outputStream.write(pixel.getChannel(2));
      }
    }

    return new ByteArrayInputStream(outputStream.toByteArray());
  }


}
