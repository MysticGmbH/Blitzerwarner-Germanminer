package de.germanminer.blackthonderjr;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class BlitzerConfiguration extends AddonConfig {

  @SwitchSetting @SettingSection("Addon")
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @TextFieldSetting
  private final ConfigProperty<String> prefix = new ConfigProperty<>("&0&lBlit&4&lzer Wa&e&lrner");
  @TextFieldSetting
  private final ConfigProperty<String> title = new ConfigProperty<>("&4");
  @TextFieldSetting
  private final ConfigProperty<String> subtitleColor = new ConfigProperty<>("&6");
  @SwitchSetting @SettingSection("Ingame")
  private final ConfigProperty<Boolean> sound = new ConfigProperty<>(true);
  @SliderSetting(min = 0.0f, max = 2.0f, steps = 0.1f)
  private final ConfigProperty<Float> lautstaerke = new ConfigProperty<>(1f);
  @SliderSetting(min = 50, max = 200, steps = 1)
  private final ConfigProperty<Integer> distanz = new ConfigProperty<>(100);
  @KeyBindSetting @SettingSection("Keybinds")
  private final ConfigProperty<Key> toggleBind = new ConfigProperty(Key.R);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> sound() {
    return this.sound;
  }

  public ConfigProperty<Float> lautstaerke() {
    return this.lautstaerke;
  }

  public ConfigProperty<Integer> distanz() {
    return this.distanz;
  }
  public ConfigProperty<Key> toggleBind() {
    return this.toggleBind;
  }
  public ConfigProperty<String> prefix(){
    return this.prefix;
  }
  public ConfigProperty<String> title(){
    return this.title;
  }
  public ConfigProperty<String> subtitleColor(){
    return this.subtitleColor;
  }
}
