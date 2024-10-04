package de.germanminer.blackthonderjr;

public class Navigation {
  int x;
  int y;
  int z;
  String gebiet;
  String name;
  String categorie;

  public Navigation(int x, int y, int z, String gebiet, String name, String categorie) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.gebiet = gebiet;
    this.name = name;
    this.categorie = categorie;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }

  public String getGebiet() {
    return this.gebiet;
  }

  public String getName() {
    return name;
  }

  public String getCategorie() {
    return categorie;
  }
}
