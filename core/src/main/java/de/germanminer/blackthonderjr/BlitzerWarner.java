package de.germanminer.blackthonderjr;

import de.germanminer.blackthonderjr.listener.BlitzerKeys;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import de.germanminer.blackthonderjr.commands.DebugIsInRangeCommand;
import de.germanminer.blackthonderjr.listener.BlitzerListener;
import java.util.ArrayList;
import java.util.List;

@AddonMain
public class BlitzerWarner extends LabyAddon<BlitzerConfiguration> {
  public static String prefix;
  public static List<String> Koords = new ArrayList<>();

  @Override
  protected void enable() {
    prefix = configuration().prefix().get().toString().replace("&", "§") + " ";
    this.registerSettingCategory();

    this.registerListener(new BlitzerListener(this));
    this.registerListener(new BlitzerKeys(this));
    this.registerCommand(new DebugIsInRangeCommand());

    this.logger().info(prefix + "Enabled the Addon");
    BlitzerListener.isInRange = false;

    Koords.add("-1927 73 -1383 Hensburg 50");
    Koords.add("-2137 65 -1437 Hensburg-Westend(Tunnel) 50");
    Koords.add("-607 67 -2268 Versicherungsviertel 70");
    Koords.add("-2437 66 -2197 Westend(Flughafen) 30");
    Koords.add("-1597 67 -1670 JVA_Spegelsbach(Autovermietung) 50");
    Koords.add("-787 67 -1388 Künstler_Viertel(Kreuzung) 50");
    Koords.add("-788 67 -2209 Offenbach_Ost(Richtung_Hbf) 50");
    Koords.add("-2026 71 -831 Hohenbrueck 50");
    Koords.add("-1866 73 -1039 Niebrück 80");
    Koords.add("-2917 59 -1017 Westend-Niebrück(im_Tunnel) 100");
    Koords.add("-2917 64 -717 Niebrück(Tunnel_Ausfahrt) 50");
    Koords.add("-2421 69 1037 Niebrück(Haudi_Autohaus) 70");
    Koords.add("-2505 70 258 Goldschweig(Einkaufsmeile) 50");
    Koords.add("-1235 70 -889 Harrenheim-GwG-Ost(Bahnübergang) 50");
    Koords.add("-1533 80 -82 Kraftwerk(Richtung_GwG_Ost) 100");
    Koords.add("152 66 -2121 Universität 30");
    Koords.add("-273 67 -1389 Zentrum(Passage_Hochhaus) 50");
    Koords.add("-1476 67 -1992 Offenbach_Süd 50");
    Koords.add("-2723 70 364 Verwahrstelle 50");

  }

  @Override
  protected Class<BlitzerConfiguration> configurationClass() {
    return BlitzerConfiguration.class;
  }
}
