package de.germanminer.blackthonderjr.gui.element;


import de.germanminer.blackthonderjr.BlitzerWarner;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.navigation.elements.ScreenNavigationElement;

public class NavigationNavigationElement extends ScreenNavigationElement {

  BlitzerWarner addon;

  public NavigationNavigationElement(BlitzerWarner addon) {
    super(addon.navigationGUI());
    this.addon = addon;
  }

  @Override
  public String getWidgetId() {
    return "blitzerwarner_navi_main";
  }

  @Override
  public Component getDisplayName() {
    return Component.translatable("blitzerwarner.gui.navi.title");
  }

  @Override
  public Icon getIcon() {
    return null;
  }

}