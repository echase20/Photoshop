package cs3500.imageprocessor.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the functionality of the ImageModel interface and its implementation.
 */
public class ImageModelTest {

  ImageModel model = new ImageModelImpl();
  ImageApplications image = new ImageApplicationsImpl();

  @Before
  public void setup() {
    model = new ImageModelImpl();
    image = new ImageApplicationsImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNonexistentImage() {
    model.getImage("name");
  }

  @Test
  public void testAddImage() {
    model.addImage("image name", image);
    assertEquals(model.getImage("image name").getPixels(),
        image.getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImage() {
    model.addImage("image", image);
    model.removeImage("image");
    model.getImage("image");
  }


}
