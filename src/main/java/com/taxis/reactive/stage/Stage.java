package com.taxis.reactive.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.overload.loc.Locatable;
import com.taxis.reactive.agents.Passenger;
import com.taxis.reactive.agents.Taxi;
import com.taxis.reactive.exception.InvalidCoordinateException;
import com.taxis.reactive.exception.UnreachableDestinationException;
import com.taxis.reactive.service.Grid;
import com.taxis.reactive.service.PassengerService;
import com.taxis.reactive.service.TaxiService;
import com.taxis.reactive.utils.ErrorMessage;
import com.taxis.reactive.utils.PathUtils;
import com.taxis.reactive.utils.ValidationUtils;
import com.taxis.reactive.writer.StageWriter;

@Service
public class Stage {

  @Autowired
  private TaxiService taxiService;

  @Autowired
  private PassengerService passengerService;

  @Autowired
  private Grid grid;

  public Stage nextStep() {
    if (passengerService.hasPassenger()) {
      taxiService.assignTaxiToPassengers(passengerService.getPassengers());
    }
    taxiService.performStep();
    return this;
  }

  public Stage restart() {
    taxiService.restart();
    passengerService.restart();
    return this;
  }

  public List<Taxi> getTaxis() {
    return taxiService.getTaxis();
  }

  public List<Passenger> getPassengers() {
    return passengerService.getPassengers();
  }

  public Stage addTaxi(Taxi taxi) throws InvalidCoordinateException {
    ValidationUtils.validateCoordinates(this, taxi.getLocation());

    taxiService.addTaxi(taxi);
    return this;
  }

  public Stage addPassenger(Passenger passenger) throws UnreachableDestinationException, InvalidCoordinateException {
    ValidationUtils.validateCoordinates(this, passenger.getLocation());
    ValidationUtils.validateCoordinates(this, passenger.getDestination());

    List<Locatable> path = PathUtils.shortestPath(grid, passenger.getLocation(), passenger.getDestination());
    if (!path.isEmpty()) {
      passenger.setPath(path);
      passengerService.addPassenger(passenger);
      return this;
    }
    throw new UnreachableDestinationException(ErrorMessage.UNREACHABLE_DESTINATION);
  }

  public void removePassenger(Passenger passenger) {
    passengerService.removePassenger(passenger);
  }

  public Grid getGrid() {
    return grid;
  }

  @Override
  public String toString() {
    return StageWriter.toString(this);
  }

  public String toHtml() {
    return StageWriter.toHtml(this);
  }

}
