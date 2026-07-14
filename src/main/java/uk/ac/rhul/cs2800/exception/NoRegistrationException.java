package uk.ac.rhul.cs2800.exception;

/**
 * Exception thrown when there are no modules available for computation.
 */
public class NoRegistrationException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Constructs a NoRegistrationException with a specified message.
   *
   * @param message the custom message for the exception
   */
  public NoRegistrationException(String message) {
    super(message); // Pass the message to the superclass (Exception)
  }

}

