package com.taxis.reactive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.taxis.reactive.model.GridFlags;
import com.taxis.reactive.parser.CSVParser;
import com.taxis.reactive.service.Grid;

public class GridFlagsTest {

  @Test
  public void testIdentifiesBlocksCorrectly() {
    Grid grid = new Grid(CSVParser.parseCSV("x,\n,x"));
    GridFlags flags = new GridFlags(grid);

    assertTrue(flags.blocked(0, 0));
    assertFalse(flags.blocked(0, 1));
    assertFalse(flags.blocked(1, 0));
    assertTrue(flags.blocked(1, 1));
  }

}
