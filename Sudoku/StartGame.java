import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SudokuUI();
            }
        });
    }
}
