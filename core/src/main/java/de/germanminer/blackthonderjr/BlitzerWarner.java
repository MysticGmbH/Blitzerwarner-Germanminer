package de.germanminer.blackthonderjr;

import de.germanminer.blackthonderjr.listener.BlitzerKeys;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.models.addon.annotation.AddonMain;
import de.germanminer.blackthonderjr.listener.BlitzerListener;
import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.io.web.request.Request.Method;
import java.util.ArrayList;
import java.util.List;

@AddonMain
public class BlitzerWarner extends LabyAddon<BlitzerConfiguration> {
  public static Component prefix;
  public static List<String> Koords = new ArrayList<>();
  public static boolean isOnline;

  @Override
  protected void enable() {
    isOnline = true;
    prefix = Component.text(this.configuration().prefix().get().toString(), TextColor.color(this.configuration().prefixColor().get())).decorate(
        TextDecoration.BOLD);
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
    Request.ofString()
        .url("http://awantalife.de/Blitzer.txt")
        .method(Method.GET)
        .async()
        .execute((stringResponse -> {
          String responseContent = stringResponse.get();
          if (responseContent != null) {
            String[] lines = responseContent.split("\n");
            for (String line : lines) {
              System.out.println(line);
              String finalStrLine = line;
              Laby.labyAPI().minecraft().executeOnRenderThread(() -> {
                Koords.add(finalStrLine);
              });
            }
          }
        }));
  }

  // - Use objects instead of strings for the data (maybe load from gson?)
  // - Use the internationalization system
}