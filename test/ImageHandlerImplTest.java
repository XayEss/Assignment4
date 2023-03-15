import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.NoSuchImageException;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
import model.interfaces.ImageProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    try {
      assertEquals(testImg, testHandler.getByName("testImg"));
    } catch (NoSuchImageException e) {
      fail("No such image!");
    }

    // Multiple images in handler
    testHandler.saveWithName("testNewImg", testNewImg);
    try {
      assertEquals(testNewImg, testHandler.getByName("testNewImg"));
    } catch (NoSuchImageException e) {
      fail("No such image!");
    }
    try {
      assertEquals(testImg, testHandler.getByName("testImg"));
    } catch (NoSuchImageException e) {
      fail("No such image!");
    }
  }


  @Test
  public void testGetByName() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    Image testNewImg = testImg.getImageChannel(0);

    // No image/ Name not present in handler
    try {
      testHandler.getByName("storedImage");
      fail("No image in Handler, error should've been thrown! ");
    } catch (NoSuchImageException e) {
      // Do Nothing
    }

    // Image present in handler
    testHandler.saveWithName("testImg", testImg);
    try {
      assertEquals(testImg, testHandler.getByName("testImg"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    // Multiple images in handler
    testHandler.saveWithName("testNewImg", testNewImg);
    try {
      assertEquals(testNewImg, testHandler.getByName("testNewImg"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testImg, testHandler.getByName("testImg"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
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
    try {
      testHandler.getChannel("testImg", 0, "testImg0");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      testHandler.getChannel("testImg", 1, "testImg1");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      testHandler.getChannel("testImg", 2, "testImg2");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testNewImg0, testHandler.getByName("testImg0"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testNewImg1, testHandler.getByName("testImg1"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testNewImg2, testHandler.getByName("testImg2"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
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
    try {
      testHandler.flipImage("testImg", true, "testFlipHor");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      testHandler.flipImage("testImg", false, "testFlipVer");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testFlipHor, testHandler.getByName("testFlipHor"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testFlipVer, testHandler.getByName("testFlipVer"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testGetValue() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testValue = testImg.getValueImage();
    try {
      testHandler.getValue("testImg", "testValue");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testValue, testHandler.getByName("testValue"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testGetIntensity() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testIntensity = testImg.getIntensityImage();
    try {
      testHandler.getIntensity("testImg", "testIntensity");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testIntensity, testHandler.getByName("testIntensity"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testGetLuma() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testLuma = testImg.getLumaImage();
    try {
      testHandler.getLuma("testImg", "testLuma");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testLuma, testHandler.getByName("testLuma"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

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

    try {
      testHandler.alterBrightness("testImg", 20, "testBrightInc");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      testHandler.alterBrightness("testImg", -20, "testBrightDec");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testBrightInc, testHandler.getByName("testBrightInc"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testBrightDec, testHandler.getByName("testBrightDec"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

  }


  @Test
  public void testGetGreyscale() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    testHandler.saveWithName("testImg", testImg);

    Image testGrey = testImg.getGreyscaleImage();
    try {
      testHandler.getGreyscale("testImg", "testGrey");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testGrey, testHandler.getByName("testGrey"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

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

    try {
      testHandler.getSplitChannels("testImg", "testRed",
              "testGre", "testBlu");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testRed, testHandler.getByName("testRed"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testGre, testHandler.getByName("testGre"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
    try {
      assertEquals(testBlu, testHandler.getByName("testBlu"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testCombineGreyscaleImages() {
    ImageProcessor testProcessor = new ImageProcessorImpl();
    ImageHandler testHandler = new ImageHandlerImpl(testProcessor);

    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
//    testHandler.saveWithName("testImg", testImg);

    Image testRed = testImg.getImageChannel(0);
    Image testGre = testImg.getImageChannel(1);
    Image testBlu = testImg.getImageChannel(2);

    testHandler.saveWithName("testRed", testRed);
    testHandler.saveWithName("testGre", testGre);
    testHandler.saveWithName("testBlu", testBlu);

    try {
      testHandler.combineGreyScaleImages("testRed", "testGre",
              "testBlu", "testImg");
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

    try {
      assertEquals(testImg, testHandler.getByName("testImg"));
    } catch (NoSuchImageException e) {
      throw new RuntimeException(e);
    }

  }


}