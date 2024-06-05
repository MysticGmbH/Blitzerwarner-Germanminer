package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.Blitzer;
import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;

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
      BlitzerAPI.loadBlitzer();
      BlitzerWarner.isOnline = true;
      isInRange = false;
    }
  }
  @Subscribe
  public void onDisconnect(ServerDisconnectEvent e){
    if(e.serverData().address().toString().equalsIgnoreCase("germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("mc.germanminer.de") || e.serverData().address().toString().equalsIgnoreCase("localhost")){
      BlitzerWarner.Koords.clear();
      BlitzerWarner.isOnline = false;
      isInRange = false;
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
      Blitzer foundBlitzer = null;
      for (Blitzer blitzer : BlitzerWarner.Koords) {
        if (BlitzerAPI.isWithinRadius(player.getPosX(), player.getPosY(), player.getPosZ(),
            blitzer.getX(), blitzer.getY(), blitzer.getZ(),
            addon.configuration().distanz().get())) {
          foundInRange = true;
          foundBlitzer = blitzer;
          break;
        }
      }

      if (foundInRange && !isInRange && foundBlitzer != null) {
        if (player.getVehicle() != null) {
          if (addon.configuration().all().get()) {
            if (this.addon.configuration().text().get()) {
              BlitzerAPI.sendMessage(foundBlitzer,addon);
            }
            if (this.addon.configuration().sound().get()) {
              BlitzerAPI.playSound(addon);
            }
            if (this.addon.configuration().screen().get()) {
              BlitzerAPI.seeTitle(foundBlitzer, addon);
            }

          }
          hasWarned = true;
        } else {
          hasWarned = false;
          isInRange = false;
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
}