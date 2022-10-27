
import org.junit.Test;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for games of marble solitaire.
 */
public class SolitaireTests {

  @Test
  public void testNoInputConstructorCorrectlyInitializesBoard() {

    MarbleSolitaireModel noInput = new EnglishSolitaireModel();
    assertEquals(noInput.getBoardSize(), 7);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(noInput);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testEmptySpaceOnlyConstructorCorrectlyInitializesBoard() {

    MarbleSolitaireModel emptySpace1 = new EnglishSolitaireModel(4,3);
    assertEquals(emptySpace1.getBoardSize(), 7);
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(emptySpace1);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O", view1.toString());


    MarbleSolitaireModel emptySpace2 = new EnglishSolitaireModel(5,3);
    assertEquals(emptySpace1.getBoardSize(), 7);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(emptySpace2);
    assertEquals("    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O",
            view2.toString());



  }

  @Test
  public void testArmThicknessOnlyConstructorCorrectlyInitializesBoard() {

    MarbleSolitaireModel thicknessOnly1 = new EnglishSolitaireModel(5);
    MarbleSolitaireModel thicknessOnly2 = new EnglishSolitaireModel(7);

    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(thicknessOnly1);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(thicknessOnly2);

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view1.toString());

    assertEquals("            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O", view2.toString());

  }

  @Test
  public void testArmThicknessAndEmptySpaceConstructorCorrectlyInitializesBoard() {

    MarbleSolitaireModel allComponents1 = new EnglishSolitaireModel(3, 1, 2);
    MarbleSolitaireModel allComponents2 = new EnglishSolitaireModel(5, 7,3);

    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(allComponents1);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(allComponents2);

    assertEquals("    O O O\n" +
            "    _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view1.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view2.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsRowPositionForSecondConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(7,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsColPositionForConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(3,7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRowPositionForConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(-3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColPositionForConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(3,-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPositionForConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(1,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenNumberForThicknessConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeNumberForThicknessConstructor() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleFromPositionForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(3,3,1,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFromPositionForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(0,1,1,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotEmptyToPositionForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(3,6,3,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidToPositionForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(3,6,1,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarblesNotExactlyTwoAwayForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    model.move(3,6,3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleInBetweenToAndFromPositionForMove() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    model.move(3,5,3,3);
    model.move(3,6,3,4);

  }

  @Test
  public void validMoves() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view.toString());
    model.move(6,4,6,6);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view.toString());
    model.move(6,7,6,5);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O _ _ O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view.toString());
    model.move(8,6,6,6);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O _ O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view.toString());
  }

  @Test
  public void testGetBoardSize() {

    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireModel model2 = new EnglishSolitaireModel(3,4);
    MarbleSolitaireModel model3 = new EnglishSolitaireModel(7);
    MarbleSolitaireModel model4 = new EnglishSolitaireModel(5, 3,6);

    assertEquals(7, model1.getBoardSize());
    assertEquals(7, model2.getBoardSize());
    assertEquals(19, model3.getBoardSize());
    assertEquals(13, model4.getBoardSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidRowPosition() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();

    model.getSlotAt(8,2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidColPosition() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(7);

    model.getSlotAt(5,24);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidNegativePosition() {

    MarbleSolitaireModel model = new EnglishSolitaireModel(5);

    model.getSlotAt(-1,0);

  }

  @Test
  public void testValidGetSlotAt() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    assertEquals(model.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model.getSlotAt(3,4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model.getSlotAt(0,1), MarbleSolitaireModelState.SlotState.Invalid);
  }

  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    model.move(3,1,3,3);
    assertFalse(model.isGameOver());
    model.move(3,4,3,2);
    model.move(5,3,3,3);
    model.move(4,1,4,3);
    model.move(4,4,4,2);
    assertFalse(model.isGameOver());
    model.move(4,6,4,4);
    model.move(5,4,3,4);
    model.move(2,3,4,3);
    model.move(2,4,4,4);
    model.move(2,5,4,5);
    model.move(2,6,4,6);
    assertFalse(model.isGameOver());
    model.move(2,1,2,3);
    model.move(1,3,3,3);
    model.move(4,3,2,3);
    model.move(4,2,2,2);
    assertFalse(model.isGameOver());
    model.move(4,5,4,3);
    model.move(6,2,4,2);
    model.move(4,3,4,1);
    model.move(4,0,4,2);
    assertFalse(model.isGameOver());
    model.move(2,2,2,4);
    model.move(1,4,3,4);
    model.move(0,2,2,2);
    model.move(0,4,0,2);
    assertFalse(model.isGameOver());
    model.move(2,0,4,0);
    model.move(6,4,6,2);
    assertTrue(model.isGameOver());
  }

  /*

  4 2 4 4 4 5 4 3 6 4 4 4" +
        "5 2 5 4 5 5 5 3 5 7 5 5 6 5 4 5 3 4 5 4" +
        "3 5 5 5 3 6 5 6 3 7 5 7 3 2 3 4 2 4 4 4" +
        "5 4 3 4 5 3 3 3 5 6 5 4 7 3 5 3 5 4 5 2" +
        "5 1 5 3 3 3 3 5 2 5 4 5 1 3 3 3 1 5 1 3" +
        "3 1 5 1 7 5 7 3
   */

  @Test
  public void testGetScore() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals(model.getScore(), 32);
    model.move(3,1,3,3);
    assertEquals(model.getScore(), 31);
    model.move(3,4,3,2);
    assertEquals(model.getScore(), 30);
  }
}