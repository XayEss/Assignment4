package view.impl;

import controller.implementation.UniversalImageLoader;
import controller.interfaces.TransformationController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import model.implementation.ImageConverter;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import view.intefraces.Output;

public class GUI extends JFrame implements Output {
  private TransformationController controller;
  JPanel panel;
  private JTextField textField;
  private JButton file;
  JMenuItem itemLoad;
  JMenuItem itemSave;
  private JButton load;

  ScrollableImagePanel image;

  public GUI() {
    setName("Image manipulator");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    panel = new JPanel();
    setLocation(500, 500);
    loadFrame();
  }

  private void loadFrame() {
    setSize(700, 700);
    //panel = new JPanel();
    panel.removeAll();
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    textField = new JTextField("File path");
    textField.setPreferredSize(new Dimension(400, 55));
    panel.add(textField);
    file = new JButton(new ImageIcon("resources/folder.png"));
    panel.add(file);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 1;
    load = new JButton("Load");
    panel.add(load, gbc);
    add(panel);
    //FileChooser chooser = new JFileChooser();
    //add(chooser);
    initLoadListeners();
    //pack();
    setVisible(true);
    repaint();
  }

  private JButton save;
  private void mainFrame() {
    panel.removeAll();
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    itemLoad = new JMenuItem("Load");
    itemSave = new JMenuItem("Save");
    menu.add(itemLoad);
    menu.add(itemSave);
    menuBar.add(menu);

    setJMenuBar(menuBar);

    setSize(700, 700);
    //panel = new JPanel();
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    textField = new JTextField("File path");
    textField.setPreferredSize(new Dimension(400, 55));

    GridBagConstraints gbcapply = new GridBagConstraints();
    gbcapply.gridx = 2;
    gbcapply.gridy = 2;
    JButton apply = new JButton("Apply");
    panel.add(textField);
    panel.add(apply, gbcapply);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 1;
    //panel.add(file, gbc);

    try {
      image = new ScrollableImagePanel(ImageToBufferedImageService.toBuffered(
          new UniversalImageLoader().readFile("resources/sephia.jpg")));
    } catch (IOException e){

    }
    //image.add(new JLabel(/*new ImageIcon("resources/sephia.jpg")*/));
    GridBagConstraints gbc2 = new GridBagConstraints();
    image.setPreferredSize(new Dimension(500, 500));
    gbc2.gridy = 1;
    gbc2.gridx = 0;
    panel.add(image, gbc2);

    JTextArea log = new JTextArea("log");
    log.setPreferredSize(new Dimension(300, 70));
    log.setBorder(BorderFactory.createLineBorder(Color.black));
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridy = 2;
    gbc3.gridx = 0;
    panel.add(log, gbc3);

    GridBagConstraints gbcOperations = new GridBagConstraints();
    gbcOperations.gridy = 0;
    gbcOperations.gridx = 2;
    JComboBox<String> box  = new JComboBox<>(new String[]{"dither", "sepia", "vertical-flip"});
    panel.add(box, gbcOperations);

    GridBagConstraints gbcParams = new GridBagConstraints();
    gbcParams.gridx = 2;
    gbcParams.gridy = 1;
    JTextField pane = new JTextField("parameters");
    pane.setSize(100, 100);
    panel.add(pane, gbcParams);

    add(panel);
    initMainListeners();
    setVisible(true);
    repaint();
  }

  private void initLoadListeners() {
    file.addActionListener(e -> {
      FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
      fd.setDirectory("C:\\");
      fd.setVisible(true);
      String filename = fd.getFile();
      if (filename == null) {
        textField.setText("You cancelled the choice");
      } else {
        textField.setText(fd.getDirectory()+fd.getFile());
      }
    });
    load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //String path = textField.getText();
        //controller.loadImage(path, path.substring(path.lastIndexOf("/")+1));
        mainFrame();
      }
    });
  }

  private void initMainListeners(){
    itemLoad.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        loadFrame();
      }
    });

    itemSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    image.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        if(!e.isShiftDown()) {
          if (e.getWheelRotation() < 0) {
            image.scrollYNegative();
            System.out.println("lol");
          } else {
            System.out.println("dlol");
            image.scrollYPositive();
          }
        }else {
          if (e.getWheelRotation() < 0) {
            image.scrollXNegative();
            System.out.println("lol");
          } else {
            System.out.println("dlol");
            image.scrollXPositive();
          }
        }
          image.repaint();
        }
    });
  }

  @Override
  public void show(Image image) {

  }

  @Override
  public void print(String string) {

  }
}
