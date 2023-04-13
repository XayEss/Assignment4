package model.implementation;

import model.interfaces.Image;

/**
 * Class to create histograms for Images.
 */
public class HistogramCreator {

  /**
   * Calculates the RGB histograms for the image.
   *
   * @return An array of integer arrays representing the histograms for RGB channels.
   */
  public int[][] getRGBHistograms(Image img) {
    int[][] histogram = new int[3][256]; // 3 channels with 256 possible values each

    // Initialize histograms
    for (int channel = 0; channel < 3; channel++) {
      for (int value = 0; value < 256; value++) {
        histogram[channel][value] = 0;
      }
    }

    // Calculate histograms
    int width = img.getWidth();
    int height = img.getHeight();
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < 3; channel++) {
          int pixelValue = img.getPixelChannel(row, col, channel);
          histogram[channel][pixelValue]++;
        }
      }
    }

    return histogram;
  }

}
