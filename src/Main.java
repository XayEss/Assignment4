import controller.implementation.CommandLineInput;
import controller.implementation.ControllerImpl;
import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.implementation.UniversalImageLoader;
import controller.implementation.UniversalImageSaver;
import controller.interfaces.Controller;
import controller.interfaces.Input;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
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
    Image im = ImageConverter.convertFromBytes(new UniversalImageLoader().readFile("resources/raiden.jpg"));
//    PipedOutputStream pipedOutputStream = new PipedOutputStream();
//    PipedInputStream pipedInputStream = new PipedInputStream();
//    pipedOutputStream.connect(pipedInputStream);
//    ObjectOutput os = new ObjectOutputStream(pipedOutputStream);
//    os.writeObject(im);
      InputStream is = ImageConverter.convertToBytes(im);
      Image im2 = ImageConverter.convertFromBytes(is);
//    ImageProcessor ip = new ImageProcessorImpl();
//    new UniversalImageSaver().save("resources/sephia.png", ImageConverter.convertToBytes(new ImageProcessorImpl().sepiaTone(im2)));
//    new VisualizeImage(ImageToBufferedImageService.toBuffered(im2));
//    new VisualizeImage(ImageToBufferedImageService.toBuffered(ImageConverter.convertToBytes(im2.dither())));
    Input input = null;
    if(true || args.length != 0 && args[0].equals("-file")){
     // input = new CommandLineInput(new FileInputStream(args[1]));
      input = new CommandLineInput(new ByteArrayInputStream(("load resources/raiden.jpg raiden\n"
          + "horizontal-flip raiden raidendither\n"
          + "save resources/raidend.jpg raidendither\n stop\n").getBytes()));

    }else{
      input = new CommandLineInput(System.in);
    }

    Controller controller = new ControllerImpl(new UniversalImageLoader(), new UniversalImageSaver(),
            input, ih, new CommandLineOutput(System.out));
    controller.start();

  }

}