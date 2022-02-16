package cs3500.imageprocessor.model;


import cs3500.imageprocessor.model.filterobject.FilterObject;
import cs3500.imageprocessor.model.filterobject.MonochromeFilter;
import cs3500.imageprocessor.model.filterobject.SepiaFilter;
import cs3500.imageprocessor.model.imageadjuster.BlurImage;
import cs3500.imageprocessor.model.imageadjuster.ImageAdjuster;
import cs3500.imageprocessor.model.imageadjuster.SharpenImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ArrayList<ArrayList<ArrayList<Double>>> readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);
    ArrayList<ArrayList<ArrayList<Double>>> allRows = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<ArrayList<Double>> thisRow = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        ArrayList<Double> thisPixel = new ArrayList<>();
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        thisPixel.add((double) r);
        thisPixel.add((double) g);
        thisPixel.add((double) b);
        thisRow.add(thisPixel);
      }
      allRows.add(thisRow);
    }
    return allRows;
  }

  /**
   * Demo main.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "koala.ppm";
    }

    ArrayList<ArrayList<ArrayList<Double>>> allPixels = ImageUtil.readPPM(filename);

    //Print out the first pixel of the image
    System.out.print("original image: " + allPixels.get(0).get(0));

    FilterObject grey = new MonochromeFilter();
    FilterObject sepia = new SepiaFilter();
    ImageAdjuster blur = new BlurImage();
    ImageAdjuster sharpen = new SharpenImage();

    //transform the image by creating a model from the pixels
    ImageApplications transform = new ImageApplicationsImpl(allPixels);
    transform.adjust(blur);

    //create new arraylist and turn all doubles into ints
    ArrayList<ArrayList<ArrayList<Integer>>> rgbValues = pixelsToRGB(transform.getPixels());

    //Print out the first pixel of the transformed image
    System.out.print("\ntransformed image: " + rgbValues.get(0).get(0));
  }

  /**
   * Converts rows and columns of three dimensional pixels into all Integer values.
   *
   * @param pixels All the pixels of an image stored as three Double channels
   * @return All the pixels now with their rgb values as integers
   */
  public static ArrayList<ArrayList<ArrayList<Integer>>> pixelsToRGB(
      ArrayList<ArrayList<ArrayList<Double>>> pixels) {
    ArrayList<ArrayList<ArrayList<Integer>>> rgbValues = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<ArrayList<Integer>> thisRow = new ArrayList<>();
      for (int k = 0; k < pixels.get(0).size(); k++) {
        ArrayList<Integer> pixel = new ArrayList<>();
        for (int r = 0; r < 3; r++) {
          pixel.add((int) Math.round(pixels.get(i).get(k).get(r)));
        }
        thisRow.add(pixel);
      }
      rgbValues.add(thisRow);
    }
    return rgbValues;
  }

  /**
   * Takes a 3 Dimensional ArrayList and turns it into a 1 Dimensional Arraylist.
   *
   * @param rgb The 3 Dimensional Arraylist to be flattened
   * @return An ArrayList containing every value the original did
   */
  public ArrayList<Integer> dimensionReduce(ArrayList<ArrayList<ArrayList<Integer>>> rgb) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int row = 0; row < rgb.size(); row++) {
      for (int column = 0; column < rgb.get(0).size(); column++) {
        for (int channel = 0; channel < 3; channel++) {
          result.add(rgb.get(row).get(column).get(channel));
        }
      }
    }
    return result;
  }

  /**
   * Turns the given ImageApplications into a BufferedImage.
   *
   * @param image ImageApplications to be transformed
   */
  public BufferedImage bufferImageApplications(ImageApplications image) {
    ArrayList<ArrayList<ArrayList<Double>>> pixels = image.getPixels();

    int width = pixels.get(0).size();
    int height = pixels.size();

    ArrayList<ArrayList<ArrayList<Integer>>> rgb = pixelsToRGB(pixels);

    BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        ArrayList<Integer> thisPixel = rgb.get(row).get(column);
        int r = clampInt(thisPixel.get(0), 0, 255);
        int g = clampInt(thisPixel.get(1), 0, 255);
        int b = clampInt(thisPixel.get(2), 0, 255);

        buffered.setRGB(column, row, new Color(r, g, b).getRGB());

      }
    }
    return buffered;
  }


  /**
   * Ensures that the given number is in between the specified min and max.
   * @param n Number to be clamped
   * @param min Minimum Value
   * @param max Maximum Value
   * @return
   */
  private int clampInt(int n, int min, int max) {
    if (n < min) {
      return min;
    }
    if (n > max) {
      return max;
    }
    return n;
  }

}

