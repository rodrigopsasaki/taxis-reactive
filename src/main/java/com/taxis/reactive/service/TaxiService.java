package com.taxis.reactive.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overload.loc.Locatable;
import com.taxis.reactive.agents.Passenger;
import com.taxis.reactive.agents.Taxi;
import com.taxis.reactive.model.TaxiPath;
import com.taxis.reactive.utils.PathUtils;

@Service("taxiService")
public class TaxiService {

  @Autowired
  private Grid grid;

  private List<Taxi> taxis;

  public TaxiService() {
    taxis = new ArrayList<>();
  }

  public void assignTaxiToPassengers(List<Passenger> passengers) {
    passengers.stream()
        .filter(p -> !p.isAssigned())
        .forEach(this::assignTaxiToPassenger);
  }

  public Optional<TaxiPath> findClosestFreeTaxi(Locatable location) {
    return taxis.stream()
        .filter(Taxi::isFree)
        .map(t -> new TaxiPath(t, PathUtils.shortestPath(grid, t.getLocation(), location)))
        .filter(tp -> !tp.getPath().isEmpty())
        .min(Comparator.comparingInt(TaxiPath::size));
  }

  public void assignTaxiToPassenger(Passenger passenger) {
    Locatable location = passenger.getLocation();
    findClosestFreeTaxi(location).ifPresent(taxiPath -> {
      Taxi taxi = taxiPath.getTaxi();
      taxi.pickupPassenger(passenger, taxiPath.getPath());
      passenger.assign();
    });
  }

  public void addTaxi(Taxi taxi) {
    taxis.add(taxi);
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public void performStep() {
    taxis.forEach(Taxi::performStep);
  }

  public List<Taxi> getTaxis() {
    return taxis;
  }

  public void restart() {
    taxis.clear();
  }
}
