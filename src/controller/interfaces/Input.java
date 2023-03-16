package controller.interfaces;

/**
 * The Input interface represents a contract for handling user input in a software system.
 */
public interface Input {

  void startCommandReading();

  void parseInput(String line);

  void setController(Controller controller);
}
