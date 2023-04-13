import controller.implementation.GUIController;
import java.io.FileInputStream;
import java.io.IOException;

import controller.implementation.TextInput;
import controller.implementation.TransformationControllerImpl;
import controller.implementation.UniversalImageLoader;
import controller.implementation.UniversalImageSaver;
import controller.interfaces.Controller;
import controller.interfaces.Input;
import model.implementation.ImageProcessorImpl;
import model.implementation.TransformImageHandlerImpl;
import view.impl.CommandLineOutput;
import view.impl.GUI;

/**
 * The Main class is the entry point of the program. It contains the main method which loads an
 * image, creates some pixels and a pixel map, and initializes a Controller object to start the
 * program.
 */
public class Main {

  /**
   * The main method is the entry point of the program. It loads an image, creates some pixels and a
   * pixel map, and initializes a Controller object to start the program.
   *
   * @param args the command line arguments
   * @throws IOException          if an error occurs while reading the image file
   * @throws InterruptedException if an error occurs while executing the program
   */
  public static void main(String[] args) throws IOException, InterruptedException {

    Input input = null;
    if (args.length != 0 && args[0].equals("-file")) {
      input = new TextInput(new FileInputStream(args[1]));
    } else if(args.length != 0 && args[0].equals("-text") ){
      input = new TextInput(System.in);
    }
    GUI gui = new GUI();
//    Controller controller = new TransformationControllerImpl(new UniversalImageLoader(),
//            new UniversalImageSaver(),
//            input, new TransformImageHandlerImpl(new ImageProcessorImpl()),
//            new CommandLineOutput(System.out));
    Controller controller2 = new GUIController(new UniversalImageLoader(),
        new UniversalImageSaver(),
        input, new TransformImageHandlerImpl(new ImageProcessorImpl()),
        gui);
    //controller.start();
  }

}