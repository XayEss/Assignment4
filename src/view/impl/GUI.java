package view.impl;

import controller.interfaces.TransformationController;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.interfaces.Image;
import view.intefraces.Output;

public class GUI extends JFrame implements Output {
  private TransformationController controller;
  JPanel panel;
  private JTextField textField;
  private JButton file;

  private JButton load;

  public GUI() {
    setName("Image manipulator");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    loadFrame();
  }

  private void loadFrame() {
    setSize(700, 700);
    panel = new JPanel();
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
    pack();
    setVisible(true);
  }

  private JButton save;
  private void mainFrame() {
    panel.removeAll();
    JMenuBar menuBar = new JMenuBar();
    load = new JButton("Load image");
    save = new JButton("Save");
    menuBar.add(load);
    menuBar.add(save);
    JMenuItem item1 = new JMenuItem("load");
    JMenuItem item2 = new JMenuItem("save");

    setJMenuBar(menuBar);

    setSize(700, 700);
    panel = new JPanel();
    GridBagLayout cl = new GridBagLayout();
    panel.setLayout(cl);
    textField = new JTextField("File path");
    textField.setPreferredSize(new Dimension(400, 55));

    GridBagConstraints gbcapply = new GridBagConstraints();
    gbcapply.gridx = 2;
    JButton apply = new JButton("Apply");
    panel.add(textField);
    panel.add(apply, gbcapply);


    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 1;
    panel.add(file, gbc);

    JPanel image = new JPanel();
    image.add(new JLabel(new ImageIcon("resources/sephia.jpg")));
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.gridy = 2;
    panel.add(image, gbc2);

    JTextField log = new JTextField("log");
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridy = 3;
    panel.add(log, gbc3);
    load = new JButton("Load");
    panel.add(load);
    add(panel);
    //FileChooser chooser = new JFileChooser();
    //add(chooser);
    repaint();
    setVisible(true);
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

  @Override
  public void show(Image image) {

  }

  @Override
  public void print(String string) {

  }
}
