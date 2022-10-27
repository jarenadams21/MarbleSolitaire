import org.junit.Test;


import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the controller implementation.
 */
public class ControllerTest {

  /**
   * Main method to test controller functionality.
   *
   * @param args - Arguments passed into main.
   */

  public static void main(String[] args) {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));

    controller.playGame();

  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullModelThrowsIllegalArgument() {

    MarbleSolitaireModel model = null;
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullViewThrowsIllegalArgument() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = null;
    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullReadableThrowsIllegalArgument() {


    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    Readable input = null;
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
  }

  //-->
  @Test
  public void testConstructorCorrectlyInitializesWithValidInputs() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    StringBuilder log = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    Readable input = new StringReader("q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
    assertEquals("", log.toString());

  }

  @Test
  public void testControllerCommunicatesValidMoveAttempts() {

    Readable in = new StringReader("4 6 4 4");
    StringBuilder log = new StringBuilder("");

    MarbleSolitaireModel model = new MockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("3 5 3 3", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testControllerThrowsIllegalStateWithNoMoreReadableInputs() {

    Readable in = new StringReader("4 6");
    StringBuilder log = new StringBuilder("");

    MarbleSolitaireModel model = new MockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
  }

  @Test
  public void testControllerSkipsGarbageInputs() {

    Readable in = new StringReader("4 p * / . d ^ 6 ! 4 f 4");
    StringBuilder log = new StringBuilder("");

    MarbleSolitaireModel model = new MockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("3 5 3 3", log.toString());
  }

  @Test
  public void testConrtollerDoesNotAcceptNegativeNumbers() {

    Readable in = new StringReader("-4 4 -6 6 -4 4 -4 4");
    StringBuilder log = new StringBuilder("");

    MarbleSolitaireModel model = new MockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("3 5 3 3", log.toString());
  }

  @Test
  public void testConstructorUpdatesCorrectViewAfterMove() {

    Readable in = new StringReader("-4 4 -6 6 -4 4 -4 4 q");
    Appendable log = new StringBuilder("");

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testControllerViewWithNoValidMoveButQuit() {

    Readable in = new StringReader("-4 4 -6 6 -4 4 -4 q");
    Appendable log = new StringBuilder("");

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());
  }

  @Test
  public void testControllerDisplaysQuitScreenAfterValidMove() {

    Readable in = new StringReader("-4 4 -6 6 -4 4 -4 4 q");
    Appendable log = new StringBuilder("");

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", log.toString());
  }

  @Test
  public void testControllerDisplaysCorrectGameOverScreen() {

    Readable in = new StringReader("4 2 4 4 4 5 4 3 6 4 4 4 5 2 5 4 5 5 5 3 5" +
            " 7 5 5 6 5 4 5 3 4 5 4 " +
            "3 5 5 5 3 6 5 6 3 7 5 7 3 2 3 4 2 4 4 4 5 4" +
            " 3 4 5 3 3 3 5 6 5 4 7 3 5 3 5 4 5 2 5 1 5 3 3 3 3 5 2" +
            " 5 4 5 1 3 3 3 1 5 1 3 3 1 5 1 7 5 7 3");
    Appendable log = new StringBuilder("");

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "O _ _ O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "O _ O _ _ O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "O _ O _ O _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O _ O _ _ _ _\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ O O O\n" +
            "O _ O O _ _ _\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ _ O O\n" +
            "O _ O _ _ O O\n" +
            "O _ O O O _ _\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ _ _ O\n" +
            "O _ O _ _ _ O\n" +
            "O _ O O O O _\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "O _ O O O O O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O _ _ O _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "O _ O O O O O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ O O _ _ _\n" +
            "O _ O O O O O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "O _ O _ O O O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O O _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O O O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O O _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "    O _ _\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O O _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ O O _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 15\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O O _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 14\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ O O _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 13\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 12\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "_ _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 11\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "_ _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "_ _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    _ O O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    O _ _\n" +
            "Score: 7\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "O _ O _ _ _ O\n" +
            "    _ _ _\n" +
            "    O _ _\n" +
            "Score: 7", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testControllerThrowsIllegalStateWithRenderBoardError() {

    Readable in = new StringReader("-4 4 -6 6 -4 4 -4 q");
    StringBuilder log = new StringBuilder();
    Appendable mock = new MockAppendable();
    MarbleSolitaireModel model = new MockModel(log);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, mock);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
  }

  @Test
  public void testControllerDoesNotTakeOutOfBoundsMoves() {

    Readable in = new StringReader("4 6 4 13 q");
    StringBuilder log = new StringBuilder("");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());

  }

  @Test
  public void testControllerDoesNotTakeMovesWithFarMarbles() {

    Readable in = new StringReader("4 7 4 4 q");
    StringBuilder log = new StringBuilder("");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());

  }

  @Test
  public void testControllerDoesNotTakeMovesWithNoMarbleInbetween() {

    Readable in = new StringReader("4 6 4 4 4 7 4 5 q");
    StringBuilder log = new StringBuilder("");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ _\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ _\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", log.toString());

  }


}
