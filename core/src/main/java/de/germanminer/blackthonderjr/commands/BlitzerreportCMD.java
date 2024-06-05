package de.germanminer.blackthonderjr.commands;

import de.germanminer.blackthonderjr.Blitzer;
import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import java.util.HashMap;
import java.util.UUID;

public class BlitzerreportCMD extends Command {
  public final BlitzerWarner addon;
  private final HashMap<UUID, Long> cooldowns = new HashMap<>();

  public BlitzerreportCMD(BlitzerWarner addon) {
    super("Blitzerreport");
    this.addon = addon;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (prefix.equalsIgnoreCase("Blitzerreport")) {
      if(arguments.length == 0){
        addon.displayMessage(Component.text(addon.configuration().prefix().get().toString(), TextColor.color(addon.configuration().prefixColor().get()))
            .append(Component.translatable("blitzerwarner.messages.bmusage", NamedTextColor.RED)));
      }else if (arguments.length == 5) {

        UUID user = labyAPI.getUniqueId();
        if(cooldowns.containsKey(user)) {
          long secondsLeft = ((cooldowns.get(user)/1000)+60) - (System.currentTimeMillis()/1000);
          if(secondsLeft>0) {
            addon.displayMessage(Component.text(addon.configuration().prefix().get().toString(), TextColor.color(addon.configuration().prefixColor().get())).append(Component.translatable("blitzerwarner.messages.cooldownerror", NamedTextColor.RED, Component.text(secondsLeft))));
            return true;
          }
        }
        cooldowns.put(user, System.currentTimeMillis());

        if (arguments[0] != null && arguments[1] != null && arguments[2] != null
            && arguments[3] != null && arguments[4] != null) {
          Blitzer blitzer = new Blitzer(Integer.valueOf(arguments[0]),
              Integer.valueOf(arguments[1]), Integer.valueOf(arguments[2]), arguments[3],
              Integer.valueOf(arguments[4]));
          BlitzerAPI.sendBlitzer(blitzer, addon);
        } else {
          addon.displayMessage(Component.text(addon.configuration().prefix().get().toString(), TextColor.color(addon.configuration().prefixColor().get()))
              .append(Component.translatable("blitzerwarner.messages.errornull", NamedTextColor.RED)));
        }
      } else {
        addon.displayMessage(Component.text(addon.configuration().prefix().get().toString(), TextColor.color(addon.configuration().prefixColor().get()))
            .append(Component.translatable("blitzerwarner.messages.errornull", NamedTextColor.RED)));
      }

    }
    return true;
  }
}
