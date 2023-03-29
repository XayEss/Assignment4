package controller.implementation.commands;

import controller.interfaces.CommandHelper;
import controller.interfaces.Controller;
import controller.interfaces.Input;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class TextCommandInput implements Input {
  InputStream stream;
  Map<String, Function<Scanner, CommandHelper>> commands;

  public TextCommandInput(InputStream stream) {
    this.stream = stream;
    commands = new HashMap<>();
  }

  @Override
  public void startCommandReading() {

  }

  @Override
  public void parseInput(String line) {

  }

  @Override
  public void setController(Controller controller) {

  }

  private void populateCommands(){
    commands.put("brighten", s -> new BrightenImage(s.nextInt()));
  }
}
