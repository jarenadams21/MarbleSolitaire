import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Mock Model class for testing.
 */
public class MockModel implements MarbleSolitaireModel {

  final StringBuilder log;
  boolean state;

  /**
   * Mock Model Constructor.
   * @param log - Tracks information provided by the controller.
   */
  public MockModel(StringBuilder log) {

    this.log = Objects.requireNonNull(log);
    this.state = false;
  }

  @Override

  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(fromRow + " " + fromCol + " " + toRow + " " + toCol);
    this.state = true;
  }

  public boolean isGameOver() {
    return isGameOver(this.state);
  }

  public boolean isGameOver(boolean state) {
    return state;
  }

  @Override

  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
