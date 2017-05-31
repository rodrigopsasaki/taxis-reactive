package com.taxis.reactive.model;

import java.util.List;

import com.overload.loc.Locatable;
import com.taxis.reactive.agents.Taxi;

public class TaxiPath {

  private Taxi taxi;
  private List<Locatable> path;

  public TaxiPath(Taxi taxi, List<Locatable> path) {
    this.taxi = taxi;
    this.path = path;
  }

  public Taxi getTaxi() {
    return taxi;
  }

  public List<Locatable> getPath() {
    return path;
  }

  public int size() {
    return path.size();
  }

}
