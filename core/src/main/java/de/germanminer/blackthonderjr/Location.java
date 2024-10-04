package de.germanminer.blackthonderjr;

public class Location {
  int x;
  int y;
  int z;
  public Location(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
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
  public String getAsString(){
    return getX() + " " + getY() + " " + getZ();
  }
}
