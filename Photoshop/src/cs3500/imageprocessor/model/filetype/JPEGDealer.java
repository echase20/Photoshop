package cs3500.imageprocessor.model.filetype;

import cs3500.imageprocessor.model.ImageApplications;
import cs3500.imageprocessor.model.ImageApplicationsImpl;
import cs3500.imageprocessor.model.ImageUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * handles the JPEG image type.
 */
public class JPEGDealer implements FileType {

  @Override
  public ImageApplications readImage(String fileName, FileType fileType) throws IOException {
    Scanner sc;
    BufferedImage buffered = ImageIO.read(new File(fileName));
    ArrayList<ArrayList<ArrayList<Double>>> pixels = new ArrayList<>();

    int width = buffered.getWidth();
    int height = buffered.getHeight();

    for (int row = 0; row < height; row++) {
      ArrayList<ArrayList<Double>> thisRow = new ArrayList<>();
      for (int col = 0; col < width; col++) {
        int pixelRGB = buffered.getRGB(col, row);
        Color pixelColor = new Color(pixelRGB);
        ArrayList<Double> thisPixel = new ArrayList<>();
        thisPixel.add((double) pixelColor.getRed());
        thisPixel.add((double) pixelColor.getBlue());
        thisPixel.add((double) pixelColor.getGreen());
        thisRow.add(thisPixel);
      }
      pixels.add(thisRow);
    }

    ImageApplications image = new ImageApplicationsImpl(pixels);

    return image;
  }

  @Override
  public void writeImage(ImageApplications image, String fileName) throws IOException {
    ImageUtil u = new ImageUtil();
    File output = new File(fileName);

    BufferedImage buffered = u.bufferImageApplications(image);
    ImageIO.write(buffered, "jpeg", output);

  }
}
