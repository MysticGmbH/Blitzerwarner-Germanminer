package de.germanminer.blackthonderjr.cfg;

import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.ShowSettingInParent;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
@SpriteTexture("settings.png")
public class BlitzerIngameSubCfg extends Config {

  @ShowSettingInParent
  @SliderSetting(min = 50, max = 200, steps = 1)
  private final ConfigProperty<Integer> distanz = new ConfigProperty<>(100);
  @SpriteSlot(x = 7)
  @SliderSetting(min = 0.0f, max = 2.0f, steps = 0.1f)
  private final ConfigProperty<Float> lautstaerke = new ConfigProperty<>(1f);
  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f) @SettingSection("Bildschirmbenachrichtigung")
  private final ConfigProperty<Double> fadeIn = new ConfigProperty<>(0.5);

  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f)
  private final ConfigProperty<Double> stay = new ConfigProperty<>(2.5);

  @SliderSetting(min = 0.5f, max = 10.0f, steps = 0.5f)
  private final ConfigProperty<Double> fadeOut = new ConfigProperty<>(0.5);

  public ConfigProperty<Float> lautstaerke() {
    return this.lautstaerke;
  }

  public ConfigProperty<Integer> distanz() {
    return this.distanz;
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
