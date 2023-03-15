package controller.implementation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import controller.interfaces.ImageInput;
import model.implementation.ImageImpl;
import model.implementation.PixelRGB;
import model.interfaces.Image;
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
  public Image readFile(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
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

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new PixelRGB(r, g, b);
        //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
    }
    return new ImageImpl(pixels);
  }

  /**
   * Reads an image in P6 format from a file.
   *
   * @param filename the path of the file to be read
   * @return an Image object representing the image read from the file,
   * or null if the file is not found
   */
  public Image readFileP6(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
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

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    System.out.println(builder.toString());
    String token;

    token = sc.next();
    //    if (!token.equals("P3")) {
    //      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    //    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    builder.delete(0, builder.lastIndexOf("255") + 3);
    System.out.println(builder.toString());
    InputStream is = new ByteArrayInputStream(builder.toString().getBytes());
    Pixel[][] pixels = new Pixel[height][width];
    try {
      byte[] allbytes = is.readAllBytes();
      int k = 0;
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = allbytes[k++];
          int g = allbytes[k++];
          int b = allbytes[k++];
          System.out.println(k);
          pixels[i][j] = new PixelRGB(r, g, b);
          //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
      }
    } catch (IOException e) {
      // Do Nothing
    }
    return new ImageImpl(pixels);
  }
}

