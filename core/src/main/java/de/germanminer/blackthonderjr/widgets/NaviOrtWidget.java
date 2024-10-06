package de.germanminer.blackthonderjr.widgets;

import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;

public class NaviOrtWidget extends TextHudWidget<TextHudWidgetConfig> {
  public static TextLine NAVI_TEXT;

  private static BlitzerWarner addon;

  public NaviOrtWidget(BlitzerWarner addon) {
    super("naviort");
    this.addon = addon;
  }

  public void load(TextHudWidgetConfig config) {
    super.load(config);
    NAVI_TEXT = createLine("Aktueller Ort", "None");
    NAVI_TEXT.setVisible(false);
  }
}