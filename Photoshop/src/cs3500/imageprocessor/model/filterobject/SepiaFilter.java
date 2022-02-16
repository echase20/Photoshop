package cs3500.imageprocessor.model.filterobject;

import cs3500.imageprocessor.model.filterobject.FilterObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class for turning images into sepia tone.
 */
public class SepiaFilter implements FilterObject {

  private final ArrayList<Double> row1 = new ArrayList<>(Arrays.asList(0.393, 0.769, 0.189));
  private final ArrayList<Double> row2 = new ArrayList<>(Arrays.asList(0.349, 0.686, 0.168));
  private final ArrayList<Double> row3 = new ArrayList<>(Arrays.asList(0.272, 0.534, 0.131));
  private final ArrayList<ArrayList<Double>> sepiaMatrix = new ArrayList<>(
      Arrays.asList(row1, row2, row3));


  @Override
  public ArrayList<ArrayList<Double>> getMatrix() {
    return sepiaMatrix;
  }
}
