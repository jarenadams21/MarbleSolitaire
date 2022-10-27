package cs3500.marblesolitaire.view;

import java.io.PrintStream;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A visualizer/view of a game of marble solitaire.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireView
        implements MarbleSolitaireView {

  /**
   * Constructor for a MarbleSolitiareTextView.
   *
   * @param model - Current model of marble solitaire that we are viewing.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {

    super(model, new PrintStream(System.out));
  }

  /**
   * Constructor that takes in a MarbleSolitaireModelState to provide view functionality
   * for the model.
   *
   * @param model       - The current model of marble solitaire that we are viewing.
   * @param destination - Destination appendable object for the view to output to.
   * @throws IllegalArgumentException - Throws illegal argument if the provided model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {

    super(model, destination);
  }

  /**
   * Returns a visualization of the current Marble Solitaire game.
   *
   * @return A string representation of the game state.
   */
  public String toString() {

    String display = "";
    int dimension = this.model.getBoardSize();

    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {

        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          display += "_ ";
        }
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          display += "O ";
        }
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          display += "  ";

        }
        if (dimension - j == 1) {
          display = display.stripTrailing();
        }
        if (dimension - j == 1 && dimension - i > 1) {
          display += "\n";
        }
      }
    }


    return display;
  }
}