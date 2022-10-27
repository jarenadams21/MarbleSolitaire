import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for updated view class.
 */
public class NewViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNewConstructorDoesNotTakeNullModel() {

    MarbleSolitaireModel model = null;
    Appendable builder = new StringBuilder("");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewConstructorDoesNotTakeNullBuilder() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable builder = null;
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
  }

  @Test
  public void testNewConstructorCorrectlyInitializesWithValidInputs() {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable builder = new StringBuilder("");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
    assertEquals(builder.toString(), "");
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
  }

  @Test
  public void testRenderBoardAppendsStateToAppendable() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable builder = new StringBuilder("");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
    try {
      view.renderBoard();
    } catch (IOException e) {

      throw new IOException("Could not render board correctly.");
    }

    assertEquals(builder.toString(), view.toString());
  }

  @Test (expected = IOException.class)
  public void testRenderBoardThrowsIOProperly() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable builder = new MockAppendable();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
    view.renderBoard();


  }

  @Test
  public void testRenderMessageWorksCorrectly() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    String message = "Hello World!";
    Appendable builder = new StringBuilder("");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);

    try {
      view.renderMessage(message);

    } catch (IOException e) {

      throw new IOException("Could not render board correctly.");
    }

    assertEquals(builder.toString(), message);
  }

  @Test (expected = IOException.class)
  public void testRenderMessageThrowsIOIfFailsToTransmit() throws IOException {

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable builder = new MockAppendable();
    String message = "Hello Builders!";
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, builder);
    view.renderMessage(message);
  }
}
