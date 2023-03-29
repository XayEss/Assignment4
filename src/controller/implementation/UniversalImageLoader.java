package controller.implementation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import controller.interfaces.ImageInput;

public class UniversalImageLoader implements ImageInput {

  @Override
  public InputStream readFile(String filename) throws IOException {
    if (filename.substring(filename.lastIndexOf('.') + 1).equals("ppm")) {
      return new ImageUtil().readFile(filename);
    } else {
      return load(filename);
    }
  }

  private InputStream load(String filename) {
    BufferedImage bi;
    try {
      bi = ImageIO.read(new File(filename));
      //ImageInputStream is = ImageIO.createImageInputStream(new File(filename));
      //ImageReader reader = ImageIO.getImageReadersByFormatName("png").next();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    if (bi == null) {

    }
    int height = bi.getHeight();
    int width = bi.getWidth();
    byte[] bytes = new byte[height * width * 3 + 8];
    int index = 0;
    byte[] hei = ByteBuffer.allocate(4).putInt(height).array();
    byte[] wid = ByteBuffer.allocate(4).putInt(width).array();
    for (int k = 0; k < 4; k++) {
      bytes[index++] = wid[k];
    }
    for (int k = 0; k < 4; k++) {
      bytes[index++] = hei[k];
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int value = bi.getRGB(j, i);
        String binary = Integer.toBinaryString(value);
        //System.out.println(binary);
        int red = Integer.parseInt(binary.substring(8, 16), 2);
        int green = Integer.parseInt(binary.substring(16, 24), 2);
        int blue = Integer.parseInt(binary.substring(24, 32), 2);

        bytes[index++] = (byte) red;
        bytes[index++] = (byte) green;
        bytes[index++] = (byte) blue;
      }
    }

    return new ByteArrayInputStream(bytes);
  }
}
