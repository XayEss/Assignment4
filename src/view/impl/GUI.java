package view.impl;

import controller.implementation.UniversalImageLoader;
import controller.implementation.UniversalImageSaver;
import controller.implementation.commands.FlipImage;
import controller.interfaces.CommandHelper;
import controller.interfaces.TransformationController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import javax.swing.*;

import model.implementation.ImageConverter;
import model.implementation.ImageHandlerImpl;
import model.implementation.ImageToBufferedImageService;
import model.interfaces.Image;
import model.interfaces.ImageHandler;
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
  private String selectedAction;
  private JTextField pane;


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
    //fileField = new JTextField("File path");
    fileField.setPreferredSize(new Dimension(getWidth() / 2, 55));
//    GridBagConstraints gbcapply = new GridBagConstraints();
//    gbcapply.gridx = 2;
//    gbcapply.gridy = 2;
    panel.add(fileField);

    JPanel interactionPanel = new JPanel();
    interactionPanel.setLayout(new FlowLayout());

    GridBagConstraints gbcOperations = new GridBagConstraints();
    gbcOperations.gridy = 1;
    gbcOperations.gridx = 0;

    JComboBox<String> box = new JComboBox<>(new String[]{"dither", "sepia", "sharpen", "blur",
        "vertical-flip", "horizontal-flip", "brighten", "greyscale", "extract channel"});


    box.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          selectedAction = (String) e.getItem();
        }
      }
    });


    interactionPanel.add(box);

    GridBagConstraints gbcParams = new GridBagConstraints();
    gbcParams.gridx = 2;
    gbcParams.gridy = 1;
    pane = new JTextField("parameters");
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
    } catch (IOException e) {
      // Do Nothing
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


  private String[] getParameters() {
    String paramsInput = pane.getText(); // Get the text from the pane (JTextField)
    String[] params = paramsInput.split(" ");

    return params;
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
        fileField.setText(fd.getDirectory() + fd.getFile());
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

  private void initMainListeners() {
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
        if (selectedAction != null) {
          // Get the parameters from the pane
          String[] params = getParameters();

          // TODO: Fix imagename and savename here and get parameters from GUI
          String name = imageName;
          String saveName = imageName;
          switch (selectedAction) {
            // TODO: Fix switchcase
            case "brighten":
              // Get the amount parameter as an integer
              int amount = Integer.parseInt(params[0]);
              controller.alterImageBrightness(name, saveName, amount);
              System.out.println("Brighten called with amount: " + amount);
              break;
            case "vertical-flip":
              controller.createFlippedImage(name, saveName, false);
              break;
            case "horizontal-flip":
              controller.createFlippedImage(name, saveName, true);
              break;
            case "greyscale":
              controller.createGreyScaleImage(name, saveName);
              break;
            case "sepia":
              controller.createSepiaImage(name, saveName);
              break;
            case "dither":
              controller.ditherImage(name, saveName);
              break;
            case "blur":
              controller.blurImage(name, saveName);
              break;
            case "sharpen":
              controller.sharpenImage(name, saveName);
              break;
            default:
              // Do nothing if an empty action is selected
              break;
          }
        } else {
          JOptionPane.showMessageDialog(GUI.this, "Please select an operation.");
        }
      }
    });


    image.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        if (!e.isShiftDown()) {
          if (e.getWheelRotation() < 0) {
            image.scrollYNegative();
          } else if (e.getWheelRotation() > 0) {
            image.scrollYPositive();
          }
        } else {
          if (e.getWheelRotation() < 0) {
            image.scrollXNegative();
          } else if (e.getWheelRotation() > 0) {
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

    // Add directory navigation button
    JButton directoryButton = new JButton(new ImageIcon("resources/folder.png"));
    directoryButton.addActionListener(e -> {
      FileDialog fd = new FileDialog(this, "Choose a folder", FileDialog.SAVE);
      fd.setVisible(true);
      String filename = fd.getFile();
      if (filename == null) {
        savePathField.setText("You cancelled the choice");
      } else {
        savePathField.setText(fd.getDirectory() + fd.getFile());
      }
    });
    gbc.gridx = 1;
    savePanel.add(directoryButton, gbc);

    // Radio buttons for file formats
    JPanel buttonPanel = new JPanel();
    gbc.gridy = 1;
    //gbc.gridx = 0;
    ButtonGroup formatGroup = new ButtonGroup();
    JRadioButton jpgRadio = new JRadioButton("JPG", true);
    formatGroup.add(jpgRadio);
    buttonPanel.add(jpgRadio, gbc);

    JRadioButton pngRadio = new JRadioButton("PNG");
    formatGroup.add(pngRadio);
    buttonPanel.add(pngRadio, gbc);

    JRadioButton ppmRadio = new JRadioButton("PPM");
    formatGroup.add(ppmRadio);
    buttonPanel.add(ppmRadio, gbc);
    savePanel.add(buttonPanel, gbc);

    // Save button
    gbc.gridy = 2;
    JButton saveButton = new JButton("Save");

    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String filePath = savePathField.getText();
        String format = "jpg";
        if (jpgRadio.isSelected()) {
          format = "jpg";
        } else if (pngRadio.isSelected()) {
          format = "png";
        } else if (ppmRadio.isSelected()) {
          format = "ppm";
        }

        filePath += "." + (String) format;
        System.out.println("Saving image as: " + filePath);
        controller.saveImage(filePath, imageName);
        JOptionPane.showMessageDialog(saveFrame, "Image saved successfully!");
        saveFrame.dispose();

      }

    });

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
