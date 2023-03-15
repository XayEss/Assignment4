import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.implementation.CommandLineInput;
import controller.implementation.ControllerImpl;
import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.Controller;
import controller.interfaces.Input;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.Pixel;
import view.impl.VisualizeAscii;
import view.impl.VisualizeImage;

/**
 * The Main class is the entry point of the program.
 * It contains the main method which loads an image, creates some pixels and a pixel map,
 * and initializes a Controller object to start the program.
 */
public class Main {

  /**
   * The main method is the entry point of the program.
   * It loads an image, creates some pixels and a pixel map, and initializes
   * a Controller object to start the program.
   *
   * @param args the command line arguments
   * @throws IOException          if an error occurs while reading the image file
   * @throws InterruptedException if an error occurs while executing the program
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    //Image koala = new ImageUtil().readFile("resources/Koala.ppm");
    //ImageHandler ih = new ImageHandlerImpl(new ImageProcessorImpl());
    //ih.saveWithName("koala", koala);
    //ih.flipImage("koala", false, "koala-dead");
    //new VisualizeAscii().show(koala);
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(ih.getByName("koala-dead")));
    //    System.out.println("---------------");
    //    VisualizeImage vi = new VisualizeImage(
    //    ImageToBufferedImageService.convertToBuffered(koala));
    //    Image koalaRedGreyscale = koala.getImageChannel(0);//.getGreyscaleImage();
    //    Image koalaGreenGreyscale = koala.getImageChannel(1).getGreyscaleImage();
    //    Image koalaBlueGreyscale = koala.getImageChannel(2).getGreyscaleImage();
    //    //new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255,
    //    koala.getLumaImage().getBytes()));
    //    ImageProcessor imageProcessor = new ImageProcessorImpl();
    //    Image combined = imageProcessor.combineGreyScaleImages(koalaRedGreyscale,
    //    koalaGreenGreyscale, koalaBlueGreyscale);
    //    new VisualizeImage(
    //    ImageToBufferedImageService.convertToBuffered(koala.alterBrightness(60)));
    //    Image koalabrighter = koala.alterBrightness(120);
    //    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koalabrighter));
    //    new VisualizeImage(
    //    ImageToBufferedImageService.convertToBuffered(koalabrighter.alterBrightness(-120)));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koalaRedGreyscale));
    //ImageSaver saver = new PPMImageSaver();
    //saver.save("resources/images/ppm/koala-red.ppm", koala.getImageChannel(0));
    //new VisualizeAscii().show(koala);
    Input in = new CommandLineInput();
    Controller controller = new ControllerImpl(new ImageUtil(), new PPMImageSaver(),
            in, new ImageHandlerImpl(new ImageProcessorImpl()), new VisualizeAscii());
    in.setController(controller);
    //controller.start();
    controller.loadImage("resources/koala.ppm", "koala");
    controller.createFlippedImage("koala", "koala-dead", true);
    controller.runScript("koala-dead");
    controller.saveImage("resources/dead.ppm", );
  }

}