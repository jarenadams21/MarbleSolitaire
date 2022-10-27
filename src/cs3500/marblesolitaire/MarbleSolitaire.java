package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Class to run various forms of Marble Solitaire.
 */
public final class MarbleSolitaire {

  /**
   * Main function that runs the various games of marble solitaire
   * and takes command-line args to run a game.
   *
   * @param args - A series of commands to initiate a game of marble solitaire.
   */
  public static void main(String[] args) {

    String type = args[0];
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;

    int size = -1;
    int rowHole = -1;
    int colHole = -1;
    int index = 1;


    while (index < args.length) {

      if (index < args.length && args[index].equals("-size")) {
        size = Integer.parseInt(args[index + 1]);
        index += 2;
      }

      if (index < args.length && args[index].equals("-hole")) {
        rowHole = Integer.parseInt(args[index + 1]);
        colHole = Integer.parseInt(args[index + 2]);
        index += 3;
      }
    }

    if (type.equals("triangular")) {

      if (size == -1) {

        if (rowHole == -1) {

          model = new TriangleSolitaireModel();
        } else {

          model = new TriangleSolitaireModel(rowHole, colHole);
        }
      } else {

        if (rowHole == -1) {
          model = new TriangleSolitaireModel(size);
        } else {

          model = new TriangleSolitaireModel(size, rowHole, colHole);
        }
      }
      view = new TriangleSolitaireTextView(model);
    }

    if (type.equals("english")) {

      if (size == -1) {

        if (rowHole == -1) {

          model = new EnglishSolitaireModel();
        } else {

          model = new EnglishSolitaireModel(rowHole, colHole);
        }
      } else {

        if (rowHole == -1) {
          model = new EnglishSolitaireModel(size);
        } else {

          model = new EnglishSolitaireModel(size, rowHole, colHole);
        }
      }
      view = new MarbleSolitaireTextView(model);
    } else {

      if (size == -1) {

        if (rowHole == -1) {

          model = new EuropeanSolitaireModel();
        } else {

          model = new EuropeanSolitaireModel(rowHole, colHole);
        }
      } else {

        if (rowHole == -1) {
          model = new EuropeanSolitaireModel(size);
        } else {

          model = new EuropeanSolitaireModel(size, rowHole, colHole);
        }
      }
      view = new MarbleSolitaireTextView(model);

    }

    controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));
    controller.playGame();
  }
}