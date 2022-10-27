import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Mock View class.
 */
public class MockView implements MarbleSolitaireView {

  private StringBuilder log;
  MarbleSolitaireModel model;

  /**
   * Mock View Constructor.
   * @param log - Verifies information provided by controller.
   * @param model - Model of marble solitaire.
   */
  public MockView(StringBuilder log, MarbleSolitaireModel model) {

    this.log = log;
    this.model = model;

  }

  @Override

  public void renderBoard() throws IOException {

    MarbleSolitaireView view = new MarbleSolitaireTextView(this.model);
    log.append(view.toString());
  }

  @Override

  public void renderMessage(String message) throws IOException {
    log.append(message);
  }
}
