package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.component.format.TextDecoration;
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
          this.addon.displayMessage(Component.text(addon.configuration().prefix().get().toString(), TextColor.color(addon.configuration().prefixColor().get())).append(Component.text(" Die Benachrichtigungen wurden ", NamedTextColor.GRAY)).append(soundStatus.equals("true") ? Component.text("Deaktiviert!",
              NamedTextColor.RED) : Component.text("Aktiviert!", NamedTextColor.GREEN)));
          this.addon.configuration().all().set(!this.addon.configuration().all().get());
        }
      }
    }
  }
}
