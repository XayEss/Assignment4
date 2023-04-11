package view.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class ScrollableImagePanel extends JPanel {
  private final BufferedImage image;
  private int x = 0;
  private int y = 0;

  public ScrollableImagePanel(BufferedImage image) {
    this.image = image;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    paintScroll(g);
  }

  private void paintScroll(Graphics g){
    int width = getWidth();
    int height = getHeight();
    BufferedImage sub = image.getSubimage(x,y, width,height);
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
  }

  private void changeY(int value){
    y += value;
  }
}