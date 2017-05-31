package com.taxis.reactive.agents;

import static com.taxis.reactive.agents.TaxiState.EN_ROUTE_TO_PASSENGER;
import static com.taxis.reactive.agents.TaxiState.FREE;
import static com.taxis.reactive.agents.TaxiState.OCCUPIED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import com.overload.loc.Locatable;
import com.overload.loc.Node;
import com.taxis.reactive.stage.Stage;

public class Taxi implements Agent {

  private String id;
  private Locatable location;
  private TaxiState state;
  private Passenger passenger;
  private Queue<Locatable> path;
  private Stage stage;

  public Taxi(Locatable location, Stage stage) {
    this.id = UUID.randomUUID().toString();
    this.location = location;
    this.state = FREE;
    this.path = new LinkedList<>();
    this.stage = stage;
  }

  @Override
  public void performStep() {
    switch (state) {
      case FREE:
        performFreeStep();
        break;
      case EN_ROUTE_TO_PASSENGER:
        performEnRouteStep();
        break;
      case OCCUPIED:
        performOccupiedStep();
        break;
    }
    move();
  }

  private void move() {
    if (!path.isEmpty()) {
      location = path.poll();
    }
  }

  private void moveTo(Locatable location) {
    this.location = location;
  }

  private void performFreeStep() {
    if (path.isEmpty()) {
      moveToRandomNeighbor();
    }
  }

  private void performEnRouteStep() {
    if (location.equals(passenger.getLocation())) {
      drivePassenger(passenger);
      stage.removePassenger(passenger);
    }
  }

  private void performOccupiedStep() {
    if (location.equals(passenger.getDestination())) {
      dropoffPassenger();
    }
  }

  private void dropoffPassenger() {
    this.passenger = null;
    this.path.clear();
    this.state = FREE;
  }

  private void moveToRandomNeighbor() {
    getShuffledNeighbors(location).stream()
        .filter(l -> stage.getGrid().isLocationWalkable(l))
        .findFirst()
        .ifPresent(this::moveTo);
  }

  /**
   * This was done like this to have a more 'organic' feel to
   * the way drivers are riding when they are free
   */
  private List<Locatable> getShuffledNeighbors(Locatable location) {
    List<Locatable> neighbors = new ArrayList<>();
    int x = location.getX();
    int y = location.getY();

    neighbors.add(new Node(x - 1, y));
    neighbors.add(new Node(x + 1, y));
    neighbors.add(new Node(x, y - 1));
    neighbors.add(new Node(x, y + 1));

    Collections.shuffle(neighbors);
    return neighbors;
  }

  public TaxiState getTaxiState() {
    return state;
  }

  @Override
  public char getState() {
    return state.getValue();
  }

  public void pickupPassenger(Passenger passenger, List<Locatable> path) {
    this.state = EN_ROUTE_TO_PASSENGER;
    this.passenger = passenger;
    this.path = new LinkedList<>(path);
  }

  public void drivePassenger(Passenger passenger) {
    this.state = OCCUPIED;
    this.path = new LinkedList<>(passenger.getPath());
  }

  public boolean isFree() {
    return FREE.equals(state);
  }

  public Locatable getLocation() {
    return location;
  }

  public Passenger getPassenger() {
    return passenger;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof Taxi)) { return false; }

    Taxi taxi = (Taxi) o;

    return id != null ? id.equals(taxi.id) : taxi.id == null;

  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
