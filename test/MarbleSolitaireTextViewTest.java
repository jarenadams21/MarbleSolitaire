
import org.junit.Test;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for marble solitaire text views.
 */
public class MarbleSolitaireTextViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelGivenToConstructor() {

    MarbleSolitaireView view = new MarbleSolitaireTextView(null);
  }

  @Test
  public void testViewsCreatedWithValidModels() {

    MarbleSolitaireView view = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));
    assertEquals("    OOOOO    \n    OOOOO    \n    OOOOO    \n    OOOOO    \n" +
            "OOOOOOOOOOOOO\nOOOOOOOOOOOOO\nOOOOOO_OOOOOO\nOOOOOOOOOOOOO\nOOOOOOOOOOOOO\n" +
            "    OOOOO    \n    OOOOO    \n    OOOOO    \n    OOOOO    ", view.toString());
  }

  @Test
  public void testToString() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    assertEquals("  OOO  \n  OOO  \n"
            + "OOOOOOO\nOOO_OOO\nOOOOOOO\n  OOO  \n  OOO  ", view.toString());
    model.move(3,5,3,3);
    assertEquals("  OOO  \n  OOO  \n"
            + "OOOOOOO\nOOOO__O\nOOOOOOO\n  OOO  \n  OOO  ", view.toString());
  }
}
