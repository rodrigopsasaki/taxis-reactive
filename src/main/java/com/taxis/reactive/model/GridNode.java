package com.taxis.reactive.model;

public enum GridNode {

  WALKABLE("_"),
  NOT_WALKABLE("x");

  private String constant;

  GridNode(String constant) {
    this.constant = constant;
  }

  public String getConstant() {
    return constant;
  }

}
