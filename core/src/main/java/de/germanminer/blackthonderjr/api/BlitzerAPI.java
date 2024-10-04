package de.germanminer.blackthonderjr.api;

import com.google.gson.JsonObject;
import de.germanminer.blackthonderjr.Blitzer;
import de.germanminer.blackthonderjr.BlitzerWarner;
import de.germanminer.blackthonderjr.Location;
import de.germanminer.blackthonderjr.Navigation;
import de.germanminer.blackthonderjr.gui.NavigationGUI;
import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.chat.Title;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.io.web.request.Request.Method;

import static net.labymod.api.client.component.Component.text;

public class BlitzerAPI {
  public static Component buildWarnMessage(Integer Reichweite, Integer Geschwindigkeit, String Gebiet, String GenaueKoords, BlitzerWarner addon){
    Component message = text()
        .append(addon.configuration().prefix())
        .append(Component.translatable("blitzerwarner.messages.text1builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text2builder", NamedTextColor.DARK_GREEN, text(Reichweite)))
        .append(Component.translatable("blitzerwarner.messages.text3builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text6builder", NamedTextColor.DARK_GREEN, text(GenaueKoords)))
        .append(Component.translatable("blitzerwarner.messages.text4builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text6builder", NamedTextColor.DARK_GREEN, text(Gebiet)))
        .append(Component.translatable("blitzerwarner.messages.text5builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messages.text7builder", NamedTextColor.DARK_GREEN, text(Geschwindigkeit)))
        .build();


    return message;
  }

  public static Component buildNaviMessage(Integer Reichweite, String Gebiet, String GenaueKoords, String Ziel, BlitzerWarner addon, Navigation navi ){
    ClientPlayer clientPlayer = addon.labyAPI().minecraft().getClientPlayer();
    int Blöcke = berechneDistanz(new Location((int)clientPlayer.getPosX(), (int)clientPlayer.getPosY(), (int)clientPlayer.getPosZ()), new Location(navi.getX(), navi.getY(), navi.getZ()));
    Component message = text()
        .append(addon.configuration().prefix())
        .append(Component.translatable("blitzerwarner.messagesnavi.text1builder", NamedTextColor.DARK_GREEN))
        .append(Component.translatable("blitzerwarner.messagesnavi.text2builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messagesnavi.text5builder", NamedTextColor.DARK_GREEN, text(Blöcke)))
        .append(Component.translatable("blitzerwarner.messagesnavi.text3builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messagesnavi.text5builder", NamedTextColor.DARK_GREEN, text(GenaueKoords)))
        .append(Component.translatable("blitzerwarner.messagesnavi.text4builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messagesnavi.text5builder", NamedTextColor.DARK_GREEN, text(Gebiet)))
        .append(Component.translatable("blitzerwarner.messagesnavi.text6builder", NamedTextColor.GRAY))
        .append(Component.translatable("blitzerwarner.messagesnavi.text5builder", NamedTextColor.DARK_GREEN, text(Ziel)))
        .build();


    return message;
  }
  public static Component prefixMessageBilder(BlitzerWarner addon, String s, TextColor n){
    Component message = text().append(addon.configuration().prefix())
        .append(text(" " + s,n))
        .build();

    return message;
  }

  public static void seeTitle(Blitzer foundBlitzer, BlitzerWarner addon){
    LabyAPI labyAPI = addon.labyAPI();
    Title title = new Title(
        Component.translatable("blitzerwarner.screen.texttitle",
            TextColor.color(addon.configuration().title().get())),
        Component.translatable("blitzerwarner.screen.textsubtitle",
            TextColor.color(addon.configuration().subtitleColor().get()),
            text(foundBlitzer.getGeschwindigkeit())),
        (int) (20 * addon.configuration().fadeIn().get()),
        (int) (20 * addon.configuration().stay().get()),
        (int) (20 * addon.configuration().fadeOut().get()));
    labyAPI.minecraft().showTitle(title);
  }

