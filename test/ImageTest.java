import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageImpl;
import model.interfaces.Image;
import model.interfaces.Pixel;

import static org.junit.Assert.assertEquals;

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
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(5, testImage.getWidth());
    assertEquals(3, testImage.getHeight());


    Image testImageAlt = new ImageImpl(20, 30);
    assertEquals(30, testImageAlt.getWidth());
    assertEquals(20, testImageAlt.getHeight());
  }

  @Test
  public void testGetImageChannel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    Image testCh0 = testImage.getImageChannel(0);
    Image testCh1 = testImage.getImageChannel(1);
    Image testCh2 = testImage.getImageChannel(2);

    Image imageCh0 = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage_RedChannel.ppm");
    Image imageCh1 = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage_GreenChannel.ppm");
    Image imageCh2 = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage_BlueChannel.ppm");


    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {
        assertEquals(imageCh0.getPixelChannel(row, col, 0),
                testCh0.getPixelChannel(row, col, 0));
        assertEquals(imageCh1.getPixelChannel(row, col, 1),
                testCh1.getPixelChannel(row, col, 1));
        assertEquals(imageCh2.getPixelChannel(row, col, 2),
                testCh2.getPixelChannel(row, col, 2));
      }
    }
  }


  @Test
  public void testFlipImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    // Horizontal flip
    Image flipImage = testImage.flipImage(true);
    // Vertical flip
    Image flipImageAlt = testImage.flipImage(false);

    Image imageHorizontalFlipped = new ImageUtil().readFile("resources/images/" +
            "ppm_testing/testBaseImage_HorizontalFlip.ppm");
    Image imageVerticalFlipped = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage_VerticalFlip.ppm");

    assertEquals(imageHorizontalFlipped, flipImage);
    assertEquals(imageVerticalFlipped, flipImageAlt);
  }


  @Test
  public void testGetValueImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
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
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
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
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
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
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

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
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    Pixel testPixel = testImage.getPixel(0, 0);

    assertEquals(101, testPixel.getChannel(0));
    assertEquals(90, testPixel.getChannel(1));
    assertEquals(58, testPixel.getChannel(2));
  }


  @Test
  public void testGetPixelChannel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    int testPixelCh0 = testImage.getPixelChannel(0, 0, 0);
    int testPixelCh1 = testImage.getPixelChannel(0, 0, 1);
    int testPixelCh2 = testImage.getPixelChannel(0, 0, 2);

    assertEquals(101, testPixelCh0);
    assertEquals(90, testPixelCh1);
    assertEquals(58, testPixelCh2);
  }


  @Test
  public void testGetNumberOfChannels() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(3, testImage.getNumberOfChannels());
  }


  @Test
  public void testGetHeight() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(3, testImage.getHeight());
  }

  @Test
  public void testGetWidth() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(5, testImage.getWidth());
  }


  @Test
  public void testGetSize() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals("5 x 3", testImage.getSize());
  }


  @Test
  public void testSetPixel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    Pixel testPixel = testImage.getPixel(0, 0);

    assertEquals(101, testPixel.getChannel(0));
    assertEquals(90, testPixel.getChannel(1));
    assertEquals(58, testPixel.getChannel(2));

    testPixel.setChannel(0, 45);
    testPixel.setChannel(1, 145);
    testPixel.setChannel(2, 51);

    assertEquals(45, testPixel.getChannel(0));
    assertEquals(145, testPixel.getChannel(1));
    assertEquals(51, testPixel.getChannel(2));
  }


  @Test
  public void testGetGreyscaleImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    Image testGreyscaleImage = testImage.getGreyscaleImage();

    for (int row = 0; row < testImage.getHeight(); row++) {
      for (int col = 0; col < testImage.getWidth(); col++) {

        assertEquals((int) (testImage.getPixelChannel(row, col, 0)
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
  public void testGetBytes() {
    // TODO: TEST THIS!
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals(testImage.getBytes(), testImage);
  }


  @Test
  public void testToString() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");
    assertEquals("[[101 90 58, 103 92 62, 110 95 66, 104 91 59, 104 91 59], " +
            "[104 93 63, 108 94 65, 100 86 57, 103 90 56, 105 91 64], [101 89 63, 103 91 65, " +
            "106 92 66, 103 86 66, 105 91 64]]", testImage.toString());
  }

}