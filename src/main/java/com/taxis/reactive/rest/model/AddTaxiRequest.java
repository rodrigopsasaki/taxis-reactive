package com.taxis.reactive.rest.model;

public class AddTaxiRequest {

  private Coordinate coordinate;

  public AddTaxiRequest() {}

  public AddTaxiRequest(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

}
