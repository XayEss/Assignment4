import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.implementation.ImageImpl;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.Pixel;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedImage image = ImageIO.read(new File("resources/images/png/koala-blue-greyscale.png"));
    Pixel pixel = new PixelRGB(1, 1, 1);
    Pixel pixel2 = new PixelRGB(2, 2, 2);
    Pixel pixel3 = new PixelRGB(3, 3, 3);
    Pixel pixel4 = new PixelRGB(4, 4, 4);
    Pixel pixel5 = new PixelRGB(5, 5, 5);
    Pixel pixel6 = new PixelRGB(6, 6, 6);
    Pixel[][] pixelMap = new PixelRGB[2][3];
    pixelMap[0][0] = pixel;
    pixelMap[0][1] = pixel2;
    pixelMap[0][2] = pixel3;
    pixelMap[1][0] = pixel4;
    pixelMap[1][1] = pixel5;
    pixelMap[1][2] = pixel6;
    Image myImage = new ImageImpl(pixelMap);
    System.out.println(pixel.getValue());
    System.out.println(pixel.getIntensity());
    System.out.println(pixel.getLuma());
    System.out.println("---------------");
    System.out.println(myImage);
    System.out.println(myImage.alterBrightness(10));
  }
}