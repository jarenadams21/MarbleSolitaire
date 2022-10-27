package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A European game of Marble Solitaire.
 */
public class EuropeanSolitaireModel extends MarbleSolitaireFactory implements MarbleSolitaireModel {

  /**
   * Default game of European marble solitaire.
   * It creates an octagonal board with sides length 3 and an empty center.
   */
  public EuropeanSolitaireModel() {

    super(3, 3, 3);
  }

  /**
   * Constructor for a game of European marble solitaire
   * with a given specified side length, if odd-positive.
   *
   * @param sideLength - Side length of the game.
   */
  public EuropeanSolitaireModel(int sideLength) {

    super(sideLength, (sideLength + (2 * (sideLength - 1)) - 1)
                    - ((sideLength - 1) + (sideLength / 2)),
            (sideLength + (2 * (sideLength - 1)) - 1)
                    - ((sideLength - 1) + (sideLength / 2)));
  }

  /**
   * Constructor for a game of European marble solitaire
   * with a given specified empty slot.
   *
   * @param row - Row pos of the empty slot.
   * @param col - Col pos of the empty slot.
   */
  public EuropeanSolitaireModel(int row, int col) {

    super(3, row, col);
  }

  /**
   * Constructor for a game of European marble solitaire
   * with a given specified empty slot and sideLength that is odd-positive.
   *
   * @param sideLength - Side length of the game.
   * @param row        - Row pos of the empty slot.
   * @param col        - Col pos of the empty slot.
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {

    super(sideLength, row, col);
  }

  /**
   * Helper method designed to check if a given pos is invalid for
   * a game of european solitaire.
   *
   * @param r - row pos
   * @param c - col pos
   * @return - Returns true if the given pos is invalid, false otherwise.
   */
  protected boolean invalidPos(int r, int c) {

    return ((r < this.armThickness - 1 &&
            c < (this.getBoardSize() / 2) -
                    ((this.armThickness + (r * 2)) / 2)) ||
            (r < this.armThickness - 1 && c > (this.getBoardSize() / 2 +
                    ((this.armThickness + (r * 2)) / 2))) ||
            ((r > this.armThickness * 2 - 2) &&
                    c > (this.getBoardSize() / 2 +
                            ((this.armThickness +
                                    ((this.getBoardSize() - (r + 1)) * 2)) / 2))) ||
            (r > this.getBoardSize() / 2 + ((this.armThickness + (c * 2)) / 2) &&
                    c < (this.armThickness - 1)));


  }
}
