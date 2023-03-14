import org.junit.Test;

import java.util.Random;

import model.implementation.PixelRGB;
import model.interfaces.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PixelTest {

  @Test
  public void testValidPixelCreation() {
    // RGB pixel
    Pixel test = new PixelRGB(10, 240, 66);
    assertEquals(10, test.getChannel(0));
    assertEquals(240, test.getChannel(1));
    assertEquals(66, test.getChannel(2));

    // Edge cases
    Pixel edge3 = new PixelRGB(0, 0, 0);
    assertEquals(0, edge3.getChannel(0));
    assertEquals(0, edge3.getChannel(1));
    assertEquals(0, edge3.getChannel(2));

    Pixel edge4 = new PixelRGB(255, 255, 255);
    assertEquals(255, edge4.getChannel(0));
    assertEquals(255, edge4.getChannel(1));
    assertEquals(255, edge4.getChannel(2));


    // Greyscale pixel
    Pixel test1 = new PixelRGB(10);
    assertEquals(10, test1.getChannel(0));
    assertEquals(10, test1.getChannel(1));
    assertEquals(10, test1.getChannel(2));

    // Edge cases
    Pixel edge1 = new PixelRGB(0);
    assertEquals(0, edge1.getChannel(0));
    assertEquals(0, edge1.getChannel(1));
    assertEquals(0, edge1.getChannel(2));


    Pixel edge2 = new PixelRGB(255);
    assertEquals(255, edge2.getChannel(0));
    assertEquals(255, edge2.getChannel(1));
    assertEquals(255, edge2.getChannel(2));


    // RGB Pixel from existing pixel
    Pixel testRGB = new PixelRGB(test);
    assertEquals(10, testRGB.getChannel(0));
    assertEquals(240, testRGB.getChannel(1));
    assertEquals(66, testRGB.getChannel(2));

    // Greyscale Pixel from existing pixel
    Pixel testGrey = new PixelRGB(test1);
    assertEquals(10, testGrey.getChannel(0));
    assertEquals(10, testGrey.getChannel(1));
    assertEquals(10, testGrey.getChannel(2));
  }

  @Test
  public void testInvalidPixelCreation() {
    // Pixel Value out of range +ve & -ve

    Random rand = new Random();

    // Greyscale Pixel Creation
    for (int i = 0; i < 100000; i++) {
      int val1 = rand.nextInt(400);

      if (val1 > 255) {
        try {
          Pixel test = new PixelRGB(val1);
        } catch (IllegalArgumentException e) {
          // Do nothing
        }
      } else {
        Pixel test = new PixelRGB(val1);
        assertEquals(val1, test.getChannel(0));
        assertEquals(val1, test.getChannel(1));
        assertEquals(val1, test.getChannel(2));
      }
    }

    // RGB Pixel Creation
    for (int i = 0; i < 10000; i++) {
      int val1 = rand.nextInt(400);
      int val2 = rand.nextInt(400);
      int val3 = rand.nextInt(400);

      if (val1 > 255 || val2 > 255 || val3 > 255) {
        try {
          Pixel test = new PixelRGB(val1, val2, val3);
        } catch (IllegalArgumentException e) {
          // Do nothing
        }
      } else {
        Pixel test = new PixelRGB(val1, val2, val3);
        assertEquals(val1, test.getChannel(0));
        assertEquals(val2, test.getChannel(1));
        assertEquals(val3, test.getChannel(2));
      }
    }

  }

  @Test
  public void testGetChannel() {
    Random rand = new Random();

    // For Greyscale Image
    for (int i = 0; i < 100000; i++) {
      int val = rand.nextInt(255);

      Pixel test = new PixelRGB(val);

      assertEquals(val, test.getChannel(0));
      assertEquals(val, test.getChannel(1));
      assertEquals(val, test.getChannel(2));
    }

    // For RGB Image
    for (int i = 0; i < 100000; i++) {
      int val1 = rand.nextInt(255);
      int val2 = rand.nextInt(255);
      int val3 = rand.nextInt(255);

      Pixel test = new PixelRGB(val1, val2, val3);

      assertEquals(val1, test.getChannel(0));
      assertEquals(val2, test.getChannel(1));
      assertEquals(val3, test.getChannel(2));
    }
  }

  @Test
  public void testSetChannel() {
    Random rand = new Random();

    // For Greyscale Image
    for (int i = 0; i < 100000; i++) {
      int val = rand.nextInt(255);

      Pixel test = new PixelRGB(val);

      int alt1 = rand.nextInt(255);
      int alt2 = rand.nextInt(255);
      int alt3 = rand.nextInt(255);

      test.setChannel(0, alt1);
      test.setChannel(1, alt2);
      test.setChannel(2, alt3);

      assertEquals(alt1, test.getChannel(0));
      assertEquals(alt2, test.getChannel(1));
      assertEquals(alt3, test.getChannel(2));

    }

    // For RGB Image
    for (int i = 0; i < 100000; i++) {
      int val1 = rand.nextInt(255);
      int val2 = rand.nextInt(255);
      int val3 = rand.nextInt(255);

      Pixel test = new PixelRGB(val1, val2, val3);

      int alt1 = rand.nextInt(255);
      int alt2 = rand.nextInt(255);
      int alt3 = rand.nextInt(255);

      test.setChannel(0, alt1);
      test.setChannel(1, alt2);
      test.setChannel(2, alt3);

      assertEquals(alt1, test.getChannel(0));
      assertEquals(alt2, test.getChannel(1));
      assertEquals(alt3, test.getChannel(2));
    }
  }

  @Test
  public void testGetOneChanneledPixel() {

    Random rand = new Random();

    // Greyscale Image - One channeled pixel
    for (int i = 0; i < 100000; i++) {
      int val = rand.nextInt(255);

      Pixel test = new PixelRGB(val);

      Pixel testR = test.getOneChanneledPixel(0);
      Pixel testG = test.getOneChanneledPixel(1);
      Pixel testB = test.getOneChanneledPixel(2);

      assertEquals(val, testR.getChannel(0));
      assertEquals(0, testR.getChannel(1));
      assertEquals(0, testR.getChannel(2));

      assertEquals(0, testG.getChannel(0));
      assertEquals(val, testG.getChannel(1));
      assertEquals(0, testG.getChannel(2));

      assertEquals(0, testB.getChannel(0));
      assertEquals(0, testB.getChannel(1));
      assertEquals(val, testB.getChannel(2));
    }

    // RGB Image - One channeled pixel
    for (int i = 0; i < 100000; i++) {
      int val1 = rand.nextInt(255);
      int val2 = rand.nextInt(255);
      int val3 = rand.nextInt(255);

      Pixel test = new PixelRGB(val1, val2, val3);

      Pixel testR = test.getOneChanneledPixel(0);
      Pixel testG = test.getOneChanneledPixel(1);
      Pixel testB = test.getOneChanneledPixel(2);

      assertEquals(val1, testR.getChannel(0));
      assertEquals(0, testR.getChannel(1));
      assertEquals(0, testR.getChannel(2));

      assertEquals(0, testG.getChannel(0));
      assertEquals(val2, testG.getChannel(1));
      assertEquals(0, testG.getChannel(2));

      assertEquals(0, testB.getChannel(0));
      assertEquals(0, testB.getChannel(1));
      assertEquals(val3, testB.getChannel(2));
    }
  }

  @Test
  public void testAlterValue() {

    Random rand = new Random();

    // Greyscale Image
    for (int i = 0; i < 100000; i++) {
      int val = rand.nextInt(255);

      Pixel test = new PixelRGB(val);
      Pixel testNeg = new PixelRGB(val);

      int alt = rand.nextInt(255);
      
      if (alt + val > 255) {
        continue;
      }

      // TODO: alterValue for Greyscale shouldn't require channel?
      test.alterValue(0, alt);
      assertEquals(val+alt, test.getChannel(0));

      test.alterValue(1, alt);
      assertEquals(val+alt, test.getChannel(1));

      test.alterValue(2, alt);
      assertEquals(val+alt, test.getChannel(2));

      alt = -1 * alt;

      if (alt + val < 0) {
        continue;
      }

      testNeg.alterValue(0, alt);
      assertEquals(val+alt, testNeg.getChannel(0));

      testNeg.alterValue(1, alt);
      assertEquals(val+alt, testNeg.getChannel(1));

      testNeg.alterValue(2, alt);
      assertEquals(val+alt, testNeg.getChannel(2));

    }

    // RGB test
    for (int i = 0; i < 100000; i++) {
      int val0 = rand.nextInt(255);
      int val1 = rand.nextInt(255);
      int val2 = rand.nextInt(255);
      
      Pixel test = new PixelRGB(val0, val1, val2);
      Pixel testNeg = new PixelRGB(val0, val1, val2);

      int alt0 = rand.nextInt(255);
      int alt1 = rand.nextInt(255);
      int alt2 = rand.nextInt(255);
      

      if (alt0 + val0 < 255 ) {
        test.alterValue(0, alt0);
        assertEquals(val0+alt0, test.getChannel(0));
      }

      if (alt1 + val1 < 255) {
        test.alterValue(1, alt1);
        assertEquals(val1+alt1, test.getChannel(1));
      }
      
      if (alt2 + val2 < 255) {
        test.alterValue(2, alt2);
        assertEquals(val2+alt2, test.getChannel(2));
      }

      // Checking that no other channels are affected by the operations
      if (alt0 + val0 < 255) {
        assertEquals(val0+alt0, test.getChannel(0));
      } else {
        assertEquals(val0, test.getChannel(0));
      }

      if (alt1 + val1 < 255) {
        assertEquals(val1+alt1, test.getChannel(1));
      } else {
        assertEquals(val1, test.getChannel(1));
      }

      if (alt2 + val2 < 255) {
        assertEquals(val2+alt2, test.getChannel(2));
      } else {
        assertEquals(val2, test.getChannel(2));
      }

      alt0 = -1 * alt0;
      alt1 = -1 * alt1;
      alt2 = -1 * alt2;
      

      if (alt0 + val0 > 0) {
        testNeg.alterValue(0, alt0);
        assertEquals(val0+alt0, testNeg.getChannel(0));
      } else {
        assertEquals(val0, testNeg.getChannel(0));
      }

      if (alt1 + val1 > 0) {
        testNeg.alterValue(1, alt1);
        assertEquals(val1+alt1, testNeg.getChannel(1));
      } else {
        assertEquals(val1, testNeg.getChannel(1));
      }

      if (alt2 + val2 > 0) {
        testNeg.alterValue(2, alt2);
        assertEquals(val2+alt2, testNeg.getChannel(2));
      } else {
        assertEquals(val2, testNeg.getChannel(2));
      }
    }
    
    // Alter above max pixel value
    // Greyscale
    Pixel testMax = new PixelRGB(200);
    try{
      testMax.alterValue(0, 60);
      fail("Shouldn't be able to increase pixel value above 255!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }

    // RGB
    Pixel testMaxRGB = new PixelRGB(200);
    try{
      testMaxRGB.alterValue(0, 60);
      fail("Shouldn't be able to increase pixel value above 255!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
  try{
    testMaxRGB.alterValue(1, 60);
    fail("Shouldn't be able to increase pixel value above 255!!");
  } catch (IllegalArgumentException e) {
    // Do Nothing
  }
  try{
    testMaxRGB.alterValue(2, 60);
    fail("Shouldn't be able to increase pixel value above 255!!");
  } catch (IllegalArgumentException e) {
    // Do Nothing
  }


    // Alter below min pixel value
    // Greyscale
    Pixel testMin = new PixelRGB(20);
    try{
      testMin.alterValue(0, -60);
      fail("Shouldn't be able to decrease pixel value below 0!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }

    // RGB
    Pixel testMinRGB = new PixelRGB(20);
    try{
      testMinRGB.alterValue(0, -60);
      fail("Shouldn't be able to decrease pixel value below 0!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    try{
      testMinRGB.alterValue(1, -60);
      fail("Shouldn't be able to decrease pixel value below 0!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    try{
      testMinRGB.alterValue(2, -60);
      fail("Shouldn't be able to decrease pixel value below 0!!");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
  }


  @Test
  public void testAlterBrightness() {

    // Greyscale
    Pixel testGrey = new PixelRGB(240);

    testGrey.alterBrightness(10);
    assertEquals(250, testGrey.getChannel(0));
    assertEquals(250, testGrey.getChannel(1));
    assertEquals(250, testGrey.getChannel(2));

    testGrey.alterBrightness(-20);
    assertEquals(230, testGrey.getChannel(0));
    assertEquals(230, testGrey.getChannel(1));
    assertEquals(230, testGrey.getChannel(2));

    // RGB
    Pixel testRGB = new PixelRGB(240, 240, 240);
    testRGB.alterBrightness(10);
    assertEquals(250, testRGB.getChannel(0));
    assertEquals(250, testRGB.getChannel(1));
    assertEquals(250, testRGB.getChannel(2));

    testRGB.alterBrightness(-20);
    assertEquals(230, testRGB.getChannel(0));
    assertEquals(230, testRGB.getChannel(1));
    assertEquals(230, testRGB.getChannel(2));

    // Alter above max value [Grey/RGB]
    testGrey.alterBrightness(200);
    assertTrue(testGrey.getChannel(0) <= 255);
    assertTrue(testGrey.getChannel(0) >= 0);


    testRGB.alterBrightness(200);
    assertTrue(testRGB.getChannel(0) <= 255);
    assertTrue(testRGB.getChannel(0) >= 0);
    assertTrue(testRGB.getChannel(1) <= 255);
    assertTrue(testRGB.getChannel(1) >= 0);
    assertTrue(testRGB.getChannel(2) <= 255);
    assertTrue(testRGB.getChannel(2) >= 0);


    // Alter below min value [Grey/RGB]
    testGrey.alterBrightness(-300);
    assertTrue(testGrey.getChannel(0) <= 255);
    assertTrue(testGrey.getChannel(0) >= 0);


    testRGB.alterBrightness(-300);
    assertTrue(testRGB.getChannel(0) <= 255);
    assertTrue(testRGB.getChannel(0) >= 0);
    assertTrue(testRGB.getChannel(1) <= 255);
    assertTrue(testRGB.getChannel(1) >= 0);
    assertTrue(testRGB.getChannel(2) <= 255);
    assertTrue(testRGB.getChannel(2) >= 0);
  }


  @Test
  public void testGetValue() {
    // Greyscale
    Pixel testGrey = new PixelRGB(10);
    assertEquals(10, testGrey.getValue());

    // RGB
    Pixel testRGB0 = new PixelRGB(10, 10, 20);
    Pixel testRGB1 = new PixelRGB(10, 20, 10);
    Pixel testRGB2 = new PixelRGB(20, 10, 10);

    assertEquals(20, testRGB0.getValue());
    assertEquals(20, testRGB1.getValue());
    assertEquals(20, testRGB2.getValue());

  }


  @Test
  public void testGetIntensity() {
    // Greyscale
    Pixel testGrey = new PixelRGB(17);
    assertEquals(17, testGrey.getIntensity());

    // RGB
    Pixel testRGB = new PixelRGB(12, 10, 100);
    assertEquals(122 / 3, testRGB.getIntensity());
  }


  @Test
  public void testGetLuma() {

    // Greyscale
    Pixel testGrey = new PixelRGB(33);
    assertEquals(33, testGrey.getLuma());

    // RGB
    Pixel testRGB = new PixelRGB(100, 100, 100);
    assertEquals((int) (0.2126 * testRGB.getChannel(0) + 0.7152 * testRGB.getChannel(1)
            + 0.0722 * testRGB.getChannel(2)), testRGB.getLuma());
  }


  @Test
  public void testGetGreyScale() {

    // Greyscale
    Pixel testGrey = new PixelRGB(17);
    assertEquals(17, testGrey.getGreyScale());

    // RGB
    Pixel testRGB0 = new PixelRGB(0, 0, 0);
    Pixel testRGB1 = new PixelRGB(100, 100, 100);
    Pixel testRGB2 = new PixelRGB(255, 255, 255);

    assertEquals(0, testRGB0.getGreyScale());
    assertEquals(100, testRGB1.getGreyScale());
    assertEquals(255, testRGB2.getGreyScale());
  }


  @Test
  public void testGetNumberOfChannels() {

    Pixel testGreyscale = new PixelRGB(10);
    assertEquals(3, testGreyscale.getNumberOfChannels());

    Pixel testRGB = new PixelRGB(10, 10, 10);
    assertEquals(3, testRGB.getNumberOfChannels());
  }

}



