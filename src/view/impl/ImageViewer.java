package view.impl;

import model.implementation.HistogramCreator;
import model.interfaces.Image;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

public class ImageViewer extends JPanel {
  private Image image;

  public ImageViewer(Image image) {
    this.image = image;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawRGBHistograms(g);
  }

  private void drawRGBHistograms(Graphics g) {

    HistogramCreator histogramCreator = new HistogramCreator();
    int[][] rgbHistograms = histogramCreator.getRGBHistograms(image);

    int maxCount = 0;
    for (int channel = 0; channel < 3; channel++) {
      for (int count : rgbHistograms[channel]) {
        maxCount = Math.max(maxCount, count);
      }
    }

    int width = getWidth() / 3;
    int height = getHeight();
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
