package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents an abstract solitaire view, providing cross-functionality
 *        for the render methods.
 */
public class AbstractSolitaireView implements MarbleSolitaireView {

  protected MarbleSolitaireModelState model;
  protected Appendable destination;

  protected AbstractSolitaireView(MarbleSolitaireModelState model,
                                  Appendable destination) {

    if (model == null || destination == null) {

      throw new IllegalArgumentException("Provided model or destination is null!");
    } else {

      this.model = model;
      this.destination = destination;
    }

  }

  /**
   * Transmits the state of the board.
   *
   * @throws IOException - Throws IOException if transmission fails.
   */
  public void renderBoard() throws IOException {

    try {
      this.destination.append(toString());
    } catch (IOException e) {

      throw new IOException("Error!");
    }
  }

  /**
   * Shows an arbitrary message, allowing this view to show messages from the user.
   *
   * @param message the message to be transmitted
   * @throws IOException - Throws IOException if transmission fails.
   */
  public void renderMessage(String message) throws IOException {

    try {
      this.destination.append(message);
    } catch (IOException e) {

      throw new IOException("Error!");
    }
  }
}
