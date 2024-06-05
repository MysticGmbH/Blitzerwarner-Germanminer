package de.germanminer.blackthonderjr;

public class Blitzer {
  int x;
  int y;
  int z;
  String gebiet;
  int geschwindigkeit;

  public Blitzer(int x, int y, int z, String gebiet, int geschwindigkeit) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.gebiet = gebiet;
    this.geschwindigkeit = geschwindigkeit;
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

  public int getGeschwindigkeit() {
    return this.geschwindigkeit;
  }
}
