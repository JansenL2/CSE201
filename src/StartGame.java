import java.awt.Color;

import javax.swing.SwingUtilities;

public class StartGame {

  public static void main(String[] args) {

    sodokuStart(null);

  }

  public static void sodokuStart(Color backgroundColor) {
    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SudokuUI(backgroundColor);
      }
    });
  }
}
