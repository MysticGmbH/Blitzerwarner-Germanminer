package de.germanminer.blackthonderjr.commands;

import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.Navigation;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import de.germanminer.blackthonderjr.widgets.NaviOrtWidget;
import net.labymod.api.client.chat.command.Command;
import java.util.HashMap;
import java.util.UUID;

public class TestCMD extends Command {
  public final BlitzerWarner addon;
  public NaviOrtWidget naviOrtWidget;

  public TestCMD(BlitzerWarner addon, NaviOrtWidget naviOrtWidget) {
    super("TestNavi");
    this.addon = addon;
    this.naviOrtWidget = naviOrtWidget;
  }
  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (prefix.equalsIgnoreCase("TestNavi")) {
      Navigation navigation = BlitzerWarner.LastNaviLoc;
      addon.displayMessage(BlitzerAPI.buildNaviMessage(0, navigation.getGebiet(), navigation.getX() + " " + navigation.getY() + " " + navigation.getZ(), navigation.getName().replace("_", " "), addon, navigation));
      naviOrtWidget.NAVI_TEXT.updateAndFlush(navigation.getName());
    }
    return true;
  }

}
