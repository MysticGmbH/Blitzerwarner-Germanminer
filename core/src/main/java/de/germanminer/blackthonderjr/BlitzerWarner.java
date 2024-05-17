package de.germanminer.blackthonderjr;

import de.germanminer.blackthonderjr.listener.BlitzerKeys;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import de.germanminer.blackthonderjr.listener.BlitzerListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AddonMain
public class BlitzerWarner extends LabyAddon<BlitzerConfiguration> {
  public static String prefix;
  public static List<String> Koords = new ArrayList<>();
  public static HashMap<String, Boolean> blitzerconf = new HashMap<>();

  @Override
  protected void enable() {
    prefix = configuration().prefix().get().toString().replace("&", "§") + " ";
    loadBlitzer();
    this.registerSettingCategory();

    this.registerListener(new BlitzerListener(this));
    this.registerListener(new BlitzerKeys(this));
    BlitzerListener.isInRange = false;
  }

  @Override
  protected Class<BlitzerConfiguration> configurationClass() {
    return BlitzerConfiguration.class;
  }

  public void loadBlitzer(){
    try{
      URL blitzer = new URL("http://awantalife.de/Blitzer.txt");

      URLConnection conBlitzer = blitzer.openConnection();
      BufferedReader br = new BufferedReader(new InputStreamReader( conBlitzer.getInputStream()));
      String strLine = "";
      while ( ( strLine = br.readLine() ) != null)
        Koords.add(strLine);
      br.close();

    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
