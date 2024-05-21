package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.Blitzer;
import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.Title;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.util.io.web.request.Request;

public class BlitzerListener {

  private final BlitzerWarner addon;
  public static boolean isInRange;
  private boolean hasWarned = false;

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
      BlitzerWarner.isOnline = false;
    }
  }
  @Subscribe
  public void onGameTick(GameTickEvent event) {
      LabyAPI labyAPI = this.addon.labyAPI();
      if (labyAPI.minecraft().isSingleplayer()) {
        return;
      }
      if (BlitzerWarner.isOnline) {
        ClientPlayer player = labyAPI.minecraft().getClientPlayer();
        if (player == null) {
          return;
        }
        boolean foundInRange = false;
        for (Blitzer blitzer : BlitzerWarner.Koords) {
          if (isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(),
              blitzer.getX(), blitzer.getY(), blitzer.getZ(),
              addon.configuration().distanz().get())) {
            foundInRange = true;
            break;
          }
        }

        Blitzer foundBlitzer = null;
        if (foundInRange && !isInRange) {
          for (Blitzer blitzer : BlitzerWarner.Koords) {
            if (isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(),
                blitzer.getX(), blitzer.getY(), blitzer.getZ(),
                addon.configuration().distanz().get())) {
              foundBlitzer = blitzer;
              break;
            }
          }
          if (foundBlitzer != null) {
            if (player.getVehicle() != null) {
              if (addon.configuration().all().get()) {
                if (this.addon.configuration().text().get()) {
                  String loc = foundBlitzer.getX() + " " + foundBlitzer.getY() + " "
                      + foundBlitzer.getZ();
                  this.addon.displayMessage(buildWarnMessage(addon.configuration().distanz().get(),
                      foundBlitzer.getGeschwindigkeit(), foundBlitzer.getGebiet(),
                      loc, addon));
                }
                if (this.addon.configuration().sound().get()) {
                  labyAPI.minecraft().sounds()
                      .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
                          addon.configuration().lautstaerke().get());
                }
                if (this.addon.configuration().screen().get()) {
                  Title title = new Title(
                      Component.text("Blitzer in Reichweite",
                      TextColor.color(this.addon.configuration().title().get())),
                      Component.text("Geschwindigkeit: " + foundBlitzer.getGeschwindigkeit() +" km/h",
                      TextColor.color(this.addon.configuration().subtitleColor().get())),
                      (int) (20 * addon.configuration().fadeIn().get()),
                      (int) (20 * addon.configuration().stay().get()),
                      (int) (20 * addon.configuration().fadeOut().get()));
                  labyAPI.minecraft().showTitle(title);
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
        } else if (isInRange) {
          if (player.getVehicle() == null) {
            isInRange = false;
          }
        }


      }
  }



  public static Component buildWarnMessage(Integer Reichweite, Integer Geschwindigkeit, String Gebiet, String GenaueKoords,BlitzerWarner Addon){
    return Component.text(Addon.configuration().prefix().get().toString(), TextColor.color(Addon.configuration().prefixColor().get()))
        .append(Component.translatable("blitzerwarner.messages.text1builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text2builder", NamedTextColor.DARK_GREEN, Component.text(Reichweite)))
        .append(Component.translatable("blitzerwarner.messages.text3builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text6builder", NamedTextColor.DARK_GREEN, Component.text(GenaueKoords)))
        .append(Component.translatable("blitzerwarner.messages.text4builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text6builder", NamedTextColor.DARK_GREEN, Component.text(Gebiet)))
        .append(Component.translatable("blitzerwarner.messages.text5builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text7builder", NamedTextColor.DARK_GREEN, Component.text(Geschwindigkeit)));
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