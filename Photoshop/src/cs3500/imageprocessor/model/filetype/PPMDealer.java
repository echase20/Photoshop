package cs3500.imageprocessor.model.filetype;

import cs3500.imageprocessor.model.ImageApplications;
import cs3500.imageprocessor.model.ImageApplicationsImpl;
import cs3500.imageprocessor.model.ImageUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * handles the PPM filetype.
 */
public class PPMDealer implements FileType {

  @Override
  public ImageApplications readImage(String fileName, FileType fileType) {
    ImageUtil u = new ImageUtil();
    return new ImageApplicationsImpl(u.readPPM(fileName));
  }

  @Override
  public void writeImage(ImageApplications image, String fileName) {
    ImageUtil u = new ImageUtil();
    ArrayList<ArrayList<ArrayList<Double>>> pixels = image.getPixels();
    ArrayList<Integer> ppmChannels = u.dimensionReduce(u.pixelsToRGB(pixels));
    String ppm = ppmChannels.toString();
    String finalPPM = "";
    finalPPM += "P3\n";
    finalPPM = finalPPM + pixels.get(0).size() + " " + image.getPixels().size();
    finalPPM = finalPPM + "\n255";
    finalPPM = finalPPM + "\n" + ppm;

    File file = new File(fileName);
    if (!file.exists()) {
      try {
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] mybytes = finalPPM.getBytes();
        fos.write(mybytes);
        fos.flush();
        fos.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }
}
