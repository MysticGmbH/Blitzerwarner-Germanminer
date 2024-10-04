package de.germanminer.blackthonderjr.widgets;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;

public class NaviOrtWidget extends TextHudWidget<TextHudWidgetConfig> {
  public static TextLine RS_TIME;

  public static int RS_LEFT;

  private static BlitzerWarner addon;

  public NaviOrtWidget(BlitzerWarner polizeiAddon) {
    super("naviort");
    addon = polizeiAddon;
  }

  public void load(TextHudWidgetConfig config) {
    super.load(config);
    RS_TIME = createLine("Aktueller Ort", "None");
    RS_TIME.setVisible(false);
  }
}
