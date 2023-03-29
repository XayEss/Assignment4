import java.io.IOException;
import model.implementation.ImageConverter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.ImageSaver;
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
    try{
      testImg = ImageConverter
          .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
          "testBaseImage.ppm"));
    } catch(IOException e) {
      fail("Couldn't read the image");
    }

    ImageSaver testSaver = new PPMImageSaver();

    try {
      testSaver.save("resources/images/testImg.ppm", testImg);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    assertEquals(testImg, new ImageUtil().readFile("resources/images/testImg.ppm"));
  }

}