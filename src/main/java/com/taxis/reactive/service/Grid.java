package com.taxis.reactive.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.overload.loc.Locatable;
import com.taxis.reactive.model.GridPosition;
import com.taxis.reactive.parser.CSVParser;

@Service("grid")
public class Grid {

  private Map<String, GridPosition> positionsMap;
  private List<GridPosition> positionsList;

  public Grid() {
    initialize(CSVParser.defaultGridPositions());
  }

  public Grid(final List<GridPosition> positions) {
    initialize(positions);
  }

  public void initialize(final List<GridPosition> positions) {
    this.positionsList = positions.stream().sorted().collect(toList());
    this.positionsMap = positionsList.stream().collect(toMap(GridPosition::getCoordinate, identity()));
  }

  public boolean isLocationWalkable(Locatable loc) {
    return getPosition(loc).filter(GridPosition::isWalkable).isPresent();
  }

  public Optional<GridPosition> getPosition(Locatable loc) {
    return getPosition(loc.getX(), loc.getY());
  }

  public Optional<GridPosition> getPosition(int x, int y) {
    return getPosition(String.format("%02d,%02d", x, y));
  }

  public Optional<GridPosition> getPosition(String coordinate) {
    return Optional.ofNullable(positionsMap.get(coordinate));
  }

  public int size() {
    return positionsMap.keySet().size();
  }

  public List<GridPosition> getPositionsList() {
    return positionsList;
  }

  public GridPosition getHighestPosition() {
    return positionsList.get(positionsList.size() - 1);
  }

}
