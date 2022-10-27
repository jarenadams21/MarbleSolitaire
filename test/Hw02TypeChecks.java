import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw02TypeChecks {

  /**
   * A sample main method.
   *
   * @param args the program arguments
   */
  public static void main(String[] args) {
    helper(new EnglishSolitaireModel());
    //helper(new EnglishSolitaireModel(2, 2));
    //helper(new EnglishSolitaireModel(5));
    //helper(new EnglishSolitaireModel(3, 0, 4));

    MarbleSolitaireModel board1 = (new EnglishSolitaireModel(3,0,2));
    MarbleSolitaireModel board2 = (new EnglishSolitaireModel(4, 3));
    MarbleSolitaireModel board3 = (new EnglishSolitaireModel(5));
    MarbleSolitaireModel board5 = (new EnglishSolitaireModel(7));
    MarbleSolitaireModel board4 = (new EnglishSolitaireModel(3,0,4));

    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(board1);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(board2);
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(board3);
    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(board4);
    MarbleSolitaireTextView view5 = new MarbleSolitaireTextView(board5);


    System.out.println(view1.toString());

  }

  private static void helper(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model) {

    model.move(1, 3, 3, 3);
  }

}
