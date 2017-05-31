package com.taxis.reactive.stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.overload.loc.Node;
import com.taxis.reactive.agents.Passenger;
import com.taxis.reactive.agents.Taxi;
import com.taxis.reactive.agents.TaxiState;
import com.taxis.reactive.utils.PathUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StageTest {

  @Autowired
  private Stage stage;

  @Before
  public void setup() {
    stage.restart();
  }

  @Test
  public void testPickupPassenger() throws Exception {
    Passenger passenger = new Passenger(new Node(15, 7), new Node(18, 12));
    passenger.setPath(PathUtils.shortestPath(stage.getGrid(), passenger.getLocation(), passenger.getDestination()));
    Taxi taxi = new Taxi(new Node(17, 6), stage);

    stage.addPassenger(passenger);
    stage.addTaxi(taxi);

    assertFalse(passenger.isAssigned());
    assertEquals(TaxiState.FREE, taxi.getTaxiState());

    stage.nextStep();

    assertTrue(passenger.isAssigned());
    assertEquals(TaxiState.EN_ROUTE_TO_PASSENGER, taxi.getTaxiState());
    assertEquals(17, taxi.getLocation().getX());
    assertEquals(6, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(16, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(TaxiState.EN_ROUTE_TO_PASSENGER, taxi.getTaxiState());
    assertEquals(15, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(TaxiState.OCCUPIED, taxi.getTaxiState());
    assertEquals(15, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(16, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(7, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(8, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(9, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(10, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(17, taxi.getLocation().getX());
    assertEquals(11, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(18, taxi.getLocation().getX());
    assertEquals(11, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(TaxiState.OCCUPIED, taxi.getTaxiState());
    assertEquals(18, taxi.getLocation().getX());
    assertEquals(12, taxi.getLocation().getY());

    stage.nextStep();

    assertEquals(TaxiState.FREE, taxi.getTaxiState());

  }

}
