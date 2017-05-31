package com.taxis.reactive.agents;

import java.util.List;
import java.util.UUID;

import com.overload.loc.Locatable;

public class Passenger implements Agent {

  private static final char PASSENGER_STATE = 'P';

  private String id;
  private Locatable location;
  private Locatable destination;
  private List<Locatable> path;
  private boolean assigned;

  public Passenger(Locatable location, Locatable destination) {
    this.id = UUID.randomUUID().toString();
    this.location = location;
    this.destination = destination;
  }

  @Override
  public void performStep() {
    // passive agent
  }

  @Override
  public char getState() {
    return PASSENGER_STATE;
  }

  public Locatable getLocation() {
    return location;
  }

  public Locatable getDestination() {
    return destination;
  }

  public List<Locatable> getPath() {
    return path;
  }

  public void setPath(List<Locatable> path) {
    this.path = path;
  }

  public boolean isAssigned() {
    return assigned;
  }

  public void assign() {
    assigned = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Passenger)) { return false; }

    Passenger passenger = (Passenger) o;

    return id.equals(passenger.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
