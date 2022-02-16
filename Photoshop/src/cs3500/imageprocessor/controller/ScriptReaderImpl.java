package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.model.ImageModel;
import cs3500.imageprocessor.model.ImageModelImpl;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Basic implementation of the IScriptReader interface.
 */
public class ScriptReaderImpl implements IScriptReader {

  private final ImageModel model;
  private Readable rd;
  private Appendable ap;

  /**
   * Constructs a Script Reader that calls the given model and writes and reads to the given
   * appendable and readable respectively.
   *
   * @param model Model to be called.
   * @param rd    Readable to be read.
   * @param ap    Appendable to be appended.
   */
  public ScriptReaderImpl(ImageModel model, Readable rd, Appendable ap) {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("null passed to ScriptReader constructor");
    } else {
      this.model = model;
      this.rd = rd; //User input goes here
      this.ap = ap;
    }
  }

  public ScriptReaderImpl(String input) {
    this(new ImageModelImpl(), new StringReader(input), System.out);
  }

  /**
   * Default script reader constructor.
   */
  public ScriptReaderImpl() {
    this("");
  }

  @Override
  public void readScript(String script) {

    //load image
    //save image
    //image effects (blur, sharpen, sepia, monochrome)
    //create/add layers

    Scanner scan = new Scanner(rd);

    // modify the specified layer (second word) according
    // to the specified transformation( first word)
    while (scan.hasNext()) {
      String command = scan.next().toLowerCase();
      switch (command) {
        case "load":
          loadImage(scan.next());
          break;
        case "save":
          saveImage(scan.next());
          break;
        case "blur":
          model.blur(scan.next());
          break;
        case "sharpen":
          model.sharpen(scan.next());
          break;
        case "sepia":
          model.sepia(scan.next());
          break;
        case "grey":
          model.monochrome(scan.next());
          break;
      }
    }
  }

  private void loadImage(String command) {

  }

  private void saveImage(String command) {

  }
}
