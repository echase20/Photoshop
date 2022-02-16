package cs3500.imageprocessor.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing proper functionality of the MatrixVectorOperationImpl class.
 */
public class MatrixVectorOperationImplTest {

  MatrixVectorOperations m = new MatrixVectorOperationImpl();
  ArrayList<Double> identityRow1 = new ArrayList<>(Arrays.asList(1.0, 0.0, 0.0));
  ArrayList<Double> identityRow2 = new ArrayList<>(Arrays.asList(0.0, 1.0, 0.0));
  ArrayList<Double> identityRow3 = new ArrayList<>(Arrays.asList(0.0, 0.0, 1.0));
  ArrayList<ArrayList<Double>> identity = new ArrayList<>();
  ArrayList<Double> vector362 = new ArrayList<>(Arrays.asList(3.0, 6.0, 2.0));
  ArrayList<ArrayList<Double>> emptyMatrix = new ArrayList<ArrayList<Double>>();
  ArrayList<Double> product = new ArrayList<Double>();
  ArrayList<Double> emptyVector = new ArrayList<Double>();

  @Before
  public void setUp() {
    m = new MatrixVectorOperationImpl();

    identityRow1 = new ArrayList<>(Arrays.asList(1.0, 0.0, 0.0));
    identityRow2 = new ArrayList<>(Arrays.asList(0.0, 1.0, 0.0));
    identityRow3 = new ArrayList<>(Arrays.asList(0.0, 0.0, 1.0));
    identity = new ArrayList<>();
    identity.add(identityRow1);
    identity.add(identityRow2);
    identity.add(identityRow3);

    vector362 = new ArrayList<>(Arrays.asList(3.0, 6.0, 2.0));

    emptyMatrix = new ArrayList<ArrayList<Double>>();
    emptyVector = new ArrayList<Double>();
    product = new ArrayList<Double>();
  }

  @Test
  public void testProductMatrixVectorIdentity() {
    product = m.productMatrixVector(identity, vector362);
    assertEquals(product, vector362);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testProductMatrixVectorEmptyMatrix() {
    product = m.productMatrixVector(emptyMatrix, vector362);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testProductMatrixVectorEmptyMatrixRow() {
    emptyMatrix.add(new ArrayList<Double>());
    emptyMatrix.add(vector362);
    emptyMatrix.add(vector362);
    assertEquals(emptyMatrix.size(), 3);
    m.productMatrixVector(emptyMatrix, vector362);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testProductMatrixVectorEmptyVector() {
    assertEquals(identity.size(), 3);
    assertEquals(emptyVector.size(), 0);
    m.productMatrixVector(identity, emptyVector);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testProductMatrixVectorNonvalidMatrix() {
    identity.get(0).add(1341245.515425); //first row has four numbers, the rest have 3
    assertEquals(identity.size(), vector362.size());
    m.productMatrixVector(identity, vector362);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testProductMatrixVectorShapeMismatch() {
    vector362.add(30.8);
    assertNotEquals(identity.get(0).size(), vector362.size());
    m.productMatrixVector(identity, vector362);
  }
}