package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.filetype.FileType;
import cs3500.imageprocessor.model.filterobject.FilterObject;
import cs3500.imageprocessor.model.imageadjuster.ImageAdjuster;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for an image processor model.
 */
public interface ImageApplications {


  /**
   * Applies the given kernel to this image.
   * @param adjust ImageAdjuster whose kernel will be used to transform this image
   */
  void adjust(ImageAdjuster adjust);

  /**
   * Applies the given filter to this image.
   *
   * @param filter FilterObject whose matrix will be used to transform this Image.
   */
  void filter(FilterObject filter);

  //ImageApplications createImage();

  /**
   * Exports this image as a file.
   * @param fileName The name the file will be saved as
   */
  void exportAs(String fileName, FileType fileType) throws IOException;

  /**
   * Returns the pixels this image represents as rows, columns, and rgb values.
   *
   * @return An outer ArrayList containing each row of Pixels.
   */
  ArrayList<ArrayList<ArrayList<Double>>> getPixels();
}
