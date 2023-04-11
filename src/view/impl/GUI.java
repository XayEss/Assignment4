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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import model.implementation.ImageConverter;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import view.intefraces.Output;

public class GUI extends JFrame implements Output {
  private TransformationController controller;
  private JPanel panel;
  private JTextField textField;
  private JButton file;
  private JMenuItem itemLoad;
  private JMenuItem itemSave;
  private JButton load;

  private ScrollableImagePanel image;

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
        saveFrame();
      }
    });
    image.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        if(!e.isShiftDown()) {
          if (e.getWheelRotation() < 0) {
            image.scrollYNegative();
          } else {
            image.scrollYPositive();
          }
        }else {
          if (e.getWheelRotation() < 0) {
            image.scrollXNegative();
          } else {
            image.scrollXPositive();
          }
        }
        image.repaint();
      }
    });
  }


  private void saveFrame() {
    JFrame saveFrame = new JFrame("Save Image");
    saveFrame.setSize(600, 300);
    saveFrame.setLocationRelativeTo(null);
    saveFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    JPanel savePanel = new JPanel();
    savePanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // File path text field
    gbc.gridy = 0;
    JTextField savePathField = new JTextField("File path");
    savePathField.setPreferredSize(new Dimension(400, 55));
    savePathField.setBorder(BorderFactory.createLineBorder(Color.black));
    savePanel.add(savePathField, gbc);

    // Radio buttons for file formats
    gbc.gridy = 1;
    ButtonGroup formatGroup = new ButtonGroup();
    JRadioButton jpgRadio = new JRadioButton("JPG");
    formatGroup.add(jpgRadio);
    savePanel.add(jpgRadio, gbc);

    JRadioButton pngRadio = new JRadioButton("PNG");
    formatGroup.add(pngRadio);
    savePanel.add(pngRadio, gbc);

    JRadioButton ppmRadio = new JRadioButton("PPM");
    formatGroup.add(ppmRadio);
    savePanel.add(ppmRadio, gbc);

    // Save button
    gbc.gridy = 2;
    JButton saveButton = new JButton("Save");
    savePanel.add(saveButton, gbc);

    saveFrame.add(savePanel);
    saveFrame.setVisible(true);

    repaint();
  }

  @Override
  public void show(Image image) {

  }

  @Override
  public void print(String string) {

  }
}
