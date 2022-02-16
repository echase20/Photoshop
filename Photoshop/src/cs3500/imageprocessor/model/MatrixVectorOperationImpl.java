package cs3500.imageprocessor.model;

import java.util.ArrayList;

/**
 * A basic implementation of the MatrixVectorOperations interface.
 */
public class MatrixVectorOperationImpl implements MatrixVectorOperations {

  @Override
  public ArrayList<Double> productMatrixVector(ArrayList<ArrayList<Double>> matrix,
      ArrayList<Double> vector) {
    //make sure all inputs are not of size 0
    if (matrix.size() == 0 || matrix.get(0).size() == 0 || vector.size() == 0) {
      throw new IllegalArgumentException("Cannot perform matrix multiplication with empty inputs.");
    }
    int matrixWidth = matrix.get(0).size();
    int matrixHeight = matrix.size();
    int vectorHeight = vector.size();
    //make sure matrix is a rectangle
    for (ArrayList<Double> row : matrix) {
      if (row.size() != matrixWidth) {
        throw new IllegalArgumentException("All rows of matrix must be the same size.");
      }
    }
    //make sure each row of the matrix is equal to the height of the vector
    if (matrixWidth != vectorHeight) {
      throw new IllegalArgumentException(String
          .format("Cannot multiply %dx%d matrix and size %d vector", matrixHeight, matrixWidth,
              vectorHeight));
    }

    //multiple each element in vector by each row in matrix
    ArrayList<Double> result = new ArrayList<Double>();
    Double value;
    for (int row = 0; row < matrixHeight; row++) {
      value = 0.0;
      for (int column = 0; column < matrixWidth; column++) {
        value += vector.get(column) * matrix.get(row).get(column);
      }
      result.add(value);
    }

    return result;
  }

}
