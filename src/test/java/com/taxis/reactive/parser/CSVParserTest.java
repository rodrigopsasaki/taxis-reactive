package com.taxis.reactive.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.overload.loc.Node;
import com.taxis.reactive.service.Grid;


public class CSVParserTest {

  @Test
  public void testTwoByTwoGridWithoutBlocks() {
    String value = ",\n,";

    Grid grid = new Grid(CSVParser.parseCSV(value));

    assertTrue(grid.isLocationWalkable(new Node(0, 0)));
    assertTrue(grid.isLocationWalkable(new Node(0, 1)));
    assertTrue(grid.isLocationWalkable(new Node(1, 0)));
    assertTrue(grid.isLocationWalkable(new Node(1, 1)));
    assertEquals(4, grid.size());
  }

  @Test
  public void testTwoByTwoGridWithBlocks() {
    String value = ",\n,x";

    Grid grid = new Grid(CSVParser.parseCSV(value));

    assertTrue(grid.isLocationWalkable(new Node(0, 0)));
    assertTrue(grid.isLocationWalkable(new Node(0, 1)));
    assertTrue(grid.isLocationWalkable(new Node(1, 0)));
    assertFalse(grid.isLocationWalkable(new Node(1, 1)));
    assertEquals(4, grid.size());
  }

}
