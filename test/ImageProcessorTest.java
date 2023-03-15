import org.junit.Test;

import controller.implementation.ImageUtil;
import model.implementation.ImageProcessorImpl;

import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;

import static org.junit.Assert.*;

public class ImageProcessorTest {

  @Test
  public void testGetSplitChannels() {
    Image testImg = new ImageUtil().readFile("resources/images/ppm_testing/testBaseImage.ppm");

    ImageProcessor testProcessor = new ImageProcessorImpl();


  }

}