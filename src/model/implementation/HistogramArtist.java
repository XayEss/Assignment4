package model.implementation;

import java.awt.Color;
import java.awt.Graphics;

public class HistogramArtist {

  public static void drawHistogram(Graphics g, int[][] rgbHistograms, int imageWidth,  int imageHeight){
    HistogramCreator histogramCreator = new HistogramCreator();

    int maxCount = 0;
    for (int channel = 0; channel < 3; channel++) {
      for (int count : rgbHistograms[channel]) {
        maxCount = Math.max(maxCount, count);
      }
    }

    int width = imageWidth / 3;
    int height = imageHeight;
    int binWidth = width / rgbHistograms[0].length;

    Color[] channelColors = {Color.RED, Color.GREEN, Color.BLUE};
    for (int channel = 0; channel < 3; channel++) {
      g.setColor(channelColors[channel]);
      for (int i = 0; i < rgbHistograms[channel].length; i++) {
        int binHeight = (int) (((double) rgbHistograms[channel][i] / maxCount) * height);
        g.fillRect(channel * width + i * binWidth, height - binHeight, binWidth, binHeight);
      }
    }
  }

}
