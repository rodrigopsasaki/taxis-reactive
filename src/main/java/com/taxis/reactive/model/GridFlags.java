package com.taxis.reactive.model;

import com.overload.algorithms.pathfinding.Pathfinder;
import com.overload.loc.Locatable;
import com.overload.loc.Node;
import com.taxis.reactive.service.Grid;

public class GridFlags implements Pathfinder.Flags {

  private Grid grid;

  public GridFlags(Grid grid) {
    this.grid = grid;
  }

  @Override
  public boolean blocked(Locatable location, Locatable parent) {
    return !grid.isLocationWalkable(location);
  }

  public boolean blocked(int x, int y) {
    return blocked(new Node(x, y), null);
  }

}
