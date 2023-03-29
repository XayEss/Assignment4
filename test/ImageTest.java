import java.io.IOException;
import model.implementation.ImageConverter;
import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageImpl;
import model.interfaces.Image;
import model.interfaces.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//     new VisualizeImage(ImageToBufferedImageService.convertToBuffered(testImage));
//         try {
//         Thread.sleep(100000000);
//         } catch (InterruptedException e) {
// :)
//         }

/**
 * Test class for Image.
 */
public class ImageTest {

  @Test
  public void testValidCreation() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals(5, testImage.getWidth());
    assertEquals(3, testImage.getHeight());


    Image testImageAlt = new ImageImpl(20, 30);
    assertEquals(30, testImageAlt.getWidth());
    assertEquals(20, testImageAlt.getHeight());
  }

  @Test
  public void testGetImageChannel() {
    Image testImage = null;
    Image imageCh0 = null;
    Image imageCh1 = null;
    Image imageCh2 = null;
    Image image2Ch0 = null;
    Image image2Ch1 = null;
    Image image2Ch2 = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
      imageCh0 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_RedChannel.ppm"));
      imageCh1 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_GreenChannel.ppm"));
      imageCh2 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_BlueChannel.ppm"));
      image2Ch0 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_RedChannel.ppm"));
      image2Ch1 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_GreenChannel.ppm"));
      image2Ch2 = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_BlueChannel.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Image testCh0 = testImage.getImageChannel(0);
    Image testCh1 = testImage.getImageChannel(1);
    Image testCh2 = testImage.getImageChannel(2);




    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals(image2Ch0.getPixelChannel(row, col, 0),
                testCh0.getPixelChannel(row, col, 0));
        assertEquals(image2Ch1.getPixelChannel(row, col, 1),
                testCh1.getPixelChannel(row, col, 1));
        assertEquals(image2Ch2.getPixelChannel(row, col, 2),
                testCh2.getPixelChannel(row, col, 2));
      }
    }
  }


  @Test
  public void testFlipImage() {
    Image testImage = null;
    Image imageHorizontalFlipped = null;
    Image imageVerticalFlipped = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
      imageHorizontalFlipped = ImageConverter.convertFromBytes(new ImageUtil().readFile("resources/images/" +
          "ppm_testing/testBaseImage_HorizontalFlip.ppm"));
      imageVerticalFlipped = ImageConverter.convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage_VerticalFlip.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    // Horizontal flip
    Image flipImage = testImage.flipImage(true);
    // Vertical flip
    Image flipImageAlt = testImage.flipImage(false);



    assertEquals(imageHorizontalFlipped, flipImage);
    assertEquals(imageVerticalFlipped, flipImageAlt);
  }


  @Test
  public void testGetValueImage() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Image testValueImage = testImage.getValueImage();


    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals(Math.max(testImage.getPixelChannel(row, col, 0),
                        Math.max(testImage.getPixelChannel(row, col, 1),
                                testImage.getPixelChannel(row, col, 2))),
                testValueImage.getPixelChannel(row, col, 0));
        assertEquals(testValueImage.getPixelChannel(row, col, 0),
                testValueImage.getPixelChannel(row, col, 1));
        assertEquals(testValueImage.getPixelChannel(row, col, 0),
                testValueImage.getPixelChannel(row, col, 2));
      }
    }
  }


  @Test
  public void testGetIntensityImage() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Image testIntensityImage = testImage.getIntensityImage();

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals((testImage.getPixelChannel(row, col, 0)
                        + testImage.getPixelChannel(row, col, 1)
                        + testImage.getPixelChannel(row, col, 2)) / 3,
                testIntensityImage.getPixelChannel(row, col, 0));

        assertEquals(testIntensityImage.getPixelChannel(row, col, 0),
                testIntensityImage.getPixelChannel(row, col, 1));

        assertEquals(testIntensityImage.getPixelChannel(row, col, 0),
                testIntensityImage.getPixelChannel(row, col, 2));
      }
    }
  }

  @Test
  public void testGetLumaImage() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Image testLumaImage = testImage.getLumaImage();

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals((int) (testImage.getPixelChannel(row, col, 0) * 0.2126
                        + testImage.getPixelChannel(row, col, 1) * 0.7152
                        + testImage.getPixelChannel(row, col, 2) * 0.0722),
                testLumaImage.getPixelChannel(row, col, 0));

        assertEquals(testLumaImage.getPixelChannel(row, col, 0),
                testLumaImage.getPixelChannel(row, col, 1));
        assertEquals(testLumaImage.getPixelChannel(row, col, 1),
                testLumaImage.getPixelChannel(row, col, 2));
      }
    }
  }

  @Test
  public void testAlterBrightness() {
    // Positive and Negative alterations
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }

    System.out.println(testImage);

    Image testIncreased = testImage.alterBrightness(20);
    Image testDecreased = testImage.alterBrightness(-50);

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals(testImage.getPixelChannel(row, col, 0) + 20,
                testIncreased.getPixelChannel(row, col, 0));

        assertEquals(testImage.getPixelChannel(row, col, 1) + 20,
                testIncreased.getPixelChannel(row, col, 1));

        assertEquals(testImage.getPixelChannel(row, col, 2) + 20,
                testIncreased.getPixelChannel(row, col, 2));

        assertEquals(testImage.getPixelChannel(row, col, 0) - 50,
                testDecreased.getPixelChannel(row, col, 0));

        assertEquals(testImage.getPixelChannel(row, col, 1) - 50,
                testDecreased.getPixelChannel(row, col, 1));

        assertEquals(testImage.getPixelChannel(row, col, 2) - 50,
                testDecreased.getPixelChannel(row, col, 2));
      }
    }
  }


  @Test
  public void testGetPixel() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Pixel testPixel = testImage.getPixel(0, 0);

    assertEquals(101, testPixel.getChannel(0));
    assertEquals(90, testPixel.getChannel(1));
    assertEquals(58, testPixel.getChannel(2));
  }


  @Test
  public void testGetPixelChannel() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    int testPixelCh0 = testImage.getPixelChannel(0, 0, 0);
    int testPixelCh1 = testImage.getPixelChannel(0, 0, 1);
    int testPixelCh2 = testImage.getPixelChannel(0, 0, 2);

    assertEquals(101, testPixelCh0);
    assertEquals(90, testPixelCh1);
    assertEquals(58, testPixelCh2);
  }


  @Test
  public void testGetNumberOfChannels() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals(3, testImage.getNumberOfChannels());
  }


  @Test
  public void testGetHeight() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals(3, testImage.getHeight());
  }

  @Test
  public void testGetWidth() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals(5, testImage.getWidth());
  }


  @Test
  public void testGetSize() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals("5 x 3", testImage.getSize());
  }


  @Test
  public void testSetPixel() {
    Image testImage = null;
    try {
      testImage = ImageConverter
              .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
                      "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    Pixel testPixel = testImage.getPixel(0, 0);

    System.out.println(testPixel);

    assertEquals(57, testPixel.getChannel(0));
    assertEquals(50, testPixel.getChannel(1));
    assertEquals(10, testPixel.getChannel(2));

    testPixel.setChannel(0, 45);
    testPixel.setChannel(1, 145);
    testPixel.setChannel(2, 51);

    assertEquals(45, testPixel.getChannel(0));
    assertEquals(145, testPixel.getChannel(1));
    assertEquals(51, testPixel.getChannel(2));
  }


  @Test
  public void testGetGreyscaleImage() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    Image testGreyscaleImage = testImage.getGreyscaleImage();

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {

        assertEquals((testImage.getPixelChannel(row, col, 0)
                        + testImage.getPixelChannel(row, col, 1)
                        + testImage.getPixelChannel(row, col, 2)) / 3,
                testGreyscaleImage.getPixelChannel(row, col, 0));

        assertEquals(testGreyscaleImage.getPixelChannel(row, col, 0),
                testGreyscaleImage.getPixelChannel(row, col, 1));
        assertEquals(testGreyscaleImage.getPixelChannel(row, col, 0),
                testGreyscaleImage.getPixelChannel(row, col, 2));

      }
    }

  }


  @Test
  public void testToString() {
    Image testImage = null;
    try{
      testImage = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }
    assertEquals("[[101 90 58, 103 92 62, 110 95 66, 104 91 59, 104 91 59], " +
            "[104 93 63, 108 94 65, 100 86 57, 103 90 56, 105 91 64], [101 89 63, 103 91 65, " +
            "106 92 66, 103 86 66, 105 91 64]]", testImage.toString());
  }

}