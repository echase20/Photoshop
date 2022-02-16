package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.model.ImageModel;

/**
 * Interface for parsing user commands and calling the correct methods from a given Model.
 */
public interface IScriptReader {

  /**
   * Passed the given user commands to the given Image model, creating a workspace to manipulate,
   * or save and load images.
   * @param script
   */
  void readScript(String script);
}
