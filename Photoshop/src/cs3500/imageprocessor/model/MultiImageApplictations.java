package cs3500.imageprocessor.model;


/**
 * interface to represent multi-layered images.
 */
public interface MultiImageApplictations extends ImageApplications {


  /**
   * Adds the given image as a new layer at the bottom of a multilayered image.
   * @param image the image to add
   */
  void addLayer(ImageApplicationsImpl image);

  /**
   * Removes the layer at the given index.
   * @param index the image to remove
   */
  void removeLayer(int index);

  /**
   * Returns the Image stored at the layer index.
   * @param index
   * @return the index of the lyaer
   */
  ImageApplicationsImpl getLayer(int index);

  /**
   * Specifies the current layer to the given index.
   * @param layer the index of the layer
   */
  void setCurrentLayer(int layer);

}
