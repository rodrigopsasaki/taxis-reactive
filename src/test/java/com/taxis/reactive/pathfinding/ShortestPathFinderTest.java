package com.taxis.reactive.pathfinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.overload.loc.Locatable;
import com.overload.loc.Node;
import com.taxis.reactive.parser.CSVParser;
import com.taxis.reactive.service.Grid;

public class ShortestPathFinderTest {

  @Test
  public void findPathInSingleLineWithoutBlocks() {
    Grid grid = new Grid(CSVParser.parseCSV(",,"));
    ShortestPathFinder pathfinder = new ShortestPathFinder(grid);

    List<Locatable> path = pathfinder.shortestPath(node(0, 0), node(0, 2));

    assertEquals(3, path.size());

    assertEquals(0, path.get(0).getX());
    assertEquals(0, path.get(0).getY());

    assertEquals(0, path.get(1).getX());
    assertEquals(1, path.get(1).getY());

    assertEquals(0, path.get(2).getX());
    assertEquals(2, path.get(2).getY());
  }

  @Test
  public void findPathInThreeByThreeWithoutBlocks() {
    Grid grid = new Grid(CSVParser.parseCSV(",,\n,,\n,,"));
    ShortestPathFinder pathfinder = new ShortestPathFinder(grid);

    List<Locatable> path = pathfinder.shortestPath(node(0, 0), node(2, 2));

    assertEquals(5, path.size());

    assertEquals(0, path.get(0).getX());
    assertEquals(0, path.get(0).getY());

    assertEquals(1, path.get(1).getX());
    assertEquals(0, path.get(1).getY());

    assertEquals(1, path.get(2).getX());
    assertEquals(1, path.get(2).getY());

    assertEquals(1, path.get(3).getX());
    assertEquals(2, path.get(3).getY());

    assertEquals(2, path.get(4).getX());
    assertEquals(2, path.get(4).getY());
  }

  @Test
  public void findPathInThreeByThreeWithBlocks() {
    Grid grid = new Grid(CSVParser.parseCSV(",,x\n,x,x\n,,"));
    ShortestPathFinder pathfinder = new ShortestPathFinder(grid);

    List<Locatable> path = pathfinder.shortestPath(node(0, 0), node(2, 2));

    assertEquals(5, path.size());

    assertEquals(0, path.get(0).getX());
    assertEquals(0, path.get(0).getY());

    assertEquals(1, path.get(1).getX());
    assertEquals(0, path.get(1).getY());

    assertEquals(2, path.get(2).getX());
    assertEquals(0, path.get(2).getY());

    assertEquals(2, path.get(3).getX());
    assertEquals(1, path.get(3).getY());

    assertEquals(2, path.get(4).getX());
    assertEquals(2, path.get(4).getY());
  }

  @Test
  public void findPathWithUnwalkableTarget() {
    Grid grid = new Grid(CSVParser.parseCSV(",,\n,,\n,,x"));
    ShortestPathFinder pathfinder = new ShortestPathFinder(grid);

    List<Locatable> path = pathfinder.shortestPath(node(0, 0), node(2, 2));

    assertEquals(0, path.size());
  }

  @Test
  public void findPathWithUnpassableObstacle() {
    Grid grid = new Grid(CSVParser.parseCSV(",,x\n,x,\nx,,"));
    ShortestPathFinder pathfinder = new ShortestPathFinder(grid);

    List<Locatable> path = pathfinder.shortestPath(node(0, 0), node(2, 2));

    assertEquals(0, path.size());
  }

  private Node node(int x, int y) {
    return new Node(x, y);
  }

}
