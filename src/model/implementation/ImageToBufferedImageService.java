package model.implementation;

import java.awt.Color;
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
    return ppm(image.getWidth(), image.getHeight(), 255, image.getBytes());
  }

  /**
   * Converts a PPM format image represented by byte array data to a BufferedImage.
   *
   * @param width     the width of the image
   * @param height    the height of the image
   * @param maxcolval the maximum color value of the image
   * @param data      the byte array representation of the image
   * @return a BufferedImage representation of the PPM image
   */
  //TODO: rewrite this code. Not include into submission until rewritten.
  static private BufferedImage ppm(int width, int height, int maxcolval, byte[] data) {
    if (maxcolval < 256) {
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      int pixel;
      int r = 0;
      int g = 0;
      int b = 0;
      int k = 0;

      if (maxcolval == 255) {                                      // don't scale
        for (int y = 0; y < height; y++) {
          for (int x = 0; (x < width) && ((k + 3) < data.length); x++) {
            r = data[k++] & 0xFF;
            g = data[k++] & 0xFF;
            b = data[k++] & 0xFF;
            pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
            image.setRGB(x, y, pixel);
          }
        }
      } else {
        for (int y = 0; y < height; y++) {
          for (int x = 0; (x < width) && ((k + 3) < data.length); x++) {
            r = data[k++] & 0xFF;
            r = ((r * 255) + (maxcolval >> 1)) / maxcolval;  // scale to 0..255 range
            g = data[k++] & 0xFF;
            g = ((g * 255) + (maxcolval >> 1)) / maxcolval;
            b = data[k++] & 0xFF;
            b = ((b * 255) + (maxcolval >> 1)) / maxcolval;
            pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
            image.setRGB(x, y, pixel);
          }
        }
      }
      return image;
    } else {

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      int pixel;
      int r = 0;
      int g = 0;
      int b = 0;
      int k = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; (x < width) && ((k + 6) < data.length); x++) {
          r = (data[k++] & 0xFF) | ((data[k++] & 0xFF) << 8);
          r = ((r * 255) + (maxcolval >> 1)) / maxcolval;  // scale to 0..255 range
          g = (data[k++] & 0xFF) | ((data[k++] & 0xFF) << 8);
          g = ((g * 255) + (maxcolval >> 1)) / maxcolval;
          b = (data[k++] & 0xFF) | ((data[k++] & 0xFF) << 8);
          b = ((b * 255) + (maxcolval >> 1)) / maxcolval;
          pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
          image.setRGB(x, y, pixel);
        }
      }
      return image;
    }
  }

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

  static public BufferedImage toBuffered(InputStream stream) throws IOException {
    byte[] widthBytes = new byte[4];
    byte[] heightBytes = new byte[4];
    stream.read(widthBytes);
    stream.read(heightBytes);
    int width = ByteBuffer.allocate(Integer.BYTES).put(widthBytes).rewind().getInt();
    int height = ByteBuffer.allocate(Integer.BYTES).put(heightBytes).rewind().getInt();
    System.out.println(height + " " + width);
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red =  stream.read() & 0xff;
        int green = stream.read() & 0xff;
        int blue = stream.read() & 0xff;
        int rgb = 0xff000000 + (red << 16) + (green << 8) + blue;
        bufferedImage.setRGB(j,i,rgb);
      }

    }
    return bufferedImage;
  }


}
