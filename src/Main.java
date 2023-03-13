import controller.implementation.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.implementation.ImageImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;
import view.impl.VisualizeImage;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
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
    Image koala = new ImageUtil().readFile("resources/images/ppm/koala.ppm");
    System.out.println("---------------");
    VisualizeImage vi = new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala));
    Image koalaRedGreyscale = koala.getImageChannel(0).getGreyscaleImage();
    Image koalaGreenGreyscale = koala.getImageChannel(1).getGreyscaleImage();
    Image koalaBlueGreyscale = koala.getImageChannel(2).getGreyscaleImage();
    //new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koala.getLumaImage().getBytes()));
    ImageProcessor imageProcessor = new ImageProcessorImpl();
    Image combined = imageProcessor.combineGreyScaleImages(koalaRedGreyscale, koalaGreenGreyscale, koalaBlueGreyscale);
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala.getGreyscaleImage()));
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(combined.flipImage(true).flipImage(false)));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaRedGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaGreenGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaBlueGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koala.alterBrightness(50).getBytes()));
    //vi.changeImage(ImageToBufferedImageService.convertToBuffered(koala.getImageChannel(0)));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala.getLumaImage()));


  }

}