package de.germanminer.blackthonderjr.widgets;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;

public class RSTimerWidget extends TextHudWidget<TextHudWidgetConfig> {
  public static TextLine RS_TIME;


  public RSTimerWidget() {
    super("rstimer");
  }

  public void load(TextHudWidgetConfig config) {
    super.load(config);
    RS_TIME = createLine("Respawnschutz", "");
    RS_TIME.setVisible(false);
  }
}
