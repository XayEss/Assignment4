package view.impl;

import java.awt.Dimension;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.implementation.UniversalImageLoader;
import controller.interfaces.ImageInput;
import model.implementation.ImageConverter;
import model.interfaces.Image;

/**
 * This class visualizes the Histogram of an Image.
 */
public class HistogramViewer {


  /**
   * This functions runs the histogram visualization.
   */
  public static void main(String[] args) {

    Image image = null;

    ImageInput imageLoader = new UniversalImageLoader();

    try {
      image = ImageConverter.convertFromBytes(imageLoader.readFile(
              "resources/images/new_examples/raiden-min.png"));
    } catch (IOException e) {
      System.out.println("Couldn't read the image");
    }


    Image finalImage = image;
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Image RGB Histogram");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);

      ImageViewer histogramDisplay = new ImageViewer();
      histogramDisplay.setPreferredSize(new Dimension(100,100));
      frame.add(histogramDisplay);
      frame.setVisible(true);

      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      histogramDisplay.setImage(finalImage);
    });
  }
}

