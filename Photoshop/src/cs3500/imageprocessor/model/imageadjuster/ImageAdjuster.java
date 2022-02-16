package cs3500.imageprocessor.model.imageadjuster;

import java.util.ArrayList;

/**
 * An interface for objects that will contain kernels for image filtering.
 */
public interface ImageAdjuster {

  /**
   * Returns the kernel this ImageAdujster represents as a 2D arrayList.
   * @return kernel 2D arrayList representing numbers to be multiplied
   *         with corresponding pixel channel values
   */
  ArrayList<ArrayList<Double>> getKernel();
}
