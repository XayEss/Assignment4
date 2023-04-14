package view.impl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.interfaces.TransformationController;
import model.implementation.HistogramArtist;
import model.implementation.ImageToBufferedImageService;
import view.intefraces.GUIOutput;
import view.intefraces.Output;


/**
 * This class contains the entire GUI of the program.
 */
public class GUI extends JFrame implements GUIOutput {
  private TransformationController controller;
  private final JPanel panel;
  private JTextField fileField;
  private JButton file;
  private JMenuItem itemLoad;
  private JMenuItem itemSave;
  private JButton load;
  private JButton apply;
  private final String imageName;
  private String selectedAction;
  private JTextField pane;
  private JTextArea log;
  private ScrollableImagePanel histogram2;

  private ImageViewer histogram;


  private ScrollableImagePanel image;
  private JButton save;

  /**
   * Constructor for GUI.
   */
  public GUI() {
    imageName = "loadedI";
    setName("Image manipulator");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    panel = new JPanel();
    setLocation(500, 500);
    loadFrame();
  }

  private void loadFrame() {
    setLocation(500, 500);
    setSize(700, 700);
    //panel = new JPanel();
    panel.removeAll();
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    fileField = new JTextField("File path");
    fileField.setPreferredSize(new Dimension(400, 55));
    panel.add(fileField);
    file = new JButton("explorer");
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
    setLocation(0, 0);
    setSize(1450, 1000);
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    fileField.setPreferredSize(new Dimension(getWidth() / 2, 55));
    panel.add(fileField);

    JPanel interactionPanel = new JPanel();
    interactionPanel.setLayout(new FlowLayout());

    GridBagConstraints gbcOperations = new GridBagConstraints();
    gbcOperations.gridy = 1;
    gbcOperations.gridx = 0;

    JComboBox<String> box = new JComboBox<>(new String[]{"------", "dither", "sepia", "sharpen",
            "blur", "vertical-flip", "horizontal-flip", "brighten", "greyscale",
            "extract channel"});

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

    image = new ScrollableImagePanel();
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new FlowLayout());
    //image.add(new JLabel(/*new ImageIcon("resources/sephia.jpg")*/));
    GridBagConstraints gbc2 = new GridBagConstraints();
    image.setPreferredSize(new Dimension(700, 500));
    gbc2.gridy = 2;
    gbc2.gridx = 0;
    imagePanel.add(image);
    //panel.add(image, gbc2);

    histogram = new ImageViewer();
    histogram.setPreferredSize(new Dimension(800, 500));
    histogram2 = new ScrollableImagePanel();
    histogram2.setPreferredSize(new Dimension(700, 500));
    imagePanel.add(histogram2);

    panel.add(imagePanel, gbc2);


    gbc2.gridy = 2;
    gbc2.gridx = 2;
    GridBagConstraints histConstraints = new GridBagConstraints();
    histConstraints.gridx = 1;
    histConstraints.gridy = 2;


    //panel.add(histogram, gbc2);

    log = new JTextArea("Logs:");
    log.setEditable(false);
    log.setLineWrap(true);
    log.setWrapStyleWord(true);
    JScrollPane logScrollPane = new JScrollPane(log);
    logScrollPane.setPreferredSize(new Dimension(300, 70));
    logScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridy = 3;
    gbc3.gridx = 0;
    panel.add(logScrollPane, gbc3); // Add the logScrollPane instead of the log JTextArea

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
      //fd.setFile("*.jpg;*.jpeg;*.png;*.ppm");
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
        boolean isValidImage = false;

        try {
          // Check if the file path points to a valid image
          BufferedImage img = ImageIO.read(new File(path));
          if (img != null || path.substring(path.length() - 3).equals("ppm")) {
            isValidImage = true;
          } else {
            // Not a valid image, show an error message
            JOptionPane.showMessageDialog(GUI.this, "Invalid image file: "
                    + path);
          }
        } catch (IOException ex) {
          // Error reading file, show an error message
          JOptionPane.showMessageDialog(GUI.this, "Error reading file: "
                  + path);
        }

        if (isValidImage) {
          mainFrame();
          controller.loadImage(path, imageName);
        }

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

          String name = imageName;
          String saveName = imageName;
          String logText = ""; // Add this line to store the operation description
          switch (selectedAction) {
            case "brighten":
              int amount = Integer.parseInt(params[0]);
              controller.alterImageBrightness(name, saveName, amount);
              logText = "Brighten called with amount: " + amount;
              break;
            case "vertical-flip":
              controller.createFlippedImage(name, saveName, false);
              logText = "Vertical flip called";
              break;
            case "horizontal-flip":
              controller.createFlippedImage(name, saveName, true);
              logText = "Horizontal flip called";
              break;
            case "greyscale":
              controller.createGreyScaleImage(name, saveName);
              logText = "Greyscale called";
              break;
            case "sepia":
              controller.createSepiaImage(name, saveName);
              logText = "Sepia called";
              break;
            case "dither":
              controller.ditherImage(name, saveName);
              logText = "Dither called";
              break;
            case "blur":
              controller.blurImage(name, saveName);
              logText = "Blur called";
              break;
            case "sharpen":
              controller.sharpenImage(name, saveName);
              logText = "Sharpen called";
              break;
            case "extract channel":
              int channel = Integer.parseInt(params[0]);
              controller.separateImageChannel(name, saveName, channel);
              logText = "Extract channel called with channel: " + channel;
              break;
            default:
              // Do nothing if an empty action is selected
              break;
          }
          log.append("\n" + logText); // Append the operation description to the log
        } else {
          JOptionPane.showMessageDialog(GUI.this,
                  "Please select an operation.");
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

    histogram2.addMouseWheelListener(e -> {
      if (!e.isShiftDown()) {
        if (e.getWheelRotation() < 0) {
          histogram2.scrollYNegative();
        } else if (e.getWheelRotation() > 0) {
          histogram2.scrollYPositive();
        }
      } else {
        if (e.getWheelRotation() < 0) {
          histogram2.scrollXNegative();
        } else if (e.getWheelRotation() > 0) {
          histogram2.scrollXPositive();
        }
      }
      histogram2.repaint();
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
    JButton directoryButton = new JButton("explorer");
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
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
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
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.NONE;
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

        filePath += "." + format;
        System.out.println("Saving image as: " + filePath);
        controller.saveImage(filePath, imageName);
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

  @Override
  public void showHistogram(int[][] histogram) {
    BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    HistogramArtist.drawHistogram(img.getGraphics(), histogram, img.getWidth(), img.getHeight());
    histogram2.setImage(img);
    //this.histogram.setImage(histogram);
  }

  @Override
  public void startGUI() {

  }

  @Override
  public void goToMainView() {
    //mainFrame();
  }
}
