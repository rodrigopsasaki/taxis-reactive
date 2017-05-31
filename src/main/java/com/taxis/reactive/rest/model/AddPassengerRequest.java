package com.taxis.reactive.rest.model;

public class AddPassengerRequest {

  private Coordinate source;
  private Coordinate destination;

  public AddPassengerRequest() {}

  public AddPassengerRequest(Coordinate source, Coordinate destination) {
    this.source = source;
    this.destination = destination;
  }

  public Coordinate getSource() {
    return source;
  }

  public void setSource(Coordinate source) {
    this.source = source;
  }

  public Coordinate getDestination() {
    return destination;
  }

  public void setDestination(Coordinate destination) {
    this.destination = destination;
  }

}
