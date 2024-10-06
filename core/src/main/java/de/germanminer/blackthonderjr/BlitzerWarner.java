package de.germanminer.blackthonderjr;

import de.germanminer.blackthonderjr.api.BlitzerAPI;
import de.germanminer.blackthonderjr.cfg.BlitzerMainCfg;
import de.germanminer.blackthonderjr.commands.BlitzerreportCMD;
import de.germanminer.blackthonderjr.commands.ReloadCMD;
import de.germanminer.blackthonderjr.commands.TestCMD;
import de.germanminer.blackthonderjr.gui.NavigationGUI;
import de.germanminer.blackthonderjr.gui.element.NavigationNavigationElement;
import de.germanminer.blackthonderjr.listener.BlitzerKeys;
import de.germanminer.blackthonderjr.listener.RSListener;
import de.germanminer.blackthonderjr.widgets.NaviOrtWidget;
import de.germanminer.blackthonderjr.widgets.RSTimerWidget;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import de.germanminer.blackthonderjr.listener.BlitzerListener;
import java.util.ArrayList;
import java.util.List;

@AddonMain
public class BlitzerWarner extends LabyAddon<BlitzerMainCfg> {
  public static List<Blitzer> Koords = new ArrayList<>();
  public static List<Navigation> Navi = new ArrayList<>();
  public static List<String> NaviNames = new ArrayList<>();
  public static List<String> NaviNamesBiz = new ArrayList<>();
  public static List<String> NaviNamesWerkstätte = new ArrayList<>();
  public static List<String> NaviNamesStaatsfirmen = new ArrayList<>();
  public static List<String> NaviNamesSpielershops = new ArrayList<>();
  public static List<String> NaviNamesOrteVonInteresse = new ArrayList<>();
  public static List<String> NaviNamesStadtgebietHilfe = new ArrayList<>();
  public static Navigation LastNaviLoc;
  public static boolean isOnline;
  private NavigationGUI navigationGUI;

  @Override
  protected void enable() {
    isOnline = false;
    this.registerSettingCategory();
    BlitzerAPI.loadNavi();
    NaviOrtWidget naviOrtWidget = new NaviOrtWidget(this);
    RSTimerWidget rsTimerWidget = new RSTimerWidget();
    labyAPI().hudWidgetRegistry().register(naviOrtWidget);
    labyAPI().hudWidgetRegistry().register(rsTimerWidget);
    this.registerListener(new BlitzerListener(this, naviOrtWidget));
    this.registerListener(new RSListener());
    this.registerListener(new BlitzerKeys(this));
    this.registerCommand(new BlitzerreportCMD(this));
    this.registerCommand(new TestCMD(this, naviOrtWidget));
    this.registerCommand(new ReloadCMD(this));
    this.navigationGUI = new NavigationGUI(this);
    labyAPI().navigationService().register("blitzerwarner_navi_main", new NavigationNavigationElement(this));

  }

  @Override
  protected Class<BlitzerMainCfg> configurationClass() {
    return BlitzerMainCfg.class;
  }
  public NavigationGUI navigationGUI() {
    return navigationGUI;
  }

}