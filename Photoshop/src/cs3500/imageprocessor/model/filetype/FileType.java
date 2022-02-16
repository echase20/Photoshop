package cs3500.imageprocessor.model.filetype;

import cs3500.imageprocessor.model.ImageApplications;
import java.io.IOException;

/**
 * An interface for supporting multiple image formats for importing and exporting.
 */
public interface FileType {

  /**
   * Imports the image with the given file name and given file type.
   * @param fileName name of the file to be read
   * @param fileType the type of image file
   * @return The Image as an ImageApplications Object
   */
  ImageApplications readImage(String fileName, FileType fileType) throws IOException;

  /**
   * Exports the given image with the given name as the specified type.
   * @param image Image to be exported
   * @param fileName Name of the exported file
   */
  void writeImage(ImageApplications image, String fileName) throws IOException;

}
