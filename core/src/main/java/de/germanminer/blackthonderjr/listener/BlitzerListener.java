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
import java.util.Optional;

public class BlitzerListener {

  private final BlitzerWarner addon;
  public static boolean isInRange;
  private boolean hasWarned = false;
  public static String prefix;

  public BlitzerListener(BlitzerWarner addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameJoin(ServerJoinEvent event) {
    if(event.serverData().address().toString().equalsIgnoreCase("mc.germanminer.de") || event.serverData().address().toString().equalsIgnoreCase("germanminer.de") || event.serverData().address().toString().equalsIgnoreCase("localhost")){
      this.addon.displayMessage(BlitzerWarner.prefix + "§7ist §2Aktiviert!");
    }
  }
  @Subscribe
  public void onGameTick(ClientPlayerTurnEvent event) {
    LabyAPI labyAPI = this.addon.labyAPI();
    if (labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("germanminer.de") || labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("mc.germanminer.de") || labyAPI.serverController().getCurrentServerData().address().toString().equalsIgnoreCase("localhost")) {
      ClientPlayer player = labyAPI.minecraft().getClientPlayer();
      if(player == null){
        return;
      }
      boolean foundInRange = BlitzerWarner.Koords.stream()
          .map(eintrag -> eintrag.split(" "))
          .anyMatch(parts -> isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), addon.configuration().distanz().get()));

      if (foundInRange && !isInRange) {
        Optional<String[]> optionalParts = BlitzerWarner.Koords.stream()
            .map(eintrag -> eintrag.split(" "))
            .filter(parts -> isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), addon.configuration().distanz().get()))
            .findFirst();

        if (optionalParts.isPresent()) {
          String[] parts = optionalParts.get();
          if(player.getVehicle() != null){
            if(this.addon.configuration().text().get()){
              this.addon.displayMessage(buildWarnMessage(addon.configuration().distanz().get(), Integer.valueOf(parts[4]), parts[3].replace("_", " "), Integer.parseInt(parts[0]) + " " + Integer.parseInt(parts[1]) + " " + Integer.parseInt(parts[2])));
            }
            if (this.addon.configuration().sound().get()) {
              labyAPI.minecraft().sounds()
                  .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
                      addon.configuration().lautstaerke().get());
            }
            if(this.addon.configuration().screen().get()){
              Title title = new Title(Component.text(this.addon.configuration().title().get().toString().replace("&","§") + "Blitzer in Reichweite"),
                  Component.text(this.addon.configuration().subtitleColor().get().toString().replace("&","§") + "§6Geschwindigkeit: " + Integer.valueOf(parts[4]) + " km/h"), 10, 50, 10);
              labyAPI.minecraft().showTitle(title);
            }
            hasWarned = true;
          }else{
            hasWarned = false;
            isInRange = false;
          }
        }
        if(player.getVehicle() != null){
          isInRange = true;
        }else{
          isInRange = false;
        }
      } else if (!foundInRange) {
        isInRange = false;
        hasWarned = false;
      }else if(isInRange){
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

  public boolean isCoords(float x, float y, float z, int isX, int isY, int isZ){
    if ((int) x == isX && (int) y == isY && (int) z == isZ) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isWithinRadius(float x, float y, float z, float centerX, float centerY, float centerZ, float radius) {
    double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) + Math.pow(z - centerZ, 2);
    double radiusSquared = Math.pow(radius, 2);

    return distanceSquared <= radiusSquared;
  }
}
