package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class to represent games
 * English, Triangular, and European marble solitaire.
 */
public abstract class MarbleSolitaireFactory implements MarbleSolitaireModel {

  protected int armThickness;
  protected SlotState[][] board;
  protected int score;

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
  protected MarbleSolitaireFactory(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {

    this.initializeBoard(armThickness, sRow, sCol);
  }

  protected abstract boolean invalidPos(int row, int col);

  /**
   * Initializes the English Solitaire board.
   *
   * @param row The row of the empty space.
   * @param col The col of the empty space.
   */
  protected void initializeBoard(int armThickness, int row, int col) {
    this.armThickness = armThickness;
    this.score = 0;

    if (this.armThickness % 2 == 0 || this.armThickness <= 1) {

      throw new IllegalArgumentException("Arm thickness is not a positive odd number.");
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

  /**
   * Move a marble over another marble to a position that is empty and exactly two spaces away.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException If the move is to an invalid/non-empty space,
   *                                  if the move is more than two positions away, if
   *                                  no marble exists inbetween
   *                                  the from and to pos, or if the selected slot is not a marble.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if (getSlotAt(fromRow, fromCol) != SlotState.Marble) {

      throw new IllegalArgumentException("Cannot move from an empty/invalid from position.");
    } else if (getSlotAt(toRow, toCol) != SlotState.Empty
            || !possibleMove(fromRow, fromCol, toRow, toCol)) {

      throw new IllegalArgumentException("The desired move position is not valid!");
    }

    // Otherwise, its a valid move, we just need to ensure there is a marble in between
    else {

      // Are we moving horizontally?
      if (fromRow == toRow) {

        int inbetween = 0;
        if (toCol > fromCol) {

          inbetween = fromCol + 1;
        } else {

          inbetween = toCol + 1;
        }

        board[fromRow][fromCol] = SlotState.Empty;

        // Is there a marble in this inbetween position?
        if (board[fromRow][inbetween] != SlotState.Marble) {

          throw new IllegalArgumentException("There is no marble inbetween the to and from pos.");
        } else {
          board[fromRow][inbetween] = SlotState.Empty;
        }
        score--;
        board[toRow][toCol] = SlotState.Marble;
      }
      // Otherwise we are moving vertically.
      else {

        int inbetween = 0;
        if (toRow > fromRow) {

          inbetween = fromRow + 1;
        } else {

          inbetween = toRow + 1;
        }

        board[fromRow][fromCol] = SlotState.Empty;

        // Is there a marble in this inbetween position?
        if (board[inbetween][fromCol] != SlotState.Marble) {

          throw new IllegalArgumentException("There is no marble inbetween the to and from pos.");
        } else {

          board[inbetween][fromCol] = SlotState.Empty;
        }
        score--;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
  }

  /**
   * Returns The size of the board (longest length on the board).
   *
   * @return The size of the board.
   */
  @Override
  public int getBoardSize() {

    return this.armThickness + (2 * (this.armThickness - 1));
  }

  /**
   * Returns the SlotState of the given row and col position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return The SlotState at the given position on the board.
   * @throws IllegalArgumentException if the row or col are beyond
   *                                  the dimensions of the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {

    if (row >= getBoardSize() || col >= getBoardSize()
            || row < 0 || col < 0) {
      throw new IllegalArgumentException("The given slot is outside the board dimensions.");
    }
    return board[row][col];
  }

  /**
   * Returns the current score (marbles removed from the board).
   *
   * @return An integer representing the current score.
   */
  @Override
  public int getScore() {

    return this.score;
  }

  /**
   * Checks if the to and from positions are exactly two positions away.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the col number of the position to be moved form.
   * @param toRow   the row number of the position to move to.
   * @param toCol   the col number of the position to move to.
   * @return Returns true if the to and from positions are exactly two positions away,
   *        false otherwise.
   */
  protected boolean possibleMove(int fromRow, int fromCol, int toRow, int toCol) {

    if (fromRow == toRow) {

      return (Math.abs(fromCol - toCol) == 2);

    } else if (fromCol == toCol) {

      return (Math.abs(fromRow - toRow) == 2);

    } else {

      return false;
    }
  }

  /**
   * Determines if the given move is possible.
   *
   * @param fromRow - Row position for the from pos.
   * @param fromCol - Col position for the from pos.
   * @param toRow   - Row position for the to pos.
   * @param toCol   - Col position for the to pos.
   * @return Returns true if the move is valid, false otherwise.
   */
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
    }

    // Otherwise, its a valid move, we just need to ensure there is a marble in between
    else {
      if (fromRow == toRow) {

        int inbetween = 0;
        if (toCol > fromCol) {

          inbetween = fromCol + 1;
        } else {

          inbetween = toCol + 1;
        }

        if (board[fromRow][inbetween] != SlotState.Marble) {

          return false;
        }

      } else {

        int inbetween = 0;
        if (toRow > fromRow) {

          inbetween = fromRow + 1;
        } else {

          inbetween = toRow + 1;
        }


        if (board[inbetween][fromCol] != SlotState.Marble) {

          return false;
        }

      }
    }

    return true;
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

        if (this.canMove(r, c, r - 2, c)
                || this.canMove(r, c, r, c - 2)
                || this.canMove(r, c, r + 2, c)
                || this.canMove(r, c, r, c + 2)) {

          return false;
        }
      }
    }
    return true;
  }
}
