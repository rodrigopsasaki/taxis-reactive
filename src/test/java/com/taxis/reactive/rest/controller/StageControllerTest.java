package com.taxis.reactive.rest.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import com.taxis.reactive.rest.model.AddPassengerRequest;
import com.taxis.reactive.rest.model.AddTaxiRequest;
import com.taxis.reactive.rest.model.Coordinate;

import reactor.core.publisher.Mono;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StageControllerTest {

  private WebTestClient webTestClient;

  @Before
  public void before() {
    webTestClient = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:8080")
        .build();
  }

  @Test
  public void getCurrentState() throws Exception {

    webTestClient.get()
        .uri("api/state")
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .expectStatus().isOk();

  }

  @Test
  public void moveStep() throws Exception {

    webTestClient.post()
        .uri("api/step")
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .expectStatus().isOk();


  }

  @Test
  public void restart() throws Exception {

    webTestClient.post()
        .uri("api/restart")
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .expectStatus().isOk();

  }

  @Test
  public void addTaxi() throws Exception {
    Coordinate coordinate = new Coordinate(10, 10);
    AddTaxiRequest request = new AddTaxiRequest(coordinate);

    webTestClient.post()
        .uri("api/taxi")
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().isOk();

  }

  @Test
  public void addPassenger() throws Exception {
    Coordinate source = new Coordinate(10, 10);
    Coordinate destination = new Coordinate(11, 11);
    AddPassengerRequest request = new AddPassengerRequest(source, destination);

    webTestClient.post()
        .uri("api/passenger")
        .body(BodyInserters.fromObject(request))
        .exchange()
        .expectStatus().isOk();

  }

}
