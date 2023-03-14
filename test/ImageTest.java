import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageImpl;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import model.interfaces.Pixel;

import view.impl.VisualizeImage;

import static org.junit.Assert.*;

// new VisualizeImage(ImageToBufferedImageService.convertToBuffered(flipImage));
//         try {
//         Thread.sleep(100000000);
//         } catch (InterruptedException e) {
//         // :)
//         }


public class ImageTest {

  @Test
  public void testValidCreation() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    assertEquals(1024, testImage.getWidth());
    assertEquals(768, testImage.getHeight());

    Image testImageAlt = new ImageImpl(200, 300);
    assertEquals(300, testImageAlt.getWidth());
    assertEquals(200, testImageAlt.getHeight());
  }

  @Test
  public void testGetImageChannel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image testCh0 = testImage.getImageChannel(0);
    Image testCh1 = testImage.getImageChannel(1);
    Image testCh2 = testImage.getImageChannel(2);

    // TODO: Replace with testing images
    Image imageCh0 = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image imageCh1 = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image imageCh2 = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(imageCh0, testCh0);
    assertEquals(imageCh1, testCh1);
    assertEquals(imageCh2, testCh2);
  }


  @Test
  public void testFlipImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    // Horizontal flip
    Image flipImage = testImage.flipImage(true);
    // Vertical flip
    Image flipImageAlt = testImage.flipImage(false);

    // TODO: Replace with testing images
    Image imageHorizontalFlipped = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image imageVerticalFlipped = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(imageHorizontalFlipped, flipImage);
    assertEquals(imageVerticalFlipped, flipImageAlt);
  }


  @Test
  public void testGetValueImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image testValueImage = testImage.getValueImage();

    // TODO: Replace with testing images
    Image actualValueImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(actualValueImage, testValueImage);
  }


  @Test
  public void testGetIntensityImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image testIntensityImage = testImage.getIntensityImage();

    // TODO: Replace with testing image
    Image actualIntensityImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(actualIntensityImage, testIntensityImage);
  }

  @Test
  public void testGetLumaImage() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image testLumaImage = testImage.getLumaImage();

    // TODO: Replace with testing image
    Image actualLumaImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(actualLumaImage, testLumaImage);
  }

  @Test
  public void testAlterBrightness() {
    // Positive and Negative alterations
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    // TODO: Replace with testing image
    Image actualBrightnessIncreased = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image actualBrightnessDecreased = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(actualBrightnessIncreased, testImage.alterBrightness(20));
    assertEquals(actualBrightnessDecreased, testImage.alterBrightness(-20));
  }


  @Test
  public void testGetPixel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Pixel testPixel = testImage.getPixel(0, 0);

    assertEquals(101, testPixel.getChannel(0));
    assertEquals(90, testPixel.getChannel(1));
    assertEquals(58, testPixel.getChannel(2));
  }


  @Test
  public void testGetPixelChannel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    int testPixelCh0 = testImage.getPixelChannel(0, 0, 0);
    int testPixelCh1 = testImage.getPixelChannel(0, 0, 1);
    int testPixelCh2 = testImage.getPixelChannel(0, 0, 2);

    assertEquals(101, testPixelCh0);
    assertEquals(90, testPixelCh1);
    assertEquals(58, testPixelCh2);
  }


  @Test
  public void testGetNumberOfChannels() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    assertEquals(3, testImage.getNumberOfChannels());
  }


  @Test
  public void testGetHeight() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    assertEquals(768, testImage.getHeight());
  }

  @Test
  public void testGetWidth() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    assertEquals(1024, testImage.getWidth());
  }


  @Test
  public void testGetSize() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    assertEquals("1024 x 768", testImage.getSize());
  }


  @Test
  public void testSetPixel() {
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

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
    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image testGreyscaleImage = testImage.getGreyscaleImage();

    // TODO: Replace with testing images
    Image actualGreyscaleImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");

    assertEquals(actualGreyscaleImage, testGreyscaleImage);
  }


  @Test
  public void testGetBytes() {
    // TODO: TEST THIS!
  }


  @Test
  public void testToString() {
    // TODO: TEST THIS!
  }


}