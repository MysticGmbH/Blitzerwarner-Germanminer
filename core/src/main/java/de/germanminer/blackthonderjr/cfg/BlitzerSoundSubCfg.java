package de.germanminer.blackthonderjr.cfg;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ShowSettingInParent;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class BlitzerSoundSubCfg extends Config {

  @ShowSettingInParent
  @SwitchSetting
  private final ConfigProperty<Boolean> all = new ConfigProperty<>(true);
  @SpriteSlot(x = 6)
  @SwitchSetting
  private final ConfigProperty<Boolean> sound = new ConfigProperty<>(true);
  @SpriteSlot(x = 1)
  @SwitchSetting
  private final ConfigProperty<Boolean> text = new ConfigProperty<>(true);
  @SpriteSlot(x = 3)
  @SwitchSetting
  private final ConfigProperty<Boolean> screen = new ConfigProperty<>(true);

  public ConfigProperty<Boolean> sound() {
    return this.sound;
  }
  public ConfigProperty<Boolean> screen() {
    return this.screen;
  }
  public ConfigProperty<Boolean> text() {
    return this.text;
  }
  public ConfigProperty<Boolean> all(){
    return this.all;
  }

}
