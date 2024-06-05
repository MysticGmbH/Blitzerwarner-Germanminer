package de.germanminer.blackthonderjr.cfg;

import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.ShowSettingInParent;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import java.awt.*;

@ConfigName("settings")
@SpriteTexture("settings.png")
public class BlitzerPrefixSubCfg extends Config {
  @ShowSettingInParent
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
}
