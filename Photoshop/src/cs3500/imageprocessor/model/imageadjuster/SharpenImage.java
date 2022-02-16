package cs3500.imageprocessor.model.imageadjuster;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class for returning a kernel capable of sharpening the edges of an images.
 */
public class SharpenImage implements ImageAdjuster {

  @Override
  public ArrayList<ArrayList<Double>> getKernel() {
    ArrayList<Double> row1 = new ArrayList<>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));
    ArrayList<Double> row2 = new ArrayList<>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    ArrayList<Double> row3 = new ArrayList<>(Arrays.asList(-0.125, 0.25, 1.0, 0.25, -0.125));
    ArrayList<Double> row4 = new ArrayList<>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    ArrayList<Double> row5 = new ArrayList<>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));
    ArrayList<ArrayList<Double>> kernel = new ArrayList<>(
        Arrays.asList(row1, row2, row3, row4, row5));
    return kernel;
  }
}
