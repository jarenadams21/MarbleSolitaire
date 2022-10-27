import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireFactory;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Triangular Marble Solitaire.
 */
public class TriangleTest {

  @Test
  public void testTriangularCorrectRenderingWithNewView() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithNegArmLength() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(-1);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidEmptySlot() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(8, 4);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidLengthWithThreeConstructorArgs() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(-1, 0, 0);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidEmptySlotWithThreeConstructorArgs() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(5, -4, 0);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveFromPos() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6);
    triangle.move(0, 1, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNegMoveFromPos() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6);
    triangle.move(0, -1, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveToPos() {
    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6);
    triangle.move(2, 1, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNegMoveToPos() {
    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6);
    triangle.move(2, 1, 0, -1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveDoesNotWorkWithNoMarble() {
    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6, 3, 1);
    triangle.move(3, 1, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveAttemptWithNoMarbleInBetween() {

    MarbleSolitaireFactory triangle = new TriangleSolitaireModel(6, 3, 1);
    triangle.move(5, 3, 1, 3);
    triangle.move(5,1,5,3);
    triangle.move(5,0,5,2);
  }

  @Test
  public void testCorrectInitialization() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(3, 1, 1);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    assertEquals("  O\n" +
            " O _\n" +
            "O O O", view.toString());
  }

  @Test
  public void testMoveUp2SameCol() {
    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(2, 0, 0, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());


  }

  @Test
  public void testMoveDown2SameCol() {
    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(2, 0);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(0, 0, 2, 0);
    assertEquals("    _\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());


  }

  @Test
  public void testMoveColRightButSameRow() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(3, 0, 3, 2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " _ _ O O\n" +
            "O O O O O", view.toString());
  }

  @Test
  public void testMoveColLeftButSameRow() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(3, 1);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(3, 3, 3, 1);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ _\n" +
            "O O O O O", view.toString());
  }

  @Test
  public void testMoveUpAndToTheRight() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(5, 1, 3, 1);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O _ O O O\n" +
            "O _ O O O O", view.toString());
  }

  @Test
  public void testMoveDownToTheLeft() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(1, 1, 3, 1);
    assertEquals("     O\n" +
            "    O _\n" +
            "   O _ O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", view.toString());

  }

  @Test
  public void testMoveDownToTheRight() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(1, 0, 3, 2);
    assertEquals("     O\n" +
            "    _ O\n" +
            "   O _ O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", view.toString());

  }

  @Test
  public void testMoveUpToTheLeft() {

    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(6, 3, 1);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    triangle.move(5, 3, 3, 1);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O _ O O\n" +
            "O O O _ O O", view.toString());

  }

  @Test
  public void testGetScore() {
    MarbleSolitaireFactory triangle =
            new TriangleSolitaireModel(6, 3, 2);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    assertEquals(20,triangle.getScore());
    triangle.move(1, 0, 3, 2);
    assertEquals(19,triangle.getScore());
    triangle.move(3,0,1,0);
    assertEquals(18,triangle.getScore());

  }

  @Test
  public void testGameOver() {
    MarbleSolitaireFactory triangle = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(triangle);
    assertFalse(triangle.isGameOver());
    triangle.move(2,0,0,0);
    triangle.move(4,0,2,0);
    triangle.move(3,2,1,0);
    triangle.move(4,2,4,0);
    triangle.move(4,4,4,2);
    assertFalse(triangle.isGameOver());
    triangle.move(2,2,4,4);
    triangle.move(0,0,2,2);
    triangle.move(2,0,0,0);
    triangle.move(4,2,2,0);
    assertTrue(triangle.isGameOver());
    System.out.println(view.toString());
  }
}
