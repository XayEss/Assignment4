import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.interfaces.ImageInput;
import model.implementation.ImageImpl;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ImageUtil.
 */
public class ImageUtilTest implements ImageInput {

  @Override
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


  @Test
  public void testReadFile() {

    ImageInput testImgUtil = new ImageUtilTest();
    Image testImg = testImgUtil.readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(testImg.toString(), "[[101 90 58, 103 92 62, 110 95 66, 104 91 59, " +
            "104 91 59], [104 93 63, 108 94 65, 100 86 57, 103 90 56, 105 91 64], " +
            "[101 89 63, 103 91 65, 106 92 66, 103 86 66, 105 91 64]]");

  }


}