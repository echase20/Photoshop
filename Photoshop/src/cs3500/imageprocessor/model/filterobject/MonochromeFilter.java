package cs3500.imageprocessor.model.filterobject;

import cs3500.imageprocessor.model.filterobject.FilterObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class for turning images into completely greyscale.
 */
public class MonochromeFilter implements FilterObject {

  @Override
  public ArrayList<ArrayList<Double>> getMatrix() {
    ArrayList<Double> row = new ArrayList<>(Arrays.asList(0.2126, 0.7251, 0.0722));
    ArrayList<ArrayList<Double>> greyscaleMatrix = new ArrayList<>(
        Arrays.asList(row, row, row));
    return greyscaleMatrix;
  }

}
