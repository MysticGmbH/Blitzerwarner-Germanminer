package de.germanminer.blackthonderjr;

import de.germanminer.blackthonderjr.cfg.BlitzerMainCfg;
import de.germanminer.blackthonderjr.commands.BlitzerreportCMD;
import de.germanminer.blackthonderjr.listener.BlitzerKeys;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import de.germanminer.blackthonderjr.listener.BlitzerListener;
import java.util.ArrayList;
import java.util.List;

@AddonMain
public class BlitzerWarner extends LabyAddon<BlitzerMainCfg> {
  public static List<Blitzer> Koords = new ArrayList<>();
  public static boolean isOnline;

  @Override
  protected void enable() {
    isOnline = false;
    this.registerSettingCategory();

    this.registerListener(new BlitzerListener(this));
    this.registerListener(new BlitzerKeys(this));
    this.registerCommand(new BlitzerreportCMD(this));
  }

  @Override
  protected Class<BlitzerMainCfg> configurationClass() {
    return BlitzerMainCfg.class;
  }
}