package de.germanminer.blackthonderjr;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import java.awt.*;

@ConfigName("settings")
public class BlitzerConfiguration extends AddonConfig {

  @SwitchSetting @SettingSection("Addon")
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @TextFieldSetting
  private final ConfigProperty<String> prefixText = new ConfigProperty<>("Blitzer Warner");
  @ColorPickerSetting
  private final ConfigProperty<Integer> prefixColor = new ConfigProperty<>(
      new Color(255, 229, 0).getRGB());

  @ColorPickerSetting
  private final ConfigProperty<Integer> title = new ConfigProperty<>(
      new Color(168, 0, 0).getRGB());
  @ColorPickerSetting
  private final ConfigProperty<Integer> subtitleColor = new ConfigProperty<>(
      new Color(229, 126,0).getRGB());
  @SliderSetting(min = 0.0f, max = 2.0f, steps = 0.1f) @SettingSection("Ingame")
  private final ConfigProperty<Float> lautstaerke = new ConfigProperty<>(1f);
  @SliderSetting(min = 50, max = 200, steps = 1)
  private final ConfigProperty<Integer> distanz = new ConfigProperty<>(100);
  @KeyBindSetting @SettingSection("Keybinds")
  private final ConfigProperty<Key> toggleBind = new ConfigProperty(Key.R);

  @SwitchSetting @SettingSection("Benachrichtigungstyp")
  private final ConfigProperty<Boolean> all = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> sound = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> text = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> screen = new ConfigProperty<>(true);

  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f) @SettingSection("Bildschirmbenachrichtigung")
  private final ConfigProperty<Double> fadeIn = new ConfigProperty<>(0.5);

  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f)
  private final ConfigProperty<Double> stay = new ConfigProperty<>(2.5);

  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f)
  private final ConfigProperty<Double> fadeOut = new ConfigProperty<>(0.5);
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
    return this.prefixText;
  }
  public ConfigProperty<Integer> prefixColor(){
    return this.prefixColor;
  }
  public ConfigProperty<Integer> title(){
    return this.title;
  }
  public ConfigProperty<Integer> subtitleColor(){
    return this.subtitleColor;
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

  public ConfigProperty<Double> stay() {
    return this.stay;
  }
  public ConfigProperty<Double> fadeIn() {
    return this.fadeIn;
  }
  public ConfigProperty<Double> fadeOut() {
    return this.fadeOut;
  }
}
