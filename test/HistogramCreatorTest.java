import org.junit.Test;

import java.io.IOException;

import controller.implementation.UniversalImageLoader;
import controller.interfaces.ImageInput;
import model.implementation.HistogramCreator;
import model.implementation.ImageConverter;
import model.interfaces.Image;

import static org.junit.Assert.*;


/**
 * Test class for Histogram Creator.
 */
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

    int[] testHist1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2050, 0, 0, 0, 0, 0, 0, 0, 0, 5109,
            0, 0, 0, 0, 0, 0, 0, 5681, 4783, 0, 0, 0, 0, 0, 0, 7247, 0, 8231, 0, 0, 0, 2049, 0,
            13461, 0, 0, 3897, 0, 8705, 0, 0, 8561, 0, 0, 5956, 0, 0, 0, 0, 9700, 9837, 0, 0, 0,
            0, 16126, 0, 0, 6427, 0, 6757, 7242, 0, 3395, 4223, 7566, 2822, 8183, 0, 4525, 2404,
            3937, 1025, 2670, 5383, 7757, 0, 0, 5966, 4014, 0, 3343, 3589, 0, 3284, 7523, 4517,
            2950, 1668, 3657, 0, 3398, 4886, 4252, 0, 2276, 6665, 3000, 4343, 0, 5579, 9706, 1966,
            1710, 2684, 5309, 4815, 4799, 1212, 0, 10811, 6633, 7064, 0, 1412, 10495, 4629, 0,
            8167, 2153, 5177, 10152, 10036, 0, 0, 5825, 9586, 878, 631, 0, 7223, 7465, 2582, 0,
            6244, 8291, 0, 0, 3065, 12255, 8165, 0, 0, 11186, 0, 5288, 5350, 8188, 2221, 4613, 0,
            3193, 4345, 5033, 3189, 5968, 9710, 10004, 0, 12274, 3602, 4258, 6209, 17402, 8664,
            4552, 8902, 8757, 8136, 6185, 0, 5275, 2299, 13690, 6806, 3907, 5879, 1742, 4118,
            13352, 4808, 0, 7260, 0, 10549, 4265, 3733, 12415, 0, 8731, 9720, 1630, 7932, 4544, 0,
            4572, 0, 3344, 4172, 4964, 768, 4224, 4047, 0, 0, 2626, 6191, 2250, 0, 2996, 3323,
            6030, 571, 3023, 5947, 0, 3370, 5997, 2522, 6337, 1347, 6392, 8833, 0, 10141, 0, 4449,
            6492, 4936, 190, 9053, 0, 4139, 0, 2341, 1912, 0, 1781, 3616, 2261, 0, 0, 0};

    int[] testHist2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2050, 0, 0, 0, 0, 0, 0, 0, 5109, 0, 0,
            0, 0, 5681, 0, 0, 4783, 0, 0, 7247, 0, 0, 8231, 3897, 0, 0, 2049, 13461, 1718, 0, 4875,
            8705, 0, 9642, 5977, 5541, 0, 4159, 8958, 9653, 0, 6757, 0, 5612, 13522, 6866, 2637,
            4525, 0, 9189, 0, 13415, 0, 3421, 5383, 3284, 7693, 5869, 0, 3589, 3657, 8827, 0, 873,
            4495, 5920, 2462, 4960, 7480, 0, 0, 6276, 8167, 1350, 0, 11804, 1480, 4799, 0, 8309,
            1822, 2092, 4775, 11638, 2044, 0, 6111, 0, 4457, 13659, 1710, 4041, 3378, 12586, 0, 0,
            9379, 10807, 5133, 4345, 7232, 0, 2221, 9883, 3307, 0, 5354, 9621, 3233, 0, 1304, 15825,
            0, 3781, 9699, 5411, 1572, 7120, 8660, 0, 8165, 5953, 5898, 11475, 4306, 2233, 9798,
            649, 11040, 0, 4118, 7773, 3928, 0, 7598, 5394, 9214, 2939, 0, 14979, 1987, 11225, 2942,
            11813, 773, 4790, 15643, 6854, 3733, 16464, 0, 8136, 1489, 0, 5430, 3902, 4235, 5275,
            3275, 10451, 0, 3907, 0, 14705, 2244, 0, 4808, 1853, 2722, 707, 3154, 3317, 0, 3466,
            3783, 6026, 4923, 2036, 4797, 7190, 0, 0, 2648, 2880, 0, 1100, 3075, 0, 2522, 0, 3023,
            2219, 0, 0, 1071, 3281, 3089, 0, 0, 4445, 0, 2311, 4449, 1347, 0, 3994, 4936, 0, 0,
            3681, 5408, 0, 3656, 0, 4139, 4026, 0, 0, 8304, 0, 0, 7893, 0, 5436, 0, 0, 6492, 3616,
            0, 0, 0, 5906, 0, 0, 0, 0, 0, 1781, 0, 0, 0, 0, 0, 0};

    int[] testHist3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2050, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 5109, 0, 0, 0, 5681, 0, 0, 0, 0, 0, 3897, 0, 4783, 8965, 0, 0, 0, 0, 4875, 0,
            12454, 5541, 0, 7571, 3937, 5430, 0, 0, 3329, 11414, 0, 8705, 3284, 0, 5860, 0, 5990,
            9613, 0, 0, 2049, 5912, 11577, 0, 5977, 873, 3686, 4810, 10018, 6635, 0, 6058, 4799,
            6757, 0, 10457, 4159, 6169, 8476, 2822, 5411, 4525, 3000, 0, 10184, 11023, 0, 10819,
            3055, 0, 6297, 15636, 5172, 1966, 4037, 7250, 9854, 7246, 10348, 0, 4345, 17461, 0, 0,
            6052, 11745, 4588, 3702, 10825, 0, 0, 11443, 12770, 0, 0, 14995, 7558, 4108, 7120, 5647,
            8038, 2416, 13453, 0, 5073, 3465, 14082, 0, 14870, 768, 6149, 5394, 8388, 0, 6023, 5410,
            5033, 1855, 14068, 5000, 0, 6857, 10200, 5367, 10987, 8236, 15475, 1610, 1895, 13517,
            8902, 3262, 571, 12708, 5503, 1554, 1958, 4964, 0, 1742, 5879, 7128, 1278, 11823, 1280,
            0, 3907, 8113, 2224, 0, 577, 3232, 4195, 382, 0, 2490, 5165, 12579, 589, 4797, 0, 5205,
            4934, 0, 429, 0, 707, 4463, 6257, 0, 1100, 0, 594, 4445, 2886, 0, 0, 7445, 0, 0, 0,
            7354, 0, 0, 3023, 5408, 0, 0, 3994, 0, 2899, 4139, 3681, 0, 0, 1912, 3656, 2311, 0,
            2544, 4026, 0, 0, 6392, 3616, 0, 5552, 0, 5314, 0, 2261, 0, 6492, 0, 0, 0, 3645, 0, 0,
            0, 0, 1781, 0, 0, 0, 0, 0, 0, 0};

    int[][] mergedArray = new int[3][256];

    for (int i = 0; i < 256; i++) {
      mergedArray[0][i] = testHist1[i];
      mergedArray[1][i] = testHist2[i];
      mergedArray[2][i] = testHist3[i];
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 256; j++) {
        assertEquals(mergedArray[i][j], hist[i][j]);
      }
    }

  }

}