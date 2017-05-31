package com.taxis.reactive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taxis.reactive.agents.Passenger;

@Service("passengerService")
public class PassengerService {

  private List<Passenger> passengers = new ArrayList<>();

  public void addPassenger(Passenger passenger) {
    passengers.add(passenger);
  }

  public void removePassenger(Passenger passenger) {
    passengers.remove(passenger);
  }

  public boolean hasPassenger() {
    return !passengers.isEmpty();
  }

  public List<Passenger> getPassengers() {
    return passengers;
  }

  public void restart() {
    passengers.clear();
  }
}
