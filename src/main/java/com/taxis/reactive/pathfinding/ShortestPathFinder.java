package com.taxis.reactive.pathfinding;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.overload.algorithms.pathfinding.Pathfinder;
import com.overload.loc.Locatable;
import com.taxis.reactive.model.GridFlags;
import com.taxis.reactive.service.Grid;

public class ShortestPathFinder {

  private Grid grid;

  public ShortestPathFinder(Grid grid) {
    this.grid = grid;
  }

  public List<Locatable> shortestPath(Locatable a, Locatable b) {
    final Pathfinder pathfinder = new Pathfinder(Pathfinder.Algorithm.ASTAR, new GridFlags(grid));
    return Optional.ofNullable(pathfinder.findPath(a, b))
        .orElse(Collections.emptyList());
  }

}
