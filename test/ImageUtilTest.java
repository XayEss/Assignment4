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
    assertEquals(testImg.toString(), "[[101 90 58, 103 92 62, 110 95 66, 104 91 59, " +
            "104 91 59], [104 93 63, 108 94 65, 100 86 57, 103 90 56, 105 91 64], " +
            "[101 89 63, 103 91 65, 106 92 66, 103 86 66, 105 91 64]]");

  }


}