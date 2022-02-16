package cs3500.imageprocessor.model;

import java.util.ArrayList;

/**
 * Interface for performing matrix transformations on a vector.
 */
public interface MatrixVectorOperations {

  /**
   * Returns the product of applying the given matrix transformation to the given vector.
   *
   * @param matrix transformation matrix to be applied
   * @param vector vector to be transformed
   * @return input vector after applying matrix transformation
   * @throws IllegalArgumentException if the dimensions of the matrix and vector don't allow for
   *                                  multiplication
   */
  ArrayList<Double> productMatrixVector(ArrayList<ArrayList<Double>> matrix,
      ArrayList<Double> vector);
}
