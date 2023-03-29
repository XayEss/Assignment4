import java.io.IOException;
import model.implementation.ImageConverter;
import org.junit.Test;

import java.awt.image.BufferedImage;

import controller.implementation.ImageUtil;
import model.interfaces.Image;

import static model.implementation.ImageToBufferedImageService.convertToBuffered;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The ImageToBufferedImageServiceTest class is a JUnit test case for the
 * ImageToBufferedImageService class.
 * It tests the functionality of the convertToBuffered() method by using a sample PPM image and
 * verifying the properties of the resulting BufferedImage.
 */
public class ImageToBufferedImageServiceTest {

  @Test
  public void testConvertToBuffered() {
    Image testImg = null;
    try{
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }

    BufferedImage testBuffer = convertToBuffered(testImg);

    assertEquals(5, testBuffer.getWidth());
    assertEquals(3, testBuffer.getHeight());
    assertEquals(1, testBuffer.getType());
  }


}