import java.io.IOException;
import model.implementation.ImageConverter;
import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageProcessorImpl;
import model.interfaces.Image;
import model.interfaces.ImageProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for ImageProcessor.
 */
public class ImageProcessorTest {

  @Test
  public void testGetChannel() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getImageChannel(0), testProcessor.getChannel(testImg, 0));
    assertEquals(testImg.getImageChannel(1), testProcessor.getChannel(testImg, 1));
    assertEquals(testImg.getImageChannel(2), testProcessor.getChannel(testImg, 2));

  }

  @Test
  public void testFlipImage() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.flipImage(true), testProcessor.flipImage(testImg,
        true));
    assertEquals(testImg.flipImage(false), testProcessor.flipImage(testImg,
        false));
  }

  @Test
  public void testGetValue() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getValueImage(), testProcessor.getValue(testImg));
  }

  @Test
  public void testGetIntensity() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getIntensityImage(), testProcessor.getIntensity(testImg));
  }

  @Test
  public void testGetLuma() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getLumaImage(), testProcessor.getLuma(testImg));
  }

  @Test
  public void testAlterBrightness() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    System.out.println(testImg);
    assertEquals(testImg.alterBrightness(20), testProcessor.alterBrightness(testImg, 20));
    assertEquals(testImg.alterBrightness(-20), testProcessor.alterBrightness(testImg, -20));
  }

  @Test
  public void testGetGreyScale() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getGreyscaleImage(), testProcessor.getGreyscale(testImg));
  }


  @Test
  public void testGetSplitChannels() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    Image[] temp = testProcessor.getSplitChannels(testImg);
    assertEquals(testImg.getImageChannel(0), temp[0]);
    assertEquals(testImg.getImageChannel(1), temp[1]);
    assertEquals(testImg.getImageChannel(2), temp[2]);
  }


  @Test
  public void testCombineGreyScaleImages() {
    Image testImg = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }
    ImageProcessor testProcessor = new ImageProcessorImpl();

    Image[] temp = testProcessor.getSplitChannels(testImg);

    assertEquals(testImg, testProcessor.combineGreyScaleImages(temp[0], temp[1], temp[2]));
  }


  @Test
  public void testCombineThreeImages() {
    Image testImg = null;
    Image testImgRed = null;
    Image testImgGreen = null;
    Image testImgBlue = null;
    try {
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage.ppm"));
      testImgRed = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage_RedChannel.ppm"));
      testImgGreen = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage_GreenChannel.ppm"));
      testImgBlue = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
              "testBaseImage_BlueChannel.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg, testProcessor.combineGreyScaleImages(testImgRed, testImgGreen,
        testImgBlue));
  }


}