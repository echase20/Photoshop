package cs3500.imageprocessor.model.filterobject;

import java.util.ArrayList;

/**
 * An interface for classes that transform every pixel in an image. Instead of
 * keeping an apply method here, since different Images might have different representation
 * of pixels, simply return the filter's corresponding matrix, since that will always be
 * useful for any image implementation.
 */
public interface FilterObject {

  /**
   * Returns the transformation matrix corresponding to this filter.
   *
   */
  ArrayList<ArrayList<Double>> getMatrix();

}
