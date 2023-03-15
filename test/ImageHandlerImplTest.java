import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageProcessorImpl;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.ImageProcessor;

import static org.junit.Assert.assertEquals;

public class ImageHandlerImplTest {

  @Test
  public void testImageHandlerCreation() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);
  }

  @Test
  public void testSaveWithName() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    Image testNewImg = testImg.getImageChannel(0);

    // Image present in handler
    testHandler.saveWithName("testImg", testImg);
    assertEquals(testImg, testHandler.getByName("testImg"));

    // Multiple images in handler
    testHandler.saveWithName("testNewImg", testNewImg);
    assertEquals(testNewImg, testHandler.getByName("testNewImg"));
    assertEquals(testImg, testHandler.getByName("testImg"));
  }


  @Test
  public void testGetByName() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    Image testNewImg = testImg.getImageChannel(0);

    // TODO: Fix case of image not in handler
    // No image/ Name not present in handler
//    try {
//      testHandler.getByName("storedImage");
//      fail("No image in Handler, error should've been thrown! ");
//    } catch (NoSuchElementException e) {
//      // Do Nothing
//    }

    // Image present in handler
    testHandler.saveWithName("testImg", testImg);
    assertEquals(testImg, testHandler.getByName("testImg"));

    // Multiple images in handler
    testHandler.saveWithName("testNewImg", testNewImg);
    assertEquals(testNewImg, testHandler.getByName("testNewImg"));
    assertEquals(testImg, testHandler.getByName("testImg"));
  }


  @Test
  public void testGetChannel() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    Image testNewImg0 = testImg.getImageChannel(0);
    Image testNewImg1 = testImg.getImageChannel(1);
    Image testNewImg2 = testImg.getImageChannel(2);

    testHandler.saveWithName("testImg", testImg);
    testHandler.getChannel("testImg", 0, "testImg0");
    testHandler.getChannel("testImg", 1, "testImg1");
    testHandler.getChannel("testImg", 2, "testImg2");

    assertEquals(testNewImg0, testHandler.getByName("testImg0"));
    assertEquals(testNewImg1, testHandler.getByName("testImg1"));
    assertEquals(testNewImg2, testHandler.getByName("testImg2"));
  }


  @Test
  public void testFlipImage() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    Image testFlipHor = testImg.flipImage(true);
    Image testFlipVer = testImg.flipImage(false);

    testHandler.saveWithName("testImg", testImg);
    testHandler.flipImage("testImg", true, "testFlipHor");
    testHandler.flipImage("testImg", false, "testFlipVer");

    assertEquals(testFlipHor, testHandler.getByName("testFlipHor"));
    assertEquals(testFlipVer, testHandler.getByName("testFlipVer"));
  }


  @Test
  public void testGetValue() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testValue = testImg.getValueImage();
    testHandler.getValue("testImg", "testValue");

    assertEquals(testValue, testHandler.getByName("testValue"));
  }


  @Test
  public void testGetIntensity() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testIntensity = testImg.getIntensityImage();
    testHandler.getIntensity("testImg", "testIntensity");

    assertEquals(testIntensity, testHandler.getByName("testIntensity"));
  }


  @Test
  public void testGetLuma() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testLuma = testImg.getLumaImage();
    testHandler.getLuma("testImg", "testLuma");

    assertEquals(testLuma, testHandler.getByName("testLuma"));

  }


  @Test
  public void testAlterBrightness() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testBrightInc = testImg.alterBrightness(20);
    Image testBrightDec = testImg.alterBrightness(-20);

    testHandler.alterBrightness("testImg", 20, "testBrightInc");
    testHandler.alterBrightness("testImg", -20, "testBrightDec");

    assertEquals(testBrightInc, testHandler.getByName("testBrightInc"));
    assertEquals(testBrightDec, testHandler.getByName("testBrightDec"));

  }


  @Test
  public void testGetGreyscale() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testGrey = testImg.getGreyscaleImage();
    testHandler.getGreyscale("testImg", "testGrey");

    assertEquals(testGrey, testHandler.getByName("testGrey"));

  }


  @Test
  public void testGetSplitChannels() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testRed = testImg.getImageChannel(0);
    Image testGre = testImg.getImageChannel(1);
    Image testBlu = testImg.getImageChannel(2);

    testHandler.getSplitChannels("testImg", "testRed",
            "testGre", "testBlu");

    assertEquals(testRed, testHandler.getByName("testRed"));
    assertEquals(testGre, testHandler.getByName("testGre"));
    assertEquals(testBlu, testHandler.getByName("testBlu"));


  }


}