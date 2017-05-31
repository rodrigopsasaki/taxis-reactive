package com.taxis.reactive.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.taxis.reactive.model.GridNode;
import com.taxis.reactive.model.GridPosition;

public final class CSVParser {

  private static final Logger LOGGER = Logger.getLogger(CSVParser.class);
  private static final String DEFAULT_GRID_CSV = "defaultGrid.csv";
  private static final String LINE_SEPARATOR = "\n";
  private static final String NODE_SEPARATOR = ",";

  private CSVParser() {
    // utility class
  }

  public static List<GridPosition> defaultGridPositions() {
    try {
      Path csvPath = Paths.get(CSVParser.class.getClassLoader().getResource(DEFAULT_GRID_CSV).toURI());
      return parseCSV(new String(Files.readAllBytes(csvPath)));
    } catch (IOException | URISyntaxException e) {
      LOGGER.error("There was an error reading the default grid csv file", e);
    }
    return Collections.emptyList();
  }

  public static List<GridPosition> parseCSV(String csv) {
    List<GridPosition> gridPositions = new ArrayList<>();
    String[] lines = csv.split(LINE_SEPARATOR);

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
      String[] nodes = line.split(NODE_SEPARATOR, -1);
      for (int j = 0; j < nodes.length; j++) {
        String value = nodes[j];
        gridPositions.add(new GridPosition(i, j, isWalkable(value)));
      }
    }

    return gridPositions;
  }

  private static boolean isWalkable(String value) {
    return !GridNode.NOT_WALKABLE.getConstant().equals(value);
  }

}
