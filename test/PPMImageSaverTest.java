import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import controller.implementation.ImageUtil;
import controller.implementation.PPMImageSaver;
import controller.interfaces.ImageSaver;
import model.interfaces.Image;

import static org.junit.Assert.assertEquals;

/**
 * Test class for PPMImageSaver.
 */
public class PPMImageSaverTest implements ImageSaver {

  @Override
  public void save(String path, Image image) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(path);
    FileOutputStream fileOutputStream = new FileOutputStream(path);
    writer.println("P3");
    writer.println(image.getWidth() + " " + image.getHeight());
    writer.println("255");
    int k = 0;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        writer.println(image.getPixelChannel(i, j, 0));
        writer.println(image.getPixelChannel(i, j, 1));
        writer.println(image.getPixelChannel(i, j, 2));
      }
    }
    writer.close();
  }


  @Test
  public void testSave() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/" +
            "testBaseImage.ppm");

    ImageSaver testSaver = new PPMImageSaver();

    try {
      testSaver.save("resources/images/testImg.ppm", testImg);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    assertEquals(testImg, new ImageUtil().readFile("resources/images/testImg.ppm"));
  }

}