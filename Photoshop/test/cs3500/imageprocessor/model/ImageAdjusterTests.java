package cs3500.imageprocessor.model;

import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.model.imageadjuster.BlurImage;
import cs3500.imageprocessor.model.imageadjuster.ImageAdjuster;
import cs3500.imageprocessor.model.imageadjuster.SharpenImage;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the functionality of Image Adjuster objects.
 */
public class ImageAdjusterTests {

  @Test
  public void testSharpenKernel() {
    ImageAdjuster sharp = new SharpenImage();
    ArrayList<ArrayList<Double>> kernel = sharp.getKernel();
    assertEquals(kernel.toString(),
        "[[-0.125, -0.125, -0.125, -0.125, -0.125], [-0.125, 0.25, 0.25, 0.25, -0.125],"
            + " [-0.125, 0.25, 1.0, 0.25, -0.125], [-0.125, 0.25, 0.25, 0.25, -0.125], [-0.125,"
            + " -0.125, -0.125, -0.125, -0.125]]");
  }

  @Test
  public void testBlurKernel() {
    ImageAdjuster blur = new BlurImage();
    ArrayList<ArrayList<Double>> kernel = blur.getKernel();
    assertEquals(kernel.toString(),
        "[[0.0625, 0.125, 0.0625], [0.125, 0.25, 0.125], [0.0625, 0.125, 0.0625]]");
  }

}
