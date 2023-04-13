package controller.interfaces;

/**
 * The Input interface represents a contract for handling user input in a software system.
 */
public interface Input {

  /**
   * Starts the reading of user input.
   */
  void startCommandReading();

  /**
   * Parses the provided input line.
   *
   * @param line the input line to be parsed
   */
  void parseInput(String line);

  /**
   * Sets the {@link Controller} object to handle the parsed input.
   *
   * @param controller the {@link Controller} object to handle the parsed input
   */
  void setController(Controller controller);
}
