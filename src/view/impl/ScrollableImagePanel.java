package view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * This class implements an ImagePanel which can be scrolled.
 */
public class ScrollableImagePanel extends JPanel {
  private BufferedImage image;
  private int x;
  private int y;

  /**
   * Constructor that initializes the image for which the panel has to be made.
   *
   * @param image BufferedImage for which panel is to be made.
   */
  public ScrollableImagePanel(BufferedImage image) {
    x = 0;
    y = 0;
    this.image = image;
  }

  public ScrollableImagePanel() {
    x = 0;
    y = 0;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (image != null) {
      paintScroll(g);
    }
  }

  private void paintScroll(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    BufferedImage sub = image.getSubimage(x, y, Math.min(width, image.getWidth()),
            Math.min(height, image.getHeight()));
    //BufferedImage sub = image.getSubimage(x, y, image.getWidth(), image.getHeight());
    g.drawImage(sub, Math.max(0, width/2-image.getWidth()/2), Math.max(0, height/2-image.getHeight()/2), Math.min(width, image.getWidth()), Math.min(height, image.getHeight()), null);
    //g.drawImage(sub, 0, 0, Math.min(width, image.getWidth()), Math.min(height, image.getHeight()), null);
    //g.drawImage(sub, 0, 0, width, height,  null);

  }

  public void scrollXNegative() {
    changeX(-10);
  }

  public void scrollXPositive() {
    changeX(10);
  }

  public void scrollYPositive() {
    changeY(10);
  }

  public void scrollYNegative() {
    changeY(-10);
  }

  private void changeX(int value) {
    x += value;
    x = Math.max(0, Math.min(x, image.getWidth() - getWidth()));
  }

  private void changeY(int value) {
    y += value;
    y = Math.max(0, Math.min(y, image.getHeight() - getHeight()));
  }

  public void setImage(BufferedImage image) {
    this.image = image;
    repaint();
  }
}