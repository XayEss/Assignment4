package model.implementation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import model.interfaces.Image;
import model.interfaces.Pixel;

/**
 * A service for converting an Image to a BufferedImage.
 */
public class ImageToBufferedImageService {

  /**
   * Converts the given Image to a BufferedImage.
   *
   * @param image the Image to be converted
   * @return a BufferedImage representation of the Image
   */
  public static BufferedImage convertToBuffered(Image image) {
    return toBuffered(image);
  }


  /**
   * Converts an Image to a BufferedImage.
   *
   * @param image the image to convert.
   * @return a BufferedImage representation of the image.
   */
  static public BufferedImage toBuffered(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = image.getPixel(i, j);
        int red = (byte) pixel.getChannel(0) & 0xff;
        int green = (byte) pixel.getChannel(1) & 0xff;
        int blue = (byte) pixel.getChannel(2) & 0xff;
        int rgb = 0xff000000 + (red << 16) + (green << 8) + blue;
        //int rgb = (((((0xFF << 8) + red) << 8) + green) << 8)  + blue;
        //System.out.println(Integer.toBinaryString(rgb));
        //System.out.println(rgb);
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Converts an Image to a BufferedImage given an InputStream.
   * First 4 bytes have to be the width of the image, next 4 bytes - the height. After each byte
   * represents an r, g, b component.
   *
   * @param stream The stream from which to read the image data from.
   * @return a BufferedImage representation of the image.
   * @throws IOException if an error while reading the stream occurs.
   */
  static public BufferedImage toBuffered(InputStream stream) throws IOException {
    byte[] widthBytes = new byte[4];
    byte[] heightBytes = new byte[4];
    stream.read(widthBytes);
    stream.read(heightBytes);
    int width = ByteBuffer.allocate(Integer.BYTES).put(widthBytes).rewind().getInt();
    int height = ByteBuffer.allocate(Integer.BYTES).put(heightBytes).rewind().getInt();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = stream.read() & 0xff;
        int green = stream.read() & 0xff;
        int blue = stream.read() & 0xff;
        int rgb = 0xff000000 + (red << 16) + (green << 8) + blue;
        bufferedImage.setRGB(j, i, rgb);
      }

    }
    return bufferedImage;
  }


}
