package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.filetype.FileType;
import cs3500.imageprocessor.model.filterobject.MonochromeFilter;
import cs3500.imageprocessor.model.filterobject.SepiaFilter;
import cs3500.imageprocessor.model.imageadjuster.BlurImage;
import cs3500.imageprocessor.model.imageadjuster.SharpenImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * An implementation of the ImageModel interface.
 */
public class ImageModelImpl implements ImageModel {

  private final HashMap<String, ImageApplications> images;

  public ImageModelImpl(HashMap<String, ImageApplications> imageMap) {
    Objects.requireNonNull(imageMap);
    this.images = imageMap;
  }

  public ImageModelImpl() {
    this.images = new HashMap<String, ImageApplications>();
  }

  @Override
  public ImageApplications getImage(String id) {
    try {
      return new ImageApplicationsImpl(images.get(id).getPixels());
    } catch (Exception e) {
      throw new IllegalArgumentException("There is no image with that id.");
    }
  }

  @Override
  public void addImage(String id, ImageApplications image) {
    images.put(id, image);
  }

  @Override
  public void removeImage(String id) {
    images.remove(id);
  }

  @Override
  public void blur(String id) {
    try {
      images.get(id).adjust(new BlurImage());
    } catch (Exception e) {
      throw new IllegalArgumentException("Blur failed. No image with that id.");
    }
  }

  @Override
  public void sharpen(String id) {
    try {
      images.get(id).adjust(new SharpenImage());
    } catch (Exception e) {
      throw new IllegalArgumentException("Sharpen failed. No image with that id.");
    }
  }

  @Override
  public void monochrome(String id) {
    try {
      images.get(id).filter(new MonochromeFilter());
    } catch (Exception e) {
      throw new IllegalArgumentException("Monochrome filter failed. No image with that id.");
    }
  }

  @Override
  public void sepia(String id) {
    try {
      images.get(id).filter(new SepiaFilter());
    } catch (Exception e) {
      throw new IllegalArgumentException("Sepia filter failed. No image with that id.");
    }
  }

  @Override
  public ImageApplications importAs(String fileName, FileType fileType) throws IOException {
    ImageUtil u = new ImageUtil();
    ImageApplications image = fileType.readImage(fileName, fileType);
    return image;
  }

  @Override
  public void exportAs(String id, String fileName, FileType fileType) {
    try {
      images.get(id).exportAs(fileName, fileType);
    } catch (NullPointerException | IOException e) {
      throw new IllegalArgumentException("File: " + fileName + " could not be exported");
    }
  }
}
