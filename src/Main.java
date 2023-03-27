import java.io.IOException;

import controller.implementation.CommandLineInput;
import controller.implementation.ControllerImpl;
import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.Controller;
import controller.interfaces.Input;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import model.implementation.ImageConverter;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import view.impl.CommandLineOutput;
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
//    Image im = ImageConverter.convertFromBytes(new ImageUtil().readFile("resources/gg.ppm"));
////    PipedOutputStream pipedOutputStream = new PipedOutputStream();
////    PipedInputStream pipedInputStream = new PipedInputStream();
////    pipedOutputStream.connect(pipedInputStream);
////    ObjectOutput os = new ObjectOutputStream(pipedOutputStream);
////    os.writeObject(im);
//    InputStream is = ImageConverter.convertToBytes(im);
//    Image im2 = ImageConverter.convertFromBytes(is);
//    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(im2));
    Input in = new CommandLineInput();
    Controller controller = new ControllerImpl(new ImageUtil(), new PPMImageSaver(),
            in, ih/*new ImageHandlerImpl(new ImageProcessorImpl())*/, new CommandLineOutput());
    in.setController(controller);
    controller.start();

  }

}