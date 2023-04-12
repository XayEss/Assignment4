package view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class ScrollableImagePanel extends JPanel {
  private BufferedImage image;
  private int x;
  private int y;

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
    if(image != null) {
      paintScroll(g);
    }
  }

  private void paintScroll(Graphics g){
    int width = getWidth();
    int height = getHeight();
    BufferedImage sub = image.getSubimage(x, y, Math.min(width, image.getWidth()), Math.min(height, image.getHeight()));
    g.drawImage(sub, 0, 0, width, height, null);

  }

  public void scrollXNegative(){
    changeX(-10);
  }

  public void scrollXPositive(){
    changeX(10);
  }

  public void scrollYPositive(){
    changeY(10);
  }

  public void scrollYNegative(){
    changeY(-10);
  }

  private void changeX(int value){
    x += value;
    x = Math.max(0, Math.min(x, image.getWidth()-getWidth()));
  }

  private void changeY(int value){
    y += value;
    y = Math.max(0, Math.min(y, image.getHeight()-getHeight()));
  }

  public void setImage(BufferedImage image){
    this.image = image;
    repaint();
  }
}