package controller.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.interfaces.ImageInput;
import model.implementation.ImageConverter;
import model.implementation.ImageImpl;
import model.implementation.PixelRGB;
import model.interfaces.Pixel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil implements ImageInput {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public InputStream readP3(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    ByteArrayOutputStream bas = new ByteArrayOutputStream();
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    try {
      int width = sc.nextInt();
      bas.write(ByteBuffer.allocate(4).putInt(width).rewind().array());
      int height = sc.nextInt();
      System.out.println("Height of image: " + height);
      bas.write(ByteBuffer.allocate(4).putInt(height).rewind().array());
      int maxValue = sc.nextInt();
      //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
      Pixel[][] pixels = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          bas.write(r);
          bas.write(g);
          bas.write(b);
          //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
      }
    }catch (IOException e){
      System.out.println("Error when reading");
    }
    return new ByteArrayInputStream(bas.toByteArray());
  }

  public InputStream readFile(String filename){
    Scanner sc;
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(filename);
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      return null;
    }
    DataInputStream dis = new DataInputStream(fileInputStream);
//    StringBuilder builder = new StringBuilder();
//    //read the file line by line, and populate a string. This will throw away any comment lines
//    while (sc.hasNextLine()) {
//      String s = sc.nextLine();
//      if (s.charAt(0) != '#') {
//        builder.append(s + System.lineSeparator());
//      }
//    }

    //now set up the scanner to read from the string we just built
    //sc = new Scanner(builder.toString());

    List<Integer> bytes = new ArrayList<>();
    int byt = 0;
    String token;
    token = sc.next();
    // TODO: This is being printed for every PPM image?
    if (!token.equals("P6")) {

      System.out.println("Invalid PPM file: plain RAW file should begin with P3" + token);
    }else if(token.equals("P3")){
      return readP3(filename);
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    byte[] data = null;
    try {
      data = dis.readAllBytes();
    }catch(IOException e){
      System.out.println("Exception when reading bytes");
    }
    Pixel[][] pixels = new Pixel[height][width];
    int get = 0;
    for(int i = 0; i < 25; i++){
      if(data[i] == '\n'){
        get = i;
      }
    }
    get++;
    int index = 0;
    byte[] returning = new byte[height*width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = data[get++] & 0xff;
        //returning[index++] = (byte)r;
        int g = data[get++] & 0xff;
        //returning[index++] = (byte)g;
        int b = data[get++] & 0xff;
        //returning[index++] = (byte)b;
        pixels[i][j] = new PixelRGB(r, g, b);
        //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
    }
    InputStream s = null;
    try {
      s = ImageConverter.convertToBytes(new ImageImpl(pixels));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s;
    //return new ByteArrayInputStream(returning);
  }
}

