package com.taxis.reactive.rest.controller;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taxis.reactive.agents.Passenger;
import com.taxis.reactive.agents.Taxi;
import com.taxis.reactive.exception.InvalidCoordinateException;
import com.taxis.reactive.exception.UnreachableDestinationException;
import com.taxis.reactive.rest.model.AddPassengerRequest;
import com.taxis.reactive.rest.model.AddTaxiRequest;
import com.taxis.reactive.stage.Stage;

import reactor.core.publisher.Mono;

@RestController
public class StageController {

  @Autowired
  private Stage stage;

  @GetMapping(value = "/api/state", produces = TEXT_HTML_VALUE)
  public Mono<String> getCurrentState() {
    return toMonoStream(stage);
  }

  @PostMapping(value = "/api/step", produces = TEXT_HTML_VALUE)
  public Mono<String> moveStep() {
    return toMonoStream(stage.nextStep());
  }

  @PostMapping(value = "/api/restart", produces = TEXT_HTML_VALUE)
  public Mono<String> restart() {
    return toMonoStream(stage.restart());
  }

  @PostMapping(value = "/api/taxi", produces = TEXT_HTML_VALUE)
  public Mono<String> addTaxi(@RequestBody AddTaxiRequest request) {
    try {
      Taxi taxi = new Taxi(request.getCoordinate(), stage);
      return toMonoStream(stage.addTaxi(taxi));
    } catch (InvalidCoordinateException e) {
      return Mono.just(e.getMessage());
    }
  }

  @PostMapping(value = "/api/passenger", produces = TEXT_HTML_VALUE)
  public Mono<String> addPassenger(@RequestBody AddPassengerRequest request) {
    try {
      Passenger passenger = new Passenger(request.getSource(), request.getDestination());
      return toMonoStream(stage.addPassenger(passenger));
    } catch (UnreachableDestinationException | InvalidCoordinateException e) {
      return Mono.just(e.getMessage());
    }
  }

  private Mono<String> toMonoStream(final Stage stage) {
    return Mono.just(stage.toHtml());
  }

}
