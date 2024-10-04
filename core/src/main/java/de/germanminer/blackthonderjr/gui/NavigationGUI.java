package de.germanminer.blackthonderjr.gui;

import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.api.BlitzerAPI;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget;
import net.labymod.api.client.render.matrix.Stack;

@AutoActivity
@Link("navi.lss")
public class NavigationGUI extends SimpleActivity {
  private BlitzerWarner addon;
  public NavigationGUI(BlitzerWarner addon){
    this.addon = addon;
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);
  }
  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    ButtonWidget button = ButtonWidget.text("Navigationsort Festlegen").addId("navi-button");
    this.document().addChild(button);
    ButtonWidget button2 = ButtonWidget.text("Navigationsort Clearen").addId("navi-button-two");
    this.document().addChild(button2);
    DropdownWidget dropdown = new DropdownWidget();
    dropdown.addId("navi-one");
    dropdown.clear();
    DropdownWidget dropdown2 = new DropdownWidget();
    dropdown2.addId("navi-two");
    dropdown2.clear();
    DropdownWidget dropdown3 = new DropdownWidget();
    dropdown3.addId("navi-three");
    dropdown3.clear();
    DropdownWidget dropdown4 = new DropdownWidget();
    dropdown4.addId("navi-four");
    dropdown4.clear();
    DropdownWidget dropdown5 = new DropdownWidget();
    dropdown5.addId("navi-five");
    dropdown5.clear();
    DropdownWidget dropdown6 = new DropdownWidget();
    dropdown6.addId("navi-six");
    dropdown6.clear();

    //dropdown.addAll(BlitzerWarner.NaviNames);
    for (int i = 0; i < BlitzerWarner.NaviNamesBiz.size(); i++) {
      dropdown.add(BlitzerWarner.NaviNamesBiz.get(i).replace("_", " "));
    }
    for (int i = 0; i < BlitzerWarner.NaviNamesWerkstätte.size(); i++) {
      dropdown2.add(BlitzerWarner.NaviNamesWerkstätte.get(i).replace("_", " "));
    }
    for (int i = 0; i < BlitzerWarner.NaviNamesStaatsfirmen.size(); i++) {
      dropdown3.add(BlitzerWarner.NaviNamesStaatsfirmen.get(i).replace("_", " "));
    }
    for (int i = 0; i < BlitzerWarner.NaviNamesSpielershops.size(); i++) {
      dropdown4.add(BlitzerWarner.NaviNamesSpielershops.get(i).replace("_", " "));
    }
    for (int i = 0; i < BlitzerWarner.NaviNamesOrteVonInteresse.size(); i++) {
      dropdown5.add(BlitzerWarner.NaviNamesOrteVonInteresse.get(i).replace("_", " "));
    }
    for (int i = 0; i < BlitzerWarner.NaviNamesStadtgebietHilfe.size(); i++) {
      dropdown6.add(BlitzerWarner.NaviNamesStadtgebietHilfe.get(i).replace("_", " "));
    }


    this.document().addChild(dropdown);
    this.document().addChild(dropdown2);
    this.document().addChild(dropdown3);
    this.document().addChild(dropdown4);
    this.document().addChild(dropdown5);
    this.document().addChild(dropdown6);


    button.setActionListener(() -> {
      if(BlitzerWarner.isOnline) {
        if(dropdown.getSelected() != null) {
          BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown.getSelected().toString().replace(" ", "_"));
          System.out.println(dropdown.getSelected().toString());

          if (BlitzerWarner.LastNaviLoc != null) {
            System.out.println(BlitzerWarner.LastNaviLoc.getName());
            addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
          }
        }else{
          if(dropdown2.getSelected() != null) {
            BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown2.getSelected().toString().replace(" ", "_"));
            System.out.println(dropdown2.getSelected().toString());

            if (BlitzerWarner.LastNaviLoc != null) {
              System.out.println(BlitzerWarner.LastNaviLoc.getName());
              addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
            }
          }else{
            if(dropdown3.getSelected() != null) {
              BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown3.getSelected().toString().replace(" ", "_"));
              System.out.println(dropdown3.getSelected().toString());

              if (BlitzerWarner.LastNaviLoc != null) {
                System.out.println(BlitzerWarner.LastNaviLoc.getName());
                addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
              }
            }else{
              if(dropdown4.getSelected() != null) {
                BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown4.getSelected().toString().replace(" ", "_"));
                System.out.println(dropdown4.getSelected().toString());

                if (BlitzerWarner.LastNaviLoc != null) {
                  System.out.println(BlitzerWarner.LastNaviLoc.getName());
                  addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
                }
              }else{
                if(dropdown5.getSelected() != null) {
                  BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown5.getSelected().toString().replace(" ", "_"));
                  System.out.println(dropdown5.getSelected().toString());

                  if (BlitzerWarner.LastNaviLoc != null) {
                    System.out.println(BlitzerWarner.LastNaviLoc.getName());
                    addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
                  }
                }else{
                  if(dropdown6.getSelected() != null) {
                    BlitzerWarner.LastNaviLoc = BlitzerAPI.getLoc(dropdown6.getSelected().toString().replace(" ", "_"));
                    System.out.println(dropdown6.getSelected().toString());

                    if (BlitzerWarner.LastNaviLoc != null) {
                      System.out.println(BlitzerWarner.LastNaviLoc.getName());
                      addon.labyAPI().minecraft().chatExecutor().chat("/testnavi");
                    }
                  }
                }
              }
            }
          }
        }
      }
    });
    button2.setActionListener(() -> {
      dropdown.onSelect(null);
      dropdown2.onSelect(null);
      dropdown3.onSelect(null);
      dropdown4.onSelect(null);
      dropdown5.onSelect(null);
      dropdown6.onSelect(null);
    });
  }
}
