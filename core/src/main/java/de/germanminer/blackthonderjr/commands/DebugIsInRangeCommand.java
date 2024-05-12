package de.germanminer.blackthonderjr.commands;

import de.germanminer.blackthonderjr.listener.BlitzerListener;
import net.labymod.api.client.chat.command.Command;

public class DebugIsInRangeCommand extends Command {

  public DebugIsInRangeCommand() {
    super("debugIsInRange");

  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
      this.displayMessage(String.valueOf(BlitzerListener.isInRange));
      return true;
  }
}
