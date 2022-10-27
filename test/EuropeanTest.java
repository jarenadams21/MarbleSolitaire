import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test classes for a European game of marble solitaire.
 */
public class EuropeanTest {

  @Test
  public void testCorrectBoardRender() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testNewCellAdditions() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    model.move(3, 5, 3, 3);
    assertTrue(model.getSlotAt(1, 5) == MarbleSolitaireModelState.SlotState.Marble);
    model.move(1, 5, 3, 5);
    assertEquals("    O O O\n" +
            "  O O O O _\n" +
            "O O O O O _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
    assertTrue(model.getSlotAt(1, 5) == MarbleSolitaireModelState.SlotState.Empty);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSideLengthConstructorThrowsExceptionForNegLength() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel(-4);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSideLengthConstructorThrowsExceptionForPosLength() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel(4);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromCornerPieceToInvalid() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    model.move(1, 5, 1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorDoesNotAllowInvalidEmptySlot() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel(1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEuropeanConstructorDoesNotAllowInvalidEmptySlotWithNeg() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel(-1, -7);
  }

  @Test
  public void testEuropeanGameOver() {

    MarbleSolitaireModel model = new EuropeanSolitaireModel(3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    assertFalse(model.isGameOver());
    model.move(3, 5, 3, 3);
    model.move(1, 5, 3, 5);
    model.move(2, 3, 2, 5);
    model.move(0, 3, 2, 3);
    assertFalse(model.isGameOver());
    model.move(1, 1, 1, 3);
    model.move(1, 3, 1, 5);
    model.move(3, 1, 1, 1);
    model.move(3, 2, 1, 2);
    model.move(3, 3, 1, 3);
    model.move(5, 2, 3, 2);
    assertFalse(model.isGameOver());
    model.move(5, 4, 3, 4);
    model.move(5, 3, 3, 3);
    model.move(4, 0, 4, 2);
    model.move(4, 2, 2, 2);
    assertFalse(model.isGameOver());
    model.move(3, 4, 3, 2);
    model.move(2, 6, 2, 4);
    model.move(3, 6, 3, 4);
    model.move(4, 6, 4, 4);
    assertFalse(model.isGameOver());
    model.move(1, 2, 1, 4);
    model.move(2, 0, 4, 0);
    model.move(2, 2, 4, 2);
    model.move(1, 5, 1, 3);
    model.move(3, 4, 1, 4);
    model.move(1, 3, 1, 5);
    assertTrue(model.isGameOver());

    System.out.println(view.toString());
  }
}
