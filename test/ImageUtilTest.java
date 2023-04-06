import org.junit.Test;

import java.io.IOException;

import controller.implementation.ImageUtil;
import controller.interfaces.ImageInput;
import model.implementation.ImageConverter;
import model.interfaces.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for ImageUtil.
 */
public class ImageUtilTest {


  @Test
  public void testReadFile() {
    Image testImg = null;
    try {
      ImageInput testImgUtil = new ImageUtil();
      testImg = ImageConverter
              .convertFromBytes(testImgUtil.readFile("resources/images/ppm_testing/" +
                      "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Eroor when reading a picture");
    }
    assertEquals(testImg.toString(), "[[57 50 10, 54 50 10, 49 49 48, 10 57 53, 10 54 54]" +
            ", [10 49 48, 52 10 57, 49 10 53, 57 10 49, 48 52 10], " +
            "[57 49 10, 53 57 10, 49 48 52, 10 57 51, 10 54 51]]");

  }


}