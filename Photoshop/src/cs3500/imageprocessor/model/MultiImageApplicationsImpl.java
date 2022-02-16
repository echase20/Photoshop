package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.filetype.FileType;
import cs3500.imageprocessor.model.filterobject.FilterObject;
import cs3500.imageprocessor.model.imageadjuster.ImageAdjuster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MultiImageApplicationsImpl implements MultiImageApplictations {

  private final ArrayList<ImageApplicationsImpl> layers;
  private int currentLayer;

  /**
   * constructs a MultiImageApplicationsImpl used for representing layered images.
   * @param layers an ArrayList of ImageApplications used to represent layers
   */
  public MultiImageApplicationsImpl(ArrayList<ImageApplicationsImpl> layers) {
    Objects.requireNonNull(layers);
    for (int i = 0; i < layers.size(); i++) {
      Objects.requireNonNull(layers.get(i));
    }
    this.layers = layers;
    this.currentLayer = 0;
  }

  public MultiImageApplicationsImpl() {
    this(new ArrayList<>());
  }

  @Override
  public void adjust(ImageAdjuster adjust) {
    layers.get(0).adjust(adjust);
  }

  @Override
  public void filter(FilterObject filter) {
    layers.get(0).filter(filter);
  }

  @Override
  public void exportAs(String folderName, FileType fileType) throws IOException {
    File folder = new File(folderName);
    folder.mkdir();
    for (int i = 0; i <= layers.size(); i++) {
      layers.get(i).exportAs((folderName + "/" + i), fileType);
    }
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Double>>> getPixels() {
    return null;
  }

  //files can be added as a layer by creating a new ImageModel Object and adding
  //the result of that model's import method
  @Override
  public void addLayer(ImageApplicationsImpl image) {
    layers.add(image);
  }

  @Override
  public void removeLayer(int index) {
    layers.remove(index);
  }

  @Override
  public ImageApplicationsImpl getLayer(int index) {
    return layers.get(index);
  }

  @Override
  public void setCurrentLayer(int layer) {
    currentLayer = layer;
  }
}
