package cs3500.marblesolitaire.controller;

/**
 * Marble Solitaire Controller Interface to represent
 *        controllers for various games of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire.
   * @throws IllegalArgumentException - Throws an illegal argument if
   *        the controller is unable to successfully read input or transmit output.
   */
  void playGame() throws IllegalStateException;
}
