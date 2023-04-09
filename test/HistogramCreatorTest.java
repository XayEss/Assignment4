import org.junit.Test;

import java.io.IOException;

import controller.implementation.UniversalImageLoader;
import controller.interfaces.ImageInput;
import model.implementation.HistogramCreator;
import model.implementation.ImageConverter;
import model.interfaces.Image;

import static org.junit.Assert.*;

public class HistogramCreatorTest {

  @Test
  public void testHistogram() {

    Image testImg = null;

    ImageInput imageLoader = new UniversalImageLoader();

    try {
      testImg = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/images/new_examples/raiden-min.png"));
    } catch (IOException e) {
      fail("Couldn't read the image");
    }

    HistogramCreator temp = new HistogramCreator();

    int[][] hist = temp.getRGBHistograms(testImg);

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.println(hist[j][i]);
      }
    }

    // TODO: Complete testing.

  }

}