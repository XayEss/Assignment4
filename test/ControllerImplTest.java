import org.junit.Test;

import java.io.FileNotFoundException;

import controller.implementation.ControllerImpl;
import controller.implementation.ImageUtil;
import controller.interfaces.Controller;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import controller.interfaces.Input;
import model.implementation.NoSuchImageException;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import view.intefraces.Output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Test class for controller.
 */
public class ControllerImplTest {

  @Test
  public void start() {
    InputMock mockInput = new InputMock();
    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            mockInput, new ImageHandlerMock(), new OutputMock());

    controller.start();
    assertEquals("startCommandReading", mockInput.getLog());
  }

  @Test
  public void testSeparateImageChannel() {
    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    String name = "nameOne";
    String resName = "nameRes";
    int channel = 3;

    controller.separateImageChannel(name, resName, channel);
    assertEquals("getChannel " + name + " " + channel + " " + resName,
            mockImageHandler.getLog());
  }

  @Test
  public void createFlippedImage() {
    String name = "NameOne";
    String resultName = "resName";
    boolean horizontal = true;

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.createFlippedImage(name, resultName, horizontal);
    assertEquals("flipImage " + name + " " + horizontal + " " + resultName,
            mockImageHandler.getLog());

    horizontal = false;
    controller.createFlippedImage(name, resultName, horizontal);
    assertEquals("flipImage " + name + " " + horizontal + " " + resultName,
            mockImageHandler.getLog());

  }

  @Test
  public void createValueImage() {
    String name = "nameOne";
    String resultName = "resName";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.createValueImage(name, resultName);
    assertEquals("getValue " + name + " " + resultName, mockImageHandler.getLog());

  }

  @Test
  public void createIntensityImage() {
    String name = "nameOne";
    String resultName = "resName";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.createIntensityImage(name, resultName);
    assertEquals("getIntensity " + name + " " + resultName, mockImageHandler.getLog());
  }

  @Test
  public void createLumaImage() {
    String name = "nameOne";
    String resultName = "resName";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.createLumaImage(name, resultName);
    assertEquals("getLuma " + name + " " + resultName, mockImageHandler.getLog());

  }

  @Test
  public void alterImageBrightness() {
    String name = "nameOne";
    String resultName = "resName";
    int value = 20;

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.alterImageBrightness(name, resultName, value);
    assertEquals("getAlterBrightness " + name + " " + value + " " + resultName,
            mockImageHandler.getLog());

    value = -20;
    controller.alterImageBrightness(name, resultName, value);
    assertEquals("getAlterBrightness " + name + " " + value + " " + resultName,
            mockImageHandler.getLog());

  }

  @Test
  public void createGreyScaleImage() {
    String name = "nameOne";
    String resultName = "resName";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.createGreyScaleImage(name, resultName);
    assertEquals("getGreyscale " + name + " " + resultName, mockImageHandler.getLog());

  }

  @Test
  public void splitImageChannels() {
    String name = "nameOne";
    String redName = "nameRed";
    String greName = "nameGre";
    String bluName = "nameBlu";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.splitImageChannels(name, redName, greName, bluName);
    assertEquals("getSplitChannels " + name + " " + redName + " " + greName + " "
            + bluName, mockImageHandler.getLog());

  }

  @Test
  public void combineGreyScaleImages() {
    String resultName = "resName";
    String redName = "nameRed";
    String greName = "nameGre";
    String bluName = "nameBlu";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.combineGreyScaleImages(redName, greName, bluName, resultName);
    assertEquals("combineGreyScaleImages " + redName + " " + greName + " " + bluName
            + " " + resultName, mockImageHandler.getLog());

  }

  @Test
  public void loadImage() {
    String path = "file/path";
    String name = "nameZero";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.loadImage(path, name);
    assertNull(mockImageHandler.getLog());

  }

  @Test
  public void saveImage() {
    String path = "path/file";
    String name = "nameZero";

    ImageHandlerMock mockImageHandler = new ImageHandlerMock();

    Controller controller = new ControllerImpl(new ImageInputMock(), new ImageSaverMock(),
            new InputMock(), mockImageHandler, new OutputMock());

    controller.saveImage(path, name);
    assertEquals("getByName " + name, mockImageHandler.getLog());

  }


  /**
   * mock ImageInput class for testing.
   */
  public class ImageInputMock implements ImageInput {

    public String log;

    @Override
    public Image readFile(String filename) {
      log = "readFile " + filename;
      return null;
    }

    public String getLog() {
      return log;
    }

  }

  /**
   * mock ImageSaver class for testing.
   */
  public class ImageSaverMock implements ImageSaver {

    public String log;

    @Override
    public void save(String path, Image image) throws FileNotFoundException {
      log = "save " + path + " " + image.toString();
    }

    public String getLog() {
      return log;
    }

  }

  /**
   * mock Input class for testing.
   */
  public class InputMock implements Input {

    public String log;

    @Override
    public void startCommandReading() {
      log = "startCommandReading";
    }

    @Override
    public void parseInput(String line) {
      log = "parseInput " + line;
    }

    @Override
    public void setController(Controller controller) {
      log = "setController " + controller.toString();
    }

    public String getLog() {
      return log;
    }

  }

  /**
   * mock ImageHandler class for testing.
   */
  public class ImageHandlerMock implements ImageHandler {

    public String log;

    @Override
    public void getChannel(String name, int channel, String saveName) throws NoSuchImageException {
      log = "getChannel " + name + " " + channel + " " + saveName;
    }

    @Override
    public void flipImage(String name, boolean horizontal, String saveName)
            throws NoSuchImageException {
      log = "flipImage " + name + " " + horizontal + " " + saveName;
    }

    @Override
    public void getValue(String name, String saveName) throws NoSuchImageException {
      log = "getValue " + name + " " + saveName;
    }

    @Override
    public void getIntensity(String name, String saveName) throws NoSuchImageException {
      log = "getIntensity " + name + " " + saveName;
    }

    @Override
    public void getLuma(String name, String saveName) throws NoSuchImageException {
      log = "getLuma " + name + " " + saveName;
    }

    @Override
    public void alterBrightness(String name, int value, String saveName)
            throws NoSuchImageException {
      log = "getAlterBrightness " + name + " " + value + " " + saveName;
    }

    @Override
    public void getGreyscale(String name, String saveName) throws NoSuchImageException {
      log = "getGreyscale " + name + " " + saveName;
    }

    @Override
    public void getSplitChannels(String name, String redSaveName, String greenSaveName,
                                 String blueSaveName) throws NoSuchImageException {
      log = "getSplitChannels " + name + " " + redSaveName + " " + greenSaveName + " "
              + blueSaveName;
    }

    @Override
    public void combineGreyScaleImages(String redName, String greenName, String blueName,
                                       String saveName) throws NoSuchImageException {
      log = "combineGreyScaleImages " + redName + " " + greenName + " " + blueName + " " + saveName;
    }

    @Override
    public Image getByName(String name) throws NoSuchImageException {
      log = "getByName " + name;
      Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm");
      return testImage;
    }


    @Override
    public void saveWithName(String name, Image image) {
      log = "saveWithName " + name + " " + image;
    }

    public String getLog() {
      return log;
    }

  }

  /**
   * mock Output class for testing.
   */
  public class OutputMock implements Output {

    public String log;

    @Override
    public void show(Image image) {
      log = "show " + image.toString();
    }

    @Override
    public void print(String string) {
      log = "print " + string;
    }

    public String getLog() {
      return log;
    }

  }

}