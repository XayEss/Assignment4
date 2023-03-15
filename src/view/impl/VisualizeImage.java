package view.impl;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The VisualizeImage class represents a JFrame for displaying an image.
 */
public class VisualizeImage extends JFrame {
  JPanel panel;
  JLabel labelImageLabel;

  /**
   * Constructs a new VisualizeImage object with the given BufferedImage.
   *
   * @param image the image to be displayed
   */
  public VisualizeImage(BufferedImage image) {
    setSize(1200, 1200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    imageView(image);
  }

  /**
   * Sets up the JPanel and JLabel to display the given BufferedImage.
   *
   * @param image the image to be displayed
   */
  public void imageView(BufferedImage image) {
    JPanel panel = new JPanel();
    JLabel labelImageLabel = new JLabel();
    labelImageLabel.setIcon(new ImageIcon(image));
    //JLabel label = new JLabel(new ImageIcon("resources/images/png/koala-vertical.png"));
    panel.add(labelImageLabel);
    add(panel);
    setVisible(true);
  }


  /**
   * Changes the displayed image to the given BufferedImage.
   *
   * @param image the image to be displayed
   */
  public void changeImage(BufferedImage image) {
    //panel.remove(0);
    //labelImageLabel = new JLabel(new ImageIcon(image));
    //panel.add(labelImageLabel);
    labelImageLabel.setIcon(new ImageIcon(image));
    labelImageLabel.repaint();
    repaint();
    setVisible(true);
    //add(panel);
    //setVisible(true);
  }

}
