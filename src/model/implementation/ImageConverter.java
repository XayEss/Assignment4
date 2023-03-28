package model.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import model.interfaces.Image;
import model.interfaces.Pixel;

/**
 * A service class that provides Images to InputStream conversion and back.
 */
public class ImageConverter {

  /**
   * Converts an InputStream to an Image. First 4 bytes have to be the width of the image,
   * next 4 bytes - the height. After, each byte represents an r, g, b component of a pixel.
   * @param stream the stream to read the data from.
   * @return an Image created from the data of the InputStream.
   * @throws IOException if a stream reading exception occurs.
   */
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
    //System.out.println("parameters" + height + " " + width);
    pixels = new PixelRGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = new PixelRGB(stream.read(), stream.read(), stream.read());
      }
    }

    return new ImageImpl(pixels);
  }
  /**
   * Converts an Image to an InputStream. First 4 bytes will be written the width of the image,
   * next 4 bytes - the height. After, each byte represents an r, g, b component of a pixel.
   * @param image the image to convert.
   * @return an InputStream created from the data of the Image.
   * @throws IOException if a stream writing exception occurs.
   */
  public static InputStream convertToBytes(Image image) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] heightBytes;
    byte[] widthBytes;
    System.out.println(image.getHeight());
    heightBytes = ByteBuffer.allocate(4).putInt(image.getHeight()).array();
    widthBytes = ByteBuffer.allocate(4).putInt(image.getWidth()).array();
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
