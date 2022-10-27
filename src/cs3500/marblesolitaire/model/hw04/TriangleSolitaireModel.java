package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a game of triangular marble solitaire.
 */
public class TriangleSolitaireModel extends MarbleSolitaireFactory
        implements MarbleSolitaireModel {

  /**
   * Default constructor that creates a 5-row game with the empty slot at
   * (0,0).
   */
  public TriangleSolitaireModel() {

    super(5, 0, 0);
  }

  /**
   * Constructor that takes in a dimension and creates a game with that
   * specified dimension.
   *
   * @param dimensions - Dimension for the game of triangle solitaire.
   * @throws IllegalArgumentException - If the given dimension is non-positive.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {

    super(dimensions, 0, 0);
  }

  /**
   * Constructor that creates a 5-row game with the empty slot at the given pos.
   *
   * @param row - Row pos for the empty slot.
   * @param col - Col pos for the empty slot.
   * @throws IllegalArgumentException If the specified pos is invalid.
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {

    super(5, row, col);
  }

  /**
   * Constructor that takes in dimensions and an empty slot for triangle solitaire.
   *
   * @param dimensions - Dimension for the board(number of slots in bottom-most row)
   * @param row        - Row pos for the empty slot.
   * @param col        - Col pos for the empty slot.
   * @throws IllegalArgumentException - If the specified dimension is not odd-positive
   *                                  or the specified empty slot position is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int row, int col)
          throws IllegalArgumentException {

    super(dimensions, row, col);
  }

  /**
   * Overridden initialize board method for a triangle game of solitaire.
   *
   * @param armThickness Dimension of the triangular board.
   * @param row          The row of the empty space.
   * @param col          The col of the empty space.
   */
  protected void initializeBoard(int armThickness, int row, int col) {
    this.armThickness = armThickness;
    this.score = 0;

    if (this.armThickness < 1) {

      throw new IllegalArgumentException("Arm thickness does not create a valid game.");
    } else if (row >= getBoardSize() || row < 0
            || col >= getBoardSize() || col < 0) {

      throw new IllegalArgumentException(
              "Invalid empty cell position " + "(" + row + "," + col + ")");
    }

    int bound = getBoardSize();


    MarbleSolitaireModelState.SlotState[][] tempBoard =
            new MarbleSolitaireModelState.SlotState[bound][bound];

    for (int r = 0; r < bound; r++) {

      for (int c = 0; c < bound; c++) {

        if (this.invalidPos(r, c)) {

          tempBoard[r][c] = MarbleSolitaireModelState.SlotState.Invalid;
        } else {

          tempBoard[r][c] = MarbleSolitaireModelState.SlotState.Marble;
          this.score++;
        }

      }
    }
    if (tempBoard[row][col] == MarbleSolitaireModelState.SlotState.Invalid) {

      throw new IllegalArgumentException("Invalid empty cell position "
              + "(" + row + "," + col + ")");
    } else {

      tempBoard[row][col] = MarbleSolitaireModelState.SlotState.Empty;
      score--;
    }

    this.board = tempBoard;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if (getSlotAt(fromRow, fromCol) != SlotState.Marble) {

      throw new IllegalArgumentException("Cannot move from an empty/invalid from position.");
    } else if (getSlotAt(toRow, toCol) != SlotState.Empty
            || !canMove(fromRow, fromCol, toRow, toCol)) {

      throw new IllegalArgumentException("The desired move position is not valid!");
    }

    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
    this.score = this.score - 1;

    // Horizontal movement
    if (fromRow == toRow) {

      int inbetween = Math.min(fromCol, toCol) + 1;
      this.board[toRow][inbetween] = SlotState.Empty;

    }

    // Left-Up Diagonal
    else if (Math.abs(fromRow - toRow) == 2 &&
            Math.abs(fromCol - toCol) == 2) {

      int inbetweenRow = Math.min(fromRow, toRow) + 1;
      int inbetweenCol = Math.min(fromCol, toCol) + 1;

      this.board[inbetweenRow][inbetweenCol] = SlotState.Empty;
    }

    // Right-Up Diagonal
    else if (Math.abs(fromRow - toRow) == 2 &&
            Math.abs(fromCol - toCol) == 0) {

      int inbetween = Math.min(fromRow, toRow) + 1;
      this.board[inbetween][toCol] = SlotState.Empty;

    }
  }

  protected boolean possibleMove(int fromRow, int fromCol, int toRow, int toCol) {

    if (fromRow == toRow) {

      return (Math.abs(fromCol - toCol) == 2);

    } else {
      return (Math.abs(fromRow - toRow) == 2 &&
              Math.abs(fromCol - toCol) == 2) ||
              (Math.abs(fromRow - toRow) == 2 &&
                      Math.abs(fromCol - toCol) == 0);
    }
  }

  protected boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {

    if (fromRow < 0 || fromRow >= getBoardSize()
            || fromCol < 0 || fromCol >= getBoardSize()
            || toRow < 0 || toRow >= getBoardSize()
            || toCol < 0 || toCol >= getBoardSize()) {

      return false;
    }

    if (getSlotAt(fromRow, fromCol) != SlotState.Marble) {

      return false;
    } else if (getSlotAt(toRow, toCol) != SlotState.Empty
            || !possibleMove(fromRow, fromCol, toRow, toCol)) {

      return false;
    } else {

      return marbleInBetween(fromRow, fromCol, toRow, toCol);
    }

  }

  /**
   * Helper to check for a marble in between the given from and to positions.
   *
   * @param fromRow - Row pos for the from pos.
   * @param fromCol - Col pos for the from pos.
   * @param toRow   - Row pos for the to pos.
   * @param toCol   - Col pos for the to pos.
   * @return Returns false if there is a marble inbetween the two positions, true otherwise.
   */
  private boolean marbleInBetween(int fromRow, int fromCol,
                                    int toRow, int toCol) {

    // Horizontal movement
    if (fromRow == toRow) {

      int inbetween = Math.min(fromCol, toCol) + 1;

      return (board[toRow][inbetween] == SlotState.Marble);
    }

    // Diagonals

    // Left-Up Diagonal
    else if (Math.abs(fromRow - toRow) == 2 &&
            Math.abs(fromCol - toCol) == 2) {

      int inbetweenRow = Math.min(fromRow, toRow) + 1;
      int inbetweenCol = Math.min(fromCol, toCol) + 1;
      return (board[inbetweenRow][inbetweenCol] == SlotState.Marble);
    }

    // Right-Up Diagonal
    else if (Math.abs(fromRow - toRow) == 2 &&
            Math.abs(fromCol - toCol) == 0) {

      int inbetween = Math.min(fromRow, toRow) + 1;
      return board[inbetween][toCol] == SlotState.Marble;
    }
    else {
      return false;
    }
  }

  @Override
  protected boolean invalidPos(int row, int col) {

    return col > row;
  }

  @Override
  public int getBoardSize() {
    return this.armThickness;
  }

  /**
   * Checks if the game is over.
   *
   * @return Returns true if the game is over, otherwise false.
   */
  @Override
  public boolean isGameOver() {

    for (int r = 0; r < this.board.length; r++) {

      for (int c = 0; c < this.board[0].length; c++) {

        if (this.canMove(r, c, r, c - 2)
                || this.canMove(r, c, r, c + 2) ||
                this.canMove(r, c, r + 2, c + 2) ||
                this.canMove(r, c, r - 2, c - 2) ||
                this.canMove(r, c, r + 2, c) ||
                this.canMove(r, c, r - 2, c)) {

          return false;
        }
      }
    }
    return true;
  }

}
