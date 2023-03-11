package view.impl;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.interfaces.Image;

public class VisualizeImage extends JFrame {
  JLabel labelImageLabel;

  public VisualizeImage(BufferedImage image){
    setSize(1200, 1200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    imageView(image);
  }
  public void imageView(BufferedImage image){
    JPanel panel = new JPanel();
    JLabel labelImageLabel = new JLabel(new ImageIcon(image));
    //JLabel label = new JLabel(new ImageIcon("resources/images/png/koala-vertical.png"));
    panel.add(labelImageLabel);
    add(panel);
    setVisible(true);
  }

  public void changeImage(BufferedImage image){
    labelImageLabel = new JLabel(new ImageIcon(image));
    //add(panel);
    //setVisible(true);
  }

}
