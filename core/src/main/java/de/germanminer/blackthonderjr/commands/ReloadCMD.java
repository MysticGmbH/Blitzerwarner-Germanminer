package de.germanminer.blackthonderjr.commands;

import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.format.NamedTextColor;

public class ReloadCMD extends Command {
  private BlitzerWarner addon;

  public ReloadCMD(BlitzerWarner addon) {
    super("gmreload");
    this.addon = addon;
  }

  @Override
  public boolean execute(String s, String[] strings) {
    BlitzerWarner.Navi.clear();
    BlitzerWarner.Koords.clear();
    BlitzerWarner.LastNaviLoc = null;
    BlitzerWarner.NaviNames.clear();
    BlitzerAPI.loadNavi();
    BlitzerAPI.loadBlitzer();
    addon.displayMessage(BlitzerAPI.prefixMessageBilder(addon, "Reloaded!", NamedTextColor.GREEN));
    return true;
  }
}
