package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.BlitzerWarner;
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
          String soundStatus = this.addon.configuration().all().get().toString();
          String message = BlitzerWarner.prefix + "§7Die Benachrichtigungen wurden " + (soundStatus.equals("true") ? "§4Deaktiviert!" : "§aAktiviert!");
          this.addon.displayMessage(message);
          this.addon.configuration().all().set(!this.addon.configuration().all().get());
        }
      }
    }
  }

}
