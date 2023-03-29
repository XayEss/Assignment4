import org.junit.Test;

import java.io.IOException;

import controller.implementation.ImageUtil;
import controller.implementation.UniversalImageLoader;
import controller.implementation.UniversalImageSaver;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import model.implementation.ImageConverter;
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
    assertEquals(testImg.alterBrightness(20),
            testProcessor.alterBrightness(testImg, 20));
    assertEquals(testImg.alterBrightness(-20),
            testProcessor.alterBrightness(testImg, -20));
  }

  @Test
  public void testGetGreyScale() {
    // TODO: Fix with new test images
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
    // TODO: Fix with new test images
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


  @Test
  public void testApplyFilter() {
    Image testImg = null;

    ImageInput imageLoader = new UniversalImageLoader();
    ImageSaver imageSaver = new UniversalImageSaver();

    try {
      testImg = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/raiden-min.png"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    ImageProcessor testProcessor = new ImageProcessorImpl();
    Image filteredImg = testProcessor.applySharpening(testImg);
    Image filteredImg1 = testProcessor.applyBlur(testImg);

    try {
      imageSaver.save("resources/raiden_sharpened.png",
              ImageConverter.convertToBytes(filteredImg));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      imageSaver.save("resources/raiden_blurred.png",
              ImageConverter.convertToBytes(filteredImg1));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }


  @Test
  public void testApplyBlur() {
    Image testImg = null;
    Image testTemp = null;

    ImageInput imageLoader = new UniversalImageLoader();

    try {
      testImg = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/raiden-min.png"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    ImageProcessor testProcessor = new ImageProcessorImpl();
    Image testBlur = testProcessor.applyBlur(testImg);

    try {
      testTemp = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/raiden_blurred.png"));
    } catch (IOException e) {
      fail("Failed to load image");
    }

    assertEquals(testTemp, testBlur);
  }

  @Test
  public void testApplySharpening() {
    Image testImg = null;
    Image testTemp = null;

    ImageInput imageLoader = new UniversalImageLoader();

    try {
      testImg = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/raiden-min.png"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    ImageProcessor testProcessor = new ImageProcessorImpl();
    Image testSharp = testProcessor.applySharpening(testImg);

    try {
      testTemp = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/raiden_sharpened.png"));
    } catch (IOException e) {
      fail("Failed to load image");
    }

    assertEquals(testTemp, testSharp);
  }


}