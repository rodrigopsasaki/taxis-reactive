package com.taxis.reactive.rest.model;

import com.overload.loc.Locatable;

public class Coordinate implements Locatable {

  private int x;
  private int y;

  public Coordinate() {}

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}
