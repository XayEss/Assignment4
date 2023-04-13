package view.intefraces;

import java.io.OutputStream;

/**
 * Interface for gui specific operations
 */
public interface GUIOutput extends Output {

  /**
   * Initializes the GUI and makes it visible.
   */
  void startGUI();

  /**
   * Switches to the main view.
   */
  void goToMainView();
}
