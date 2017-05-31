package com.taxis.reactive.utils;

import java.util.Optional;

import com.overload.loc.Locatable;
import com.taxis.reactive.exception.InvalidCoordinateException;
import com.taxis.reactive.model.GridPosition;
import com.taxis.reactive.stage.Stage;

public final class ValidationUtils {

  private ValidationUtils() {
    // utility class
  }

  public static boolean validateCoordinates(Stage stage, Locatable coordinate) throws InvalidCoordinateException {
    Optional<GridPosition> gridPosition = stage.getGrid().getPosition(coordinate.getX(), coordinate.getY());
    if (!gridPosition.isPresent()) {
      throw new InvalidCoordinateException(String.format(ErrorMessage.NON_EXISTENT_COORDINATE, coordinate.getX(), coordinate.getY()));
    } else if (!gridPosition.get().isWalkable()) {
      throw new InvalidCoordinateException(String.format(ErrorMessage.NOT_WALKABLE_COORDINATE, coordinate.getX(), coordinate.getY()));
    }
    return true;
  }

}
