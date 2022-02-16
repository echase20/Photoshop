package cs3500.imageprocessor.model.imageadjuster;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that is meant to store and return a kernel for blurring an image.
 */
public class BlurImage implements ImageAdjuster {


  @Override
  public ArrayList<ArrayList<Double>> getKernel() {
    return new ArrayList<ArrayList<Double>>(
        Arrays.asList(new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625)),
            new ArrayList<Double>(Arrays.asList(0.125, 0.25, 0.125)),
            new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625))));
  }
}
