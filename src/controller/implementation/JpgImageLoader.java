package controller.implementation;

import controller.interfaces.ImageInput;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import model.interfaces.Image;

public class JpgImageLoader implements ImageInput {

  @Override
  public InputStream readFile(String filename) {
    BufferedImage bi;
    try {
      bi = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    int height = bi.getHeight();
    int width = bi.getWidth();
    byte[] bytes = new byte[height*width+8];
    int index = 0;
    byte[] hei = ByteBuffer.allocate(4).putInt(height).array();
    byte[] wid = ByteBuffer.allocate(4).putInt(width).array();
    for(int k = 0; k < 4; k++){
      bytes[index++] = wid[k];
    }
    for(int k = 0; k < 4; k++){
      bytes[index++] = hei[k];
    }
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        bytes[index] = (byte)bi.getRGB(i, j);
      }
    }
    return new ByteArrayInputStream(bytes);
  }
}
