import controller.implementation.CommandLineInput;
import controller.implementation.ControllerImpl;
import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.Controller;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;
import view.impl.VisualizeAscii;
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
    //Image koala = new ImageUtil().readFileP6("resources/images/ppm/anime-test.ppm");
//    System.out.println("---------------");
//    VisualizeImage vi = new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala));
      //Image koalaRedGreyscale = koala.getImageChannel(0);//.getGreyscaleImage();
//    Image koalaGreenGreyscale = koala.getImageChannel(1).getGreyscaleImage();
//    Image koalaBlueGreyscale = koala.getImageChannel(2).getGreyscaleImage();
//    //new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koala.getLumaImage().getBytes()));
//    ImageProcessor imageProcessor = new ImageProcessorImpl();
//    Image combined = imageProcessor.combineGreyScaleImages(koalaRedGreyscale, koalaGreenGreyscale, koalaBlueGreyscale);
//    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala.alterBrightness(60)));
//    Image koalabrighter = koala.alterBrightness(120);
//    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koalabrighter));
//    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koalabrighter.alterBrightness(-120)));
      //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koalaRedGreyscale));
      //ImageSaver saver = new PPMImageSaver();
      //saver.save("resources/images/ppm/koala-red.ppm", koala.getImageChannel(0));
    //new VisualizeAscii().show(koala);
    Input in = new CommandLineInput();
    Controller controller = new ControllerImpl(new ImageUtil(), new PPMImageSaver(),
        in, new ImageHandlerImpl(new ImageProcessorImpl()), new VisualizeAscii());
    in.setController(controller);
    controller.start();
  }

}