package uk.ac.rhul.cs2800.exception;

/**
 * Exception thrown when there are no grades available for computation.
 */

public class NoGradeAvailableException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a NoGradeAvailableException with a specified message.
   *
   * @param message the custom message for the exception
   */
  public NoGradeAvailableException(String message) {
    super(message);
  }

}
