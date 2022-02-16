package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.filetype.FileType;
import java.io.IOException;

/**
 * An interface for storing and manipulating multiple ImageApplications objects.
 */
public interface ImageModel {

  /**
   * Returns a new image constructed from the pixels of the image retrieved.
   *
   * @param id The key for the desired Image
   * @return A new Image with the same pixels as the image locked by the id
   */
  ImageApplications getImage(String id);

  /**
   * Adds a new image to this models images.
   *
   * @param id    The id that the image will have in this model
   * @param image The image to be added
   */
  void addImage(String id, ImageApplications image);

  /**
   * Removes an image from the model.
   *
   * @param id The id of the image to be removed
   */
  void removeImage(String id);

  /**
   * Blurs the image with the given id.
   *
   * @param id The id of the image to be blurred
   */
  void blur(String id);

  /**
   * Sharpens the image with the given id.
   *
   * @param id The id of the image to be sharpened
   */
  void sharpen(String id);

  /**
   * Applies a Monochrome filter to the image with the given id.
   *
   * @param id The id of the image to be turned greyscale
   */
  void monochrome(String id);

  /**
   * Applies a Sepia filter to the image with the given id.
   *
   * @param id The id of the image to be sepiad
   */
  void sepia(String id);

  /**
   * Imports the given file as the type of specified image.
   * @param filename the name of the file
   */
  ImageApplications importAs(String filename, FileType fileType) throws IOException;

  /**
   * Exports the image with the given id with the given fileName as the given filetype.
   * @param id id of the image
   * @param fileName name of the file
   */
  void exportAs(String id, String fileName, FileType fileType);


}
