import controller.implementation.UniversalImageLoader;
import java.io.IOException;

import model.implementation.ImageConverter;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
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
    ImageHandler ih = new ImageHandlerImpl(new ImageProcessorImpl());
    Image im = ImageConverter.convertFromBytes(new UniversalImageLoader().readFile("resources/raiden.jpg"));
//    PipedOutputStream pipedOutputStream = new PipedOutputStream();
//    PipedInputStream pipedInputStream = new PipedInputStream();
//    pipedOutputStream.connect(pipedInputStream);
//    ObjectOutput os = new ObjectOutputStream(pipedOutputStream);
//    os.writeObject(im);
//    InputStream is = ImageConverter.convertToBytes(im);
//    Image im2 = ImageConverter.convertFromBytes(is)ccc;
//    ImageProcessor ip = new ImageProcessorImpl();
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(im));
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(im.dither()));
//    Input in = new CommandLineInput();
//    Controller controller = new ControllerImpl(new ImageUtil(), new PPMImageSaver(),
//            in, ih/*new ImageHandlerImpl(new ImageProcessorImpl())*/, new CommandLineOutput());
//    in.setController(controller);
//    controller.start();

  }

}