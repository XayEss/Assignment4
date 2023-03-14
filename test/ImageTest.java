import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import view.impl.VisualizeImage;

import static org.junit.Assert.*;

public class ImageTest {

  @Test
  public void testFlipImage() {

    Image testImage = new ImageUtil().readFile("resources/images/ppm/Koala.ppm");
    Image flipImage = testImage.flipImage(true);

    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(flipImage));
    try {
      Thread.sleep(100000000);
    } catch (InterruptedException e) {
      // :)
    }

  }

}