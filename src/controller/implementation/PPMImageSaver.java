package controller.implementation;

import controller.interfaces.ImageSaver;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import model.interfaces.Image;

public class PPMImageSaver implements ImageSaver {

  @Override
  public void save(String path, Image image) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(path);
    FileOutputStream fileOutputStream = new FileOutputStream(path);
    writer.println("P3");
    writer.println(image.getWidth() + " " + image.getHeight());
    writer.println("255");
    int k = 0;
    for(int i = 0; i < image.getHeight(); i++){
      for (int j = 0; j < image.getWidth(); j++){
        writer.println(image.getPixelChannel(i, j, 0));
        writer.println(image.getPixelChannel(i, j, 1));
        writer.println(image.getPixelChannel(i, j, 2));
        //k+=1;
        //System.out.println(image.getPixelChannel(i, j, 0) + image.getPixelChannel(i, j, 1) + image.getPixelChannel(i, j, 2));
      }
    }
    System.out.println(k);
    writer.close();
  }
}
