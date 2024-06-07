package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;

public class BlitzerKeys {
  private final BlitzerWarner addon;
  public BlitzerKeys(BlitzerWarner addon) {
    this.addon = addon;
  }
  @Subscribe
  public void KeyEvent(KeyEvent e){
    if(!Laby.labyAPI().minecraft().minecraftWindow().isScreenOpened()){
      if(e.state().name().equalsIgnoreCase("PRESS")){
        if(e.key().getTranslationKey().equalsIgnoreCase(this.addon.configuration().toggleBind().get().getTranslationKey())){
          BlitzerAPI.toggleNotification(addon);
        }
      }
    }
  }
}