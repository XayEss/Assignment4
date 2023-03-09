import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.implementation.PixelRGB;
import model.interfaces.Pixel;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedImage image = ImageIO.read(new File("resources/images/png/koala-blue-greyscale.png"));
    Pixel pixel = new PixelRGB(255, 143, 25);
    System.out.println(pixel.getValue());
    System.out.println(pixel.getIntensity());
    System.out.println(pixel.getLuma());
  }
}