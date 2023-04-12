package view.impl;

import controller.implementation.UniversalImageLoader;
import controller.interfaces.TransformationController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import javax.swing.*;
import model.implementation.ImageConverter;
import model.implementation.ImageToBufferedImageService;
import view.intefraces.Output;

public class GUI extends JFrame implements Output {
  private TransformationController controller;
  private JPanel panel;
  private JTextField fileField;
  private JButton file;
  private JMenuItem itemLoad;
  private JMenuItem itemSave;
  private JButton load;
  private JButton apply;
  private String imageName;

  private ScrollableImagePanel image;

  public GUI() {
    imageName = "loadedI";
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
    fileField = new JTextField("File path");
    fileField.setPreferredSize(new Dimension(400, 55));
    panel.add(fileField);
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

    setSize(900, 750);
    //panel = new JPanel();
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    fileField = new JTextField("File path");
    fileField.setPreferredSize(new Dimension(getWidth()/2, 55));
//    GridBagConstraints gbcapply = new GridBagConstraints();
//    gbcapply.gridx = 2;
//    gbcapply.gridy = 2;
    panel.add(fileField);


    JPanel interactionPanel = new JPanel();
    interactionPanel.setLayout(new FlowLayout());

    GridBagConstraints gbcOperations = new GridBagConstraints();
    gbcOperations.gridy = 1;
    gbcOperations.gridx = 0;

    JComboBox<String> box  = new JComboBox<>(new String[]{"dither", "sepia", "sharpen", "blur",
        "vertical-flip", "horizontal-flip", "brighten", "greyscale", "extract channel"});
    interactionPanel.add(box);

    GridBagConstraints gbcParams = new GridBagConstraints();
    gbcParams.gridx = 2;
    gbcParams.gridy = 1;
    JTextField pane = new JTextField("parameters");
    pane.setSize(100, 100);
    interactionPanel.add(pane);

    apply = new JButton("Apply");
    interactionPanel.add(apply);
    panel.add(interactionPanel, gbcOperations);


    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 1;
    ImageViewer histogram = null;
    try {
    histogram = new ImageViewer(ImageConverter.convertFromBytes(
        new UniversalImageLoader().readFile("resources/images/new_examples/raiden-min.png")));
    histogram.setPreferredSize(new Dimension(800, 800));
      //panel.add(file, gbc);


      image = new ScrollableImagePanel();
    } catch (IOException e){

    }
    //image.add(new JLabel(/*new ImageIcon("resources/sephia.jpg")*/));
    GridBagConstraints gbc2 = new GridBagConstraints();
    image.setPreferredSize(new Dimension(700, 500));
    gbc2.gridy = 2;
    gbc2.gridx = 0;
    panel.add(image, gbc2);
    gbc2.gridy = 2;
    gbc2.gridx = 1;
    //panel.add(histogram, gbc2);

    JTextArea log = new JTextArea("log");
    log.setEditable(false);
    log.setPreferredSize(new Dimension(300, 70));
    log.setBorder(BorderFactory.createLineBorder(Color.black));
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridy = 3;
    gbc3.gridx = 0;
    panel.add(log, gbc3);

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
        fileField.setText("You cancelled the choice");
      } else {
        fileField.setText(fd.getDirectory()+fd.getFile());
      }
    });
    load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String path = fileField.getText();
        mainFrame();
        System.out.println(path);
        controller.loadImage(path, imageName);
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

    apply.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          image.setImage(ImageToBufferedImageService.toBuffered(
              new UniversalImageLoader().readFile("resources/images/new_examples/raiden_sharpened.png")));
        }catch (IOException m){

        }
      }
    });

    image.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        if(!e.isShiftDown()) {
          if (e.getWheelRotation() < 0) {
            image.scrollYNegative();
          } else if (e.getWheelRotation() > 0){
            image.scrollYPositive();
          }
        }else {
          if (e.getWheelRotation() < 0) {
            image.scrollXNegative();
          } else if (e.getWheelRotation() > 0){
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
    JPanel buttonPanel = new JPanel();
    gbc.gridy = 1;
    ButtonGroup formatGroup = new ButtonGroup();
    JRadioButton jpgRadio = new JRadioButton("JPG");
    formatGroup.add(jpgRadio);
    buttonPanel.add(jpgRadio, gbc);

    JRadioButton pngRadio = new JRadioButton("PNG");
    formatGroup.add(pngRadio);
    buttonPanel.add(pngRadio, gbc);

    JRadioButton ppmRadio = new JRadioButton("PPM");
    formatGroup.add(ppmRadio);
    buttonPanel.add(ppmRadio, gbc);
    savePanel.add(buttonPanel,gbc);

    // Save button
    gbc.gridy = 2;
    JButton saveButton = new JButton("Save");
    savePanel.add(saveButton, gbc);

    saveFrame.add(savePanel);
    saveFrame.setVisible(true);

    repaint();
  }

  @Override
  public void show(InputStream image) throws IOException {
    this.image.setImage(ImageToBufferedImageService.toBuffered(image));
  }

  @Override
  public void print(String string) {
    JOptionPane.showMessageDialog(this, string);
  }

  @Override
  public void setController(TransformationController controller) {
    this.controller = controller;
  }
}