  public static void playSound(BlitzerWarner addon){
    LabyAPI labyAPI = addon.labyAPI();
    labyAPI.minecraft().sounds()
        .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
            addon.configuration().lautstaerke().get());
  }

  public static void sendMessage(Blitzer foundBlitzer, BlitzerWarner addon){
    String loc = foundBlitzer.getX() + " " + foundBlitzer.getY() + " "
        + foundBlitzer.getZ();
    addon.displayMessage(buildWarnMessage(addon.configuration().distanz().get(),
        foundBlitzer.getGeschwindigkeit(), foundBlitzer.getGebiet(),
        loc, addon));
  }

  public static boolean isWithinRadius(float x, float y, float z, float centerX, float centerY, float centerZ, float radius) {
    double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) + Math.pow(z - centerZ, 2);
    double radiusSquared = Math.pow(radius, 2);

    return distanceSquared <= radiusSquared;
  }

  public static void loadBlitzer(){
    Request.ofString()
        .url("http://85.117.241.176/Blitzer.txt")
        .method(Method.GET)
        .async()
        .execute((stringResponse -> {
          String responseContent = stringResponse.get();
          if (responseContent != null) {
            String[] lines = responseContent.split("\n");
            for (String line : lines) {
              System.out.println(line);
              String[] parts = line.split(" ");
              Blitzer blitzer = new Blitzer(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3].replace("_", " "), Integer.parseInt(parts[4]));
              Laby.labyAPI().minecraft().executeOnRenderThread(() -> {
                BlitzerWarner.Koords.add(blitzer);
              });
            }
          }
        }));
  }
  public static void loadNavi(){
    Request.ofString()
        .url("http://85.117.241.176/Navi.txt")
        .method(Method.GET)
        .async()
        .execute((stringResponse -> {
          String responseContent = stringResponse.get();
          if (responseContent != null) {
            String[] lines = responseContent.split("\n");
            for (String line : lines) {
              System.out.println(line);
              String[] parts = line.split(" ");
              Navigation navi = new Navigation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3].replace("_", " "), parts[4], parts[5]);
              Laby.labyAPI().minecraft().executeOnRenderThread(() -> {
                switch (parts[5]){
                  case "Biz":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesBiz.add(parts[4]);
                    BlitzerWarner.NaviNamesBiz.sort(String::compareTo);
                    break;
                  case "Werkstätte":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesWerkstätte.add(parts[4]);
                    BlitzerWarner.NaviNamesWerkstätte.sort(String::compareTo);
                    break;
                  case "Staatsfirmen":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesStaatsfirmen.add(parts[4]);
                    BlitzerWarner.NaviNamesStaatsfirmen.sort(String::compareTo);
                    break;
                  case "Spielershops":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesSpielershops.add(parts[4]);
                    BlitzerWarner.NaviNamesSpielershops.sort(String::compareTo);
                    break;
                  case "Orte-Von-Interesse":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesOrteVonInteresse.add(parts[4]);
                    BlitzerWarner.NaviNamesOrteVonInteresse.sort(String::compareTo);
                    break;
                  case "Stadtgebiet-Hilfe":
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNamesStadtgebietHilfe.add(parts[4]);
                    BlitzerWarner.NaviNamesStadtgebietHilfe.sort(String::compareTo);
                    break;
                  default:
                    BlitzerWarner.Navi.add(navi);
                    BlitzerWarner.NaviNames.add(parts[4]);
                    BlitzerWarner.NaviNames.sort(String::compareTo);
                    break;
                }


              });
            }
          }
        }));
  }
  public static void toggleNotification(BlitzerWarner addon){
    Boolean soundStatus = addon.configuration().all().get();
    Component message = text()
        .append(addon.configuration().prefix())
        .append(
            Component.translatable("blitzerwarner.keys.message",
                NamedTextColor.GRAY,
                soundStatus.equals(true) ? Component.translatable("blitzerwarner.keys.notactive", NamedTextColor.RED) : Component.translatable("blitzerwarner.keys.active", NamedTextColor.GREEN))
        )
        .build();
    addon.configuration().all().set(!soundStatus);
    addon.labyAPI().minecraft().sounds()
        .playSound(ResourceLocation.create("minecraft", "block.note.bell"), 1f,
            addon.configuration().lautstaerke().get());
    addon.displayMessage(message);

  }

  public static void sendBlitzer(Blitzer blitzer, BlitzerWarner addon) {
    JsonObject data = new JsonObject();
    data.addProperty("x", blitzer.getX());
    data.addProperty("y", blitzer.getY());
    data.addProperty("z", blitzer.getZ());
    data.addProperty("gebiet", blitzer.getGebiet());
    data.addProperty("geschwindigkeit", blitzer.getGeschwindigkeit());
    Request.ofGson(JsonObject.class)
        .url("http://85.117.241.176:8989/blitzer")
        .method(Method.POST)
        .async()
        .handleErrorStream()
        .json(data)
        .execute((jsonObjectResponse -> {
          Component message = text()
              .append(addon.configuration().prefix())
              .append(Component.translatable("blitzerwarner.messages.sendsuccess", NamedTextColor.GREEN))
              .build();
          addon.displayMessage(message);

        }));
  }
  public static Navigation getLoc(String name){
    for (int i = 0; i < BlitzerWarner.Navi.size(); i++) {
      if (BlitzerWarner.Navi.get(i).getName().equals(name)){
        return BlitzerWarner.Navi.get(i);
      }
    }
    return null;
  }
  public static int berechneDistanz(Location loc1, Location loc2) {
    int x1 = loc1.getX(); int y1 = loc1.getY(); int z1 = loc1.getZ();
    int x2 = loc2.getX(); int y2 = loc2.getY(); int z2 = loc2.getZ();
    // Berechnung mit der 3D-Distanzformel und Umwandlung in int
    return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow((z2 - z1), 2));
  }
}