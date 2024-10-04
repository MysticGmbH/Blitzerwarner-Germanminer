package de.germanminer.blackthonderjr.cfg;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
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
  private Component prefix = Component.text(Laby.references().componentMapper().translateColorCodes("&0&lGM-&4&lUti&e&lls"));

  @ShowSettingInParent
  @TextFieldSetting
  private ConfigProperty<String> prefixSetting = new ConfigProperty<>("&0&lGM-&4&lUti&e&lls")
      .addChangeListener(s -> prefix = Component.text(Laby.references().componentMapper().translateColorCodes(s)));

  @ColorPickerSetting
  private final ConfigProperty<Integer> title = new ConfigProperty<>(
      new Color(168, 0, 0).getRGB());

  @ColorPickerSetting
  private final ConfigProperty<Integer> subtitleColor = new ConfigProperty<>(
      new Color(229, 126,0).getRGB());


  public Component prefix() {
    return this.prefix;
  }
  public ConfigProperty<Integer> title(){
    return this.title;
  }
  public ConfigProperty<Integer> subtitleColor(){
    return this.subtitleColor;
  }
}
