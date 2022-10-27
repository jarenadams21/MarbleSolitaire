package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireFactory;

/**
 * A Game of English Solitaire.
 */
public class EnglishSolitaireModel extends MarbleSolitaireFactory
        implements MarbleSolitaireModel {

  /**
   * Default constructor for English Solitaire, initializes the game board with a
   * thickness of 3, and the empty slot in the middle of the board at (3,3).
   */
  public EnglishSolitaireModel() {

    super(3, 3, 3);
  }

  /**
   * Constructor that takes in the row and col position of the empty space, and initializes the game
   * with a thickness of 3.
   *
   * @param sRow - Row position of the empty space.
   * @param sCol - Col position of the empty space.
   * @throws IllegalArgumentException - Throws an illegal exception if provided empty space
   *                                  is not valid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {

    super(3, sRow, sCol);

  }

  /**
   * Constructor that only takes in arm thickness as a parameter.
   *
   * @param armThickness - Number of marbles in the top row.
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness, (armThickness + (2 *
                    (armThickness - 1)) - 1)
                    - ((armThickness - 1) + (armThickness / 2)),
            (armThickness + (2 * (armThickness - 1)) - 1)
                    - ((armThickness - 1) + (armThickness / 2)));

  }

  /**
   * Constructor that takes in thickness, the row position of the empty space,
   * and the col position of the empty space.
   *
   * @param armThickness - Number of marbles in the top row.
   * @param sRow         - Row position of the empty space.
   * @param sCol         - Col position of the empty space.
   * @throws IllegalArgumentException - Throws an illegal exception if
   *                                  the provided empty space is not valid.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {

    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean invalidPos(int r, int c) {
    return (r < this.armThickness - 1 && c < this.armThickness - 1
            || r < this.armThickness - 1 && c > (this.armThickness - 1) * 2
            || r > (this.armThickness - 1) * 2 && c < this.armThickness - 1
            || r > (this.armThickness - 1) * 2 && c > (this.armThickness - 1) * 2);
  }


}