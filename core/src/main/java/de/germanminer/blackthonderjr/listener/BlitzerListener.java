package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.Title;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.component.format.TextDecoration;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import java.util.Optional;

public class BlitzerListener {

  private final BlitzerWarner addon;
  public static boolean isInRange;
  private boolean hasWarned = false;
  public static Component prefix = BlitzerWarner.prefix;
  public static Component message;

  public BlitzerListener(BlitzerWarner addon) {
    this.addon = addon;
  }
  @Subscribe
  public void onJoin(ServerJoinEvent e){
    if(e.serverData().address().toString().equalsIgnoreCase("germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("mc.germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("localhost")){
      BlitzerWarner.Koords.clear();
      addon.loadBlitzer();
      BlitzerWarner.isOnline = true;
    }
  }
  @Subscribe
  public void onDisconnect(ServerDisconnectEvent e){
    if(e.serverData().address().toString().equalsIgnoreCase("germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("mc.germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("localhost")){
      BlitzerWarner.Koords.clear();
      addon.loadBlitzer();
      BlitzerWarner.isOnline = false;
    }
  }
  @Subscribe
  public void onGameTick(GameTickEvent event) {
    LabyAPI labyAPI = this.addon.labyAPI();
    if(labyAPI.minecraft().isSingleplayer()){
      return;
    }
    if (BlitzerWarner.isOnline) {
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
            if(addon.configuration().all().get()) {
              if (this.addon.configuration().text().get()) {
                this.addon.displayMessage(buildWarnMessage(addon.configuration().distanz().get(),
                    Integer.valueOf(parts[4]), parts[3].replace("_", " "),
                    Integer.parseInt(parts[0]) + " " + Integer.parseInt(parts[1]) + " "
                        + Integer.parseInt(parts[2]), addon));
              }
              if (this.addon.configuration().sound().get()) {
                labyAPI.minecraft().sounds()
                    .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
                        addon.configuration().lautstaerke().get());
              }
              if (this.addon.configuration().screen().get()) {
                Title title = new Title(Component.text("Blitzer in Reichweite", TextColor.color(this.addon.configuration().title().get())),
                    Component.text("Geschwindigkeit: " + Integer.valueOf(parts[4])
                        + " km/h", TextColor.color(this.addon.configuration().subtitleColor().get())),
                    (int) (20 * addon.configuration().fadeIn().get()),
                    (int) (20 * addon.configuration().stay().get()),
                    (int) (20 * addon.configuration().fadeOut().get()));
                labyAPI.minecraft().showTitle(title);
              }
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



  public static Component buildWarnMessage(Integer Reichweite, Integer Geschwindigkeit, String Gebiet, String GenaueKoords,BlitzerWarner Addon){
    return Component.text(Addon.configuration().prefix().get().toString(), TextColor.color(Addon.configuration().prefixColor().get()))
        .append(Component.text(" Blitzer in Reichweite von ", NamedTextColor.GRAY))
        .append(Component.text(Reichweite + " Blöcken", NamedTextColor.DARK_GREEN))
        .append(Component.text("\nKoordinaten: ", NamedTextColor.GRAY))
        .append(Component.text(GenaueKoords, NamedTextColor.DARK_GREEN))
        .append(Component.text("\nGebiet: ", NamedTextColor.GRAY))
        .append(Component.text(Gebiet, NamedTextColor.DARK_GREEN))
        .append(Component.text("\nGeschwindigkeit: ", NamedTextColor.GRAY))
        .append(Component.text(Geschwindigkeit + "KM/H", NamedTextColor.DARK_GREEN));
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

// - Set a boolean on server join/leave if the user is on the server instead of checking it every time  X
// - Do not use streams
// - Reformat BlitzerWarner.Koords to contain a Object that holds all the information in different fields
// - Use the GameTickEvent in either phase PRE or POST instead of ClientPlayerTurnEvent  X
// - Do not use legacy color codes X
// - Use Component.translatable (Guidelines #6) X