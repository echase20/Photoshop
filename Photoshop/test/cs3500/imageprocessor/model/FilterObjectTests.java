package cs3500.imageprocessor.model;

import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.filterobject.MonochromeFilter;
import cs3500.imageprocessor.model.filterobject.SepiaFilter;
import java.util.ArrayList;
import org.junit.Test;

/**
 * A class for testing the values of the transformation matrices corresponding to a Filter Object.
 */
public class FilterObjectTests {

  @Test
  public void testMonochrome() {
    ArrayList<ArrayList<Double>> greytrix = new MonochromeFilter().getMatrix();
    for (int i = 0; i < greytrix.size(); i++) {
      assertEquals(greytrix.get(0).get(i), greytrix.get(1).get(i));
      assertEquals(greytrix.get(0).get(i), greytrix.get(2).get(i));
    }
    assertEquals("0.7251", greytrix.get(0).get(1).toString());
    assertEquals("0.2126", greytrix.get(0).get(0).toString());
    assertEquals("0.0722", greytrix.get(0).get(2).toString());
  }

  @Test
  public void testSepia() {
    ArrayList<ArrayList<Double>> s = new SepiaFilter().getMatrix();
    assertEquals(3, s.size());
    for (int i = 0; i < s.size(); i++) {
      assertEquals(s.get(i).size(), 3);
    }
    assertEquals("0.393", s.get(0).get(0).toString());
    assertEquals("0.769", s.get(0).get(1).toString());
    assertEquals("0.189", s.get(0).get(2).toString());
    assertEquals("0.349", s.get(1).get(0).toString());
    assertEquals("0.686", s.get(1).get(1).toString());
    assertEquals("0.168", s.get(1).get(2).toString());
    assertEquals("0.272", s.get(2).get(0).toString());
    assertEquals("0.534", s.get(2).get(1).toString());
    assertEquals("0.131", s.get(2).get(2).toString());
  }
}
