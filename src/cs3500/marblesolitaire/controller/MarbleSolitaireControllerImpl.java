package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Controller implementation for various games of marble solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructor for the controller implementation of Marble Solitaire.
   *
   * @param model - Marble Solitaire game model.
   * @param view  - View of the provided Marble Solitaire model.
   * @param input - Input to read from.
   * @throws IllegalArgumentException - If any provided argument is null.
   */

  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model
          , MarbleSolitaireView view, Readable input) throws IllegalArgumentException {

    if (model == null || view == null || input == null) {

      throw new IllegalArgumentException("No null arguments!");
    }

    this.model = model;
    this.view = view;
    this.input = input;
  }

  // End game display
  private void endGame(String type) throws IllegalStateException {

    if ( type.compareTo("Game over") == 0) {

      try {
        view.renderMessage("Game over!\n");
      } catch (IOException e) {

        throw new IllegalStateException("Could not render quit message.");
      }

      try {
        view.renderBoard();
        view.renderMessage("\n");
      } catch (IOException e) {

        throw new IllegalStateException("Could not render quit message.");
      }

      try {
        view.renderMessage("Score: " + this.model.getScore());
      } catch (IOException e) {

        throw new IllegalStateException("Could not render current score.");
      }

    }

    else {

      try {
        view.renderMessage("Game quit!\n");
      } catch (IOException e) {

        throw new IllegalStateException("Could not render quit message.");
      }


      try {
        view.renderMessage("State of game when quit:\n");
      } catch (IOException e) {

        throw new IllegalStateException("Could not render state of game message.");
      }

      try {
        view.renderBoard();
        view.renderMessage("\n");
      } catch (IOException e) {

        throw new IllegalStateException("Could not render current board.");
      }

      try {
        view.renderMessage("Score: " + this.model.getScore());
      } catch (IOException e) {

        throw new IllegalStateException("Could not render current score.");
      }
    }
  }

  // Checks if a given input is a positive number.
  private boolean validMove(String move) {

    try {
      Integer.parseInt(move);
    } catch (NumberFormatException e) {

      return false;
    }

    return Integer.parseInt(move) > 0;
  }

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalArgumentException - Throws an illegal argument if
   *        the controller is unable to successfully read input or transmit output.
   */
  public void playGame() throws IllegalStateException {

    // Step 1 - Show initial board state.
    try {
      view.renderBoard();
      view.renderMessage("\n");
    } catch (IOException e) {

      throw new IllegalStateException("Could not render board.");
    }

    // Step 2 - Show initial score.
    try {
      view.renderMessage("Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {

      throw new IllegalStateException("Could not render current score.");
    }

    // Initialize scanner and arraylist of moves
    Scanner scanner = new Scanner(this.input);
    ArrayList<Integer> validMoves = new ArrayList<Integer>();

    // Step 3 - Play the game
    while (!this.model.isGameOver()) {

      if (!scanner.hasNext()) {
        throw new IllegalStateException("No readable inputs, but game is not over.");
      }
      // Reading the input
      String moveInput = scanner.next();

      // Has the user quit?
      if (moveInput.equals("Q") || moveInput.equals("q")) {

        endGame("Quit");
        return;
      } else {

        if (validMove(moveInput)) {
          validMoves.add(Integer.parseInt(moveInput));
        }
      }

      if (validMoves.size() == 4) {

        try {
          this.model.move(validMoves.get(0) - 1, validMoves.get(1) - 1,
                  validMoves.get(2) - 1, validMoves.get(3) - 1);
        } catch (IllegalArgumentException e) {

          try {
            view.renderMessage("Invalid move. Play again.\n");

          } catch (IOException error) {

            throw new IllegalStateException("Could not render invalid move message.");
          }
        }

        try {
          view.renderBoard();
          view.renderMessage("\n");
        } catch (IOException e) {

          throw new IllegalStateException("Could not render board.");
        }


        try {
          view.renderMessage("Score: " + this.model.getScore() + "\n");
        } catch (IOException e) {

          throw new IllegalStateException("Could not render board.");
        }

        validMoves.clear();
      }

      if (this.model.isGameOver()) {

        endGame("Game over");
        return;
      }
    }

  }
}
