package controller.interfaces;

public interface Input {

  void startCommandReading();
  void parseInput(String line);
  void runScript(String path);
}
