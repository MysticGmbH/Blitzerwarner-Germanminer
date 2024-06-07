package de.germanminer.blackthonderjr.cfg;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
@SpriteTexture("settings.png")
public class BlitzerMainCfg extends AddonConfig {

  @SpriteSlot()
  @SwitchSetting @SettingSection("Addon")
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SpriteSlot(x = 4)
  private final BlitzerPrefixSubCfg prefixSub = new BlitzerPrefixSubCfg();

  @SpriteSlot(x = 5)
  @SettingSection("Ingame")
  private final BlitzerIngameSubCfg ingameSub = new BlitzerIngameSubCfg();

  @SpriteSlot(x = 2)
  @KeyBindSetting @SettingSection("Keybinds")
  private final ConfigProperty<Key> toggleBind = new ConfigProperty(Key.R);

  @SpriteSlot(y = 1)
  @SettingSection("Benachrichtigungstyp")
  private final BlitzerSoundSubCfg sub = new BlitzerSoundSubCfg();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Float> lautstaerke() {
    return this.ingameSub.lautstaerke();
  }

  public ConfigProperty<Integer> distanz() {
    return this.ingameSub.distanz();
  }
  public ConfigProperty<Key> toggleBind() {
    return this.toggleBind;
  }
  public Component prefix(){
    return prefixSub.prefix();
  }
  public ConfigProperty<Integer> title(){
    return prefixSub.title();
  }
  public ConfigProperty<Integer> subtitleColor(){
    return prefixSub.subtitleColor();
  }
  public ConfigProperty<Boolean> sound() {
    return sub.sound();
  }
  public ConfigProperty<Boolean> screen() {
    return sub.screen();
  }
  public ConfigProperty<Boolean> text() {
    return sub.text();
  }
  public ConfigProperty<Boolean> all(){
    return this.sub.all();
  }

  public ConfigProperty<Double> stay() {
    return this.ingameSub.stay();
  }
  public ConfigProperty<Double> fadeIn() {
    return this.ingameSub.fadeIn();
  }
  public ConfigProperty<Double> fadeOut() {
    return this.ingameSub.fadeOut();
  }
}
