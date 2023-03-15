import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageProcessorImpl;

import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;

import static org.junit.Assert.*;

public class ImageProcessorTest {

  @Test
  public void testGetChannel() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getImageChannel(0), testProcessor.getChannel(testImg, 0));
    assertEquals(testImg.getImageChannel(1), testProcessor.getChannel(testImg, 1));
    assertEquals(testImg.getImageChannel(2), testProcessor.getChannel(testImg, 2));

  }

  @Test
  public void testFlipImage() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.flipImage(true), testProcessor.flipImage(testImg, true));
    assertEquals(testImg.flipImage(false), testProcessor.flipImage(testImg, false));
  }

  @Test
  public void testGetValue() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getValueImage(), testProcessor.getValue(testImg));
  }

  @Test
  public void testGetIntensity() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getIntensityImage(), testProcessor.getIntensity(testImg));
  }

  @Test
  public void testGetLuma() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getLumaImage(), testProcessor.getLuma(testImg));
  }

  @Test
  public void testAlterBrightness() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    System.out.println(testImg);
    // TODO: Alter Brightness running twice through ImageProcessor!
    assertEquals(testImg.alterBrightness(20), testProcessor.alterBrightness(testImg, 20));
    assertEquals(testImg.alterBrightness(-20), testProcessor.alterBrightness(testImg, -20));
  }

  @Test
  public void testGetGreyScale() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    assertEquals(testImg.getGreyscaleImage(), testProcessor.getGreyscale(testImg));
  }


  @Test
  public void testGetSplitChannels() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    Image[] temp = testProcessor.getSplitChannels(testImg);
    assertEquals(testImg.getImageChannel(0), temp[0]);
    assertEquals(testImg.getImageChannel(1), temp[1]);
    assertEquals(testImg.getImageChannel(2), temp[2]);
  }


  @Test
  public void testCombineGreyScaleImages() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");
    ImageProcessor testProcessor = new ImageProcessorImpl();

    Image[] temp = testProcessor.getSplitChannels(testImg);


    System.out.println(testImg);
    System.out.println(temp[0]);
    System.out.println(temp[1]);
    System.out.println(temp[2]);

    assertEquals(testImg, testProcessor.combineGreyScaleImages(temp[0], temp[1], temp[2]));
  }

}