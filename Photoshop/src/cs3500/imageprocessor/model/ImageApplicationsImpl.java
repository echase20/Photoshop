package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.filetype.FileType;
import cs3500.imageprocessor.model.filterobject.FilterObject;
import cs3500.imageprocessor.model.imageadjuster.ImageAdjuster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * An implementation of the ImageApplciations interface that transforms images by turning them into
 * a board of three dimensional color vectors.
 */
public class ImageApplicationsImpl implements ImageApplications {

  private final ArrayList<ArrayList<ArrayList<Double>>> pixels;

  /**
   * Constructs an ImageApplicationsImpl object from a list of pixels.
   *
   * @param pixels The pixels that this object will represent
   */
  public ImageApplicationsImpl(ArrayList<ArrayList<ArrayList<Double>>> pixels) {
    Objects.requireNonNull(pixels);
    this.pixels = pixels; //MAKE SURE everything about the image is solid
  }

  /**
   * Default construct that sets this Objects pixels field to an empty list.
   */
  public ImageApplicationsImpl() {
    this.pixels = new ArrayList<>();
  }

  @Override
  public void adjust(ImageAdjuster adjuster) {
    applyKernel(adjuster.getKernel());
  }

  /**
   * Applies the given kernel on each pixel in this image.
   *
   * @param kernel Kernel that will be used to transform image.
   */
  private void applyKernel(ArrayList<ArrayList<Double>> kernel) {
    kernelCheck(kernel);
    //go through every single pixel and set the rgb values to their new kernel results
    for (int row = 0; row < pixels.size(); row++) {
      for (int column = 0; column < pixels.get(0).size(); column++) {
        for (int channel = 0; channel < 3; channel++) {
          pixels.get(row).get(column).set(channel, kernelUpdate(row, column, channel, kernel));
        }
      }
    }
  }

  /**
   * Updates the pixel at the given row and column using the given kernel.
   *
   * @param row    The row of the pixel being updated
   * @param column The column of the pixel being updated
   * @param kernel The odd dimensions kernel that will be applied to a pixel
   * @return the new value the channel should store
   */
  private Double kernelUpdate(int row, int column, int channel,
      ArrayList<ArrayList<Double>> kernel) {
    Double result = 0.0;
    int center = kernel.size() / 2;
    for (int kernelRow = 0; kernelRow < kernel.size(); kernelRow++) {
      // when looking at the center of a kernel(kernelRow = center), imageRow should be the same as
      // the row we passed in. The same applied for the kernel and image column
      int imageRow = row + kernelRow - center;
      for (int kernelCol = 0; kernelCol < kernel.get(0).size(); kernelCol++) {
        int imageCol = column + kernelCol - center;
        if (imageRow < 0 || imageRow >= pixels.size() || imageCol < 0 || imageCol >= pixels.get(0)
            .size()) {
          result += 0;
        } else {
          result += kernel.get(kernelRow).get(kernelCol) * pixels.get(imageRow).get(imageCol)
              .get(channel);
        }
      }
    }
    return result;
  }

  /**
   * Ensure that the given kernel is valid.
   *
   * @param kernel 2D Arraylist to be validated
   * @throws IllegalArgumentException if the kernel is not square, and is not odd length
   */
  private void kernelCheck(ArrayList<ArrayList<Double>> kernel) {
    if (kernel.size() == 0) {
      throw new IllegalArgumentException("Empty kernel");
    }
    if (kernel.size() % 2 != 1) {
      throw new IllegalArgumentException("Kernel dimensions must be odd in length");
    }
    if (kernel.get(0).size() != kernel.size()) {
      throw new IllegalArgumentException("Kernel is not square");
    }
    for (int i = 0; i < kernel.size(); i++) {
      if (kernel.get(i).size() != kernel.get(0).size()) {
        throw new IllegalArgumentException("Each kernel row must be same length");
      }
    }
  }


  @Override
  public void filter(FilterObject filter) {
    applyFilter(filter.getMatrix());
  }

  /**
   * Performs the transformation matrix on each vector pixel in this image.
   *
   * @param matrix The transformation matrix that will be applied.
   */
  private void applyFilter(ArrayList<ArrayList<Double>> matrix) {
    for (int row = 0; row < pixels.size(); row++) {
      for (int column = 0; column < pixels.get(0).size(); column++) {
        pixels.get(row).set(column,
            new MatrixVectorOperationImpl()
                .productMatrixVector(matrix, pixels.get(row).get(column)));
      }
    }
  }

  @Override
  public void exportAs(String fileName, FileType fileType) throws IOException {
    fileType.writeImage(this, fileName);
  }

  @Override
  public ArrayList<ArrayList<ArrayList<Double>>> getPixels() {
    ArrayList<ArrayList<ArrayList<Double>>> copy = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<ArrayList<Double>> row = new ArrayList<>();
      for (int k = 0; k < pixels.get(0).size(); k++) {
        ArrayList<Double> pixel = new ArrayList<>();
        for (int c = 0; c < 3; c++) {
          pixel.add(pixels.get(i).get(k).get(c));
        }
        row.add(pixel);
      }
      copy.add(row);
    }
    return copy;
  }

}
