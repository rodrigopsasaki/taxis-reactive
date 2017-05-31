package com.taxis.reactive.utils;

import java.util.List;

import com.overload.loc.Locatable;
import com.taxis.reactive.pathfinding.ShortestPathFinder;
import com.taxis.reactive.service.Grid;

public final class PathUtils {

  private PathUtils() {
    // utility class
  }

  public static List<Locatable> shortestPath(Grid grid, Locatable a, Locatable b) {
    return new ShortestPathFinder(grid).shortestPath(a, b);
  }

}
