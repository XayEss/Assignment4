import org.junit.Test;

import java.awt.image.BufferedImage;

import controller.implementation.ImageUtil;
import model.interfaces.Image;

import static model.implementation.ImageToBufferedImageService.convertToBuffered;
import static org.junit.Assert.assertEquals;

/**
 * The ImageToBufferedImageServiceTest class is a JUnit test case for the
 * ImageToBufferedImageService class.
 * It tests the functionality of the convertToBuffered() method by using a sample PPM image and
 * verifying the properties of the resulting BufferedImage.
 */
public class ImageToBufferedImageServiceTest {

  @Test
  public void testConvertToBuffered() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    BufferedImage testBuffer = convertToBuffered(testImg);

    assertEquals(5, testBuffer.getWidth());
    assertEquals(3, testBuffer.getHeight());
    assertEquals(1, testBuffer.getType());
  }


}