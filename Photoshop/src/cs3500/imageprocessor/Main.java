package cs3500.imageprocessor;

import cs3500.imageprocessor.model.filetype.FileType;
import cs3500.imageprocessor.model.filetype.JPEGDealer;
import cs3500.imageprocessor.model.filetype.PNGDealer;
import cs3500.imageprocessor.model.filetype.PPMDealer;
import cs3500.imageprocessor.model.ImageApplications;
import cs3500.imageprocessor.model.ImageModel;
import cs3500.imageprocessor.model.ImageModelImpl;
import java.io.File;
import java.io.IOException;


/**
 * Class for running code.
 */
public class Main {

  /**
   * Method for running code.
   *
   * @param args Arguments
   */
  public static void main(String[] args) throws IOException {
    FileType ppm = new PPMDealer();
    ImageModel model = new ImageModelImpl();
    ImageApplications image = model.importAs("Balloon.jpg", new PNGDealer());
    model.addImage("Balloon.jpg", image);
    model.sharpen("Balloon.jpg");
    File file = new File("test directory");
    file.mkdir();
    model.exportAs("Balloon.jpg", "test directory/sharp balloon.jpg", new JPEGDealer());

  }
}
