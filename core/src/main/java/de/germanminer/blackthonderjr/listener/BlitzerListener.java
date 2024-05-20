package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.Title;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.entity.player.ClientPlayerTurnEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlitzerListener {

  private final BlitzerWarner addon;
  public static boolean isInRange;
  private boolean hasWarned = false;
  public static String prefix = BlitzerWarner.prefix;
  public static ArrayList<String> speeds = new ArrayList<>();

  public BlitzerListener(BlitzerWarner addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onJoin(ServerJoinEvent e){
    if(e.serverData().address().toString().equalsIgnoreCase("germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("mc.germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("localhost")){
      BlitzerWarner.Koords.clear();
      addon.loadBlitzer();
      addon.displayMessage("");
    }
  }

  @Subscribe
  public void onGameTick(ClientPlayerTurnEvent event) {
    speeds.clear();
    LabyAPI labyAPI = this.addon.labyAPI();
    if(labyAPI.minecraft().isSingleplayer()){
      return;
    }
    if (labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("germanminer.de") || labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("mc.germanminer.de") || labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("localhost")) {
      ClientPlayer player = labyAPI.minecraft().getClientPlayer();
      if(player == null){
        return;
      }
      boolean foundInRange = BlitzerWarner.Koords.stream()
          .map(eintrag -> eintrag.split(" "))
          .anyMatch(parts -> isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), addon.configuration().distanz().get()));

      if (foundInRange && !isInRange) {
        List<String[]> allParts = BlitzerWarner.Koords.stream()
            .map(eintrag -> eintrag.split(" "))
            .filter(parts -> isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), addon.configuration().distanz().get()))
            .collect(Collectors.toList());

        for (String[] parts : allParts) {
          if (player.getVehicle() != null) {
            if (addon.configuration().all().get()) {
              if (addon.configuration().text().get()) {
                this.addon.displayMessage(buildWarnMessage(addon.configuration().distanz().get(),
                    Integer.valueOf(parts[4]), parts[3].replace("_", " "),
                    Integer.parseInt(parts[0]) + " " + Integer.parseInt(parts[1]) + " "
                        + Integer.parseInt(parts[2])));
              }
              if (addon.configuration().sound().get()) {
                labyAPI.minecraft().sounds()
                    .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
                        addon.configuration().lautstaerke().get());
              }
              if(allParts.size() < 2) {
                if (addon.configuration().screen().get()) {
                  Title title = new Title(Component.text(
                      this.addon.configuration().title().get().toString().replace("&", "§")
                          + "Blitzer in Reichweite"),
                      Component.text(this.addon.configuration().subtitleColor().get().toString()
                          .replace("&", "§") + "Geschwindigkeit: " + Integer.valueOf(parts[4])
                          + " km/h"), (int) (20 * addon.configuration().fadeIn().get()),
                      (int) (20 * addon.configuration().stay().get()),
                      (int) (20 * addon.configuration().fadeOut().get()));
                  labyAPI.minecraft().showTitle(title);
                }
              }else{
                if (addon.configuration().screen().get()) {
                  Title title = new Title(Component.text(
                      this.addon.configuration().title().get().toString().replace("&", "§")
                          + "Mehrere Blitzer in Reichweite"),
                      Component.text(this.addon.configuration().subtitleColor().get().toString()
                          .replace("&", "§") + "Geschwindigkeiten: Siehe Chat"),
                      (int) (20 * addon.configuration().fadeIn().get()),
                      (int) (20 * addon.configuration().stay().get()),
                      (int) (20 * addon.configuration().fadeOut().get()));
                  labyAPI.minecraft().showTitle(title);
                }
              }
            }
            hasWarned = true;
          } else {
            hasWarned = false;
            isInRange = false;
          }
        }
        if (player.getVehicle() != null) {
          isInRange = true;
        } else {
          isInRange = false;
        }
      } else if (!foundInRange) {
        isInRange = false;
        hasWarned = false;
      } else if(isInRange){
        if(player.getVehicle() == null){
          isInRange = false;
        }
      }


    }
  }

  public static String buildWarnMessage(Integer Reichweite, Integer Geschwindigkeit, String Gebiet, String GenaueKoords){
    String message = prefix + "§7Blitzer in Reichweite von §2" + Reichweite +" Blöcken.\n§7Koordinaten: §2" + GenaueKoords + "\n§7Gebiet: §2" + Gebiet +"\n§7Geschwindigkeit: §2" + Geschwindigkeit + " KM/H";
    return message;
  }

  public boolean isWithinRadius(float x, float y, float z, float centerX, float centerY, float centerZ, float radius) {
    double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) + Math.pow(z - centerZ, 2);
    double radiusSquared = Math.pow(radius, 2);

    return distanceSquared <= radiusSquared;
  }
}
