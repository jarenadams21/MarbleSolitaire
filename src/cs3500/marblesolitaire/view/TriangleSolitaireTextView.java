package cs3500.marblesolitaire.view;

import java.io.PrintStream;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Text view for a game of Triangle Marble Solitaire.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireView
        implements MarbleSolitaireView {


  /**
   * Triangle Solitaire Text View that only takes in a model.
   *
   * @param model - Triangle Solitaire game model.
   * @throws IllegalArgumentException - If the provided model is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) throws
          IllegalArgumentException {

    super(model, new PrintStream(System.out));

  }

  /**
   * Triangle Solitaire text view that takes in a model and an appendable.
   *
   * @param model       - Triangle Solitaire game model.
   * @param destination - Appendable to transmit to.
   * @throws IllegalArgumentException - If either provided argument is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model,
                                   Appendable destination) throws
          IllegalArgumentException {

    super(model, destination);

  }

  /**
   * Returns a visualization of the current Triangle Solitaire game.
   *
   * @return A string representation of the game state.
   */
  public String toString() {

    String display = "";
    int dimension = this.model.getBoardSize();

    for (int i = 0; i < dimension; i++) {

      for (int space = 0; space < this.model.getBoardSize() - (1 + i); space++) {

        display += " ";
      }

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
