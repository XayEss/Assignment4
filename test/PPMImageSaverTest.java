import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.ImageSaver;
import model.implementation.ImageConverter;
import model.interfaces.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for PPMImageSaver.
 */
public class PPMImageSaverTest {


  @Test
  public void testSave() {
    Image testImg = null;
    Image testTemp = null;
    ImageSaver testSaver = new PPMImageSaver();

    // Reading the image
    try {
      testImg = ImageConverter
              .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
                      "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    // Saving the image to alt path
    try {
      testSaver.save("resources/images/testImg.ppm", testImg);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    // Reading image from alt path
    try {
      testTemp = ImageConverter.convertFromBytes(new ImageUtil().readFile(
              "resources/images/testImg.ppm"));
    } catch (IOException e) {
      fail("Couldn't read image");
    }

    assertEquals("[[53 48 10, 49 48 10, 52 57 10, 52 57 10, 52 56 10], " +
            "[49 48 10, 53 55 10, 53 51 10, 49 48 10, 53 52 10], " +
            "[53 52 10, 49 48 10, 52 57 10, 52 56 10, 53 50 10]]", testTemp.toString());
  }

}