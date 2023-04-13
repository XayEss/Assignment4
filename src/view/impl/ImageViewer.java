package view.impl;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.implementation.HistogramCreator;
import model.interfaces.Image;

/**
 * This class ouputs the Histogram visualization.
 */
public class ImageViewer extends JPanel {
  private Image image;

  /**
   * Constructor that sets the Image for the class.
   *
   * @param image Histogram visualization.
   */
  public ImageViewer(Image image) {
    this.image = image;
  }
  public ImageViewer(){

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(image != null)
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

  public void setImage(Image image) {
    this.image = image;
    repaint();
  }
}
