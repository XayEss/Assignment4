import org.junit.Test;

import java.io.IOException;

import controller.implementation.ImageUtil;
import controller.implementation.UniversalImageLoader;
import controller.implementation.UniversalImageSaver;
import controller.interfaces.ImageInput;
import controller.interfaces.ImageSaver;
import model.implementation.ImageConverter;
import model.interfaces.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for UniversalImageSaver and Loader.
 */
public class UniversalSaveLoadTest {

  @Test
  public void testUniversalImageSaveLoadPPMtoPNG() {

    Image testImg = null;
    Image loadedTestImg = null;

    ImageInput imageLoader = new UniversalImageLoader();
    ImageSaver imageSaver = new UniversalImageSaver();

    try {
      testImg = ImageConverter
              .convertFromBytes(new ImageUtil().readFile("resources/images/ppm_testing/" +
                      "testBaseImage.ppm"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    try {
      imageSaver.save("resources/images/testBaseImage.png", ImageConverter.convertToBytes(testImg));
    } catch (IOException e) {
      fail("Couldn't save the image");
    }

    try {
      loadedTestImg = ImageConverter
              .convertFromBytes(imageLoader.readFile("resources/images/testBaseImage.png"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    assertEquals(testImg, loadedTestImg);
  }

}
