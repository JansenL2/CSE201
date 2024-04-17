import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuUI {
  private JFrame frame;
  private JPanel gridPanel;
  private JPanel topPanel;
  private JLabel timerLabel;
  private int difficulty;

  private Sudoku sudoku;
  private ArrayList<JComponent> cells;
  private Long timer;
  public static final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");

  /**
   * Default constructor. Creating an instance of SudokuUI with this constructor
   * will automatically start the sudoku puzzle. It initializes the sudoku game
   * logic (by creating a Sudoku instance), a collection of UI components, and a
   * timer, then calls the start() method to begin the game.
   */
  public SudokuUI(Color backgroundColor) {
    sudoku = new Sudoku();
    cells = new ArrayList<>();
    timer = (long) 0;
    start(backgroundColor);
  }

  /**
   * Launches the game setup dialog, allowing the player to select the difficulty
   * of the Sudoku puzzle. This method initializes the game environment, including
   * setting up a difficulty selection dialog with a slider for choosing the game
   * difficulty, and a confirm button to start the game based on the selected
   * difficulty. The difficulty level affects the number of pre-filled cells in
   * the puzzle.
   */
  private void start(Color backgroundColor) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Choose the difficulty.");
        dialog.setModal(true);
        dialog.setLayout(new FlowLayout());

        JSlider slider = new JSlider(1, 4, 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("Easy"));
        labelTable.put(2, new JLabel("Normal"));
        labelTable.put(3, new JLabel("Hard"));
        labelTable.put(4, new JLabel("Insane"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);

        dialog.add(slider);

        JButton button = new JButton("Confirm");
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dialog.dispose();
            difficulty = slider.getValue();
            initializeUI();
            timer();

            topPanel.setBackground(backgroundColor);
            gridPanel.setBackground(backgroundColor);

            frame.setVisible(true);
          }
        });
        dialog.add(button);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
      }
    });
  }

  /**
   * Initializes the user interface of the Sudoku game. This method sets up the
   * main game window, including a top panel for game controls (like a new game
   * button and difficulty slider), a timer label for displaying elapsed time, and
   * the main Sudoku grid. The Sudoku grid is populated based on the selected
   * difficulty level, with either JTextField for empty cells or JLabel for
   * pre-filled cells.
   */
  private void initializeUI() {
    // Create and set up the window.
    frame = new JFrame("Sudoku");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLayout(new BorderLayout());

    topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    timerLabel = new JLabel("00:00:00");
    topPanel.add(timerLabel);

    JSlider slider = new JSlider(1, 4, difficulty);
    slider.setMajorTickSpacing(1);
    slider.setPaintTicks(true);
    Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
    labelTable.put(1, new JLabel("Easy"));
    labelTable.put(2, new JLabel("Normal"));
    labelTable.put(3, new JLabel("Hard"));
    labelTable.put(4, new JLabel("Insane"));
    slider.setLabelTable(labelTable);
    slider.setPaintLabels(true);
    topPanel.add(slider);

    JButton answer = new JButton("new game");
    topPanel.add(answer);
    answer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        difficulty = slider.getValue();
        startNewGame();
        // sudoku.print(sudoku.getSudoku());
      }
    });
    JButton check = new JButton("check");
    topPanel.add(check);
    check.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isWin()) {
          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              difficulty = slider.getValue();
              JDialog dialog = new JDialog();
              dialog.setTitle("You Win");
              dialog.setSize(300, 100);
              dialog.setLayout(new FlowLayout());
              dialog.setModal(true);

              JButton newGameButton = new JButton("New Game");
              newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  dialog.dispose();
                  startNewGame();
                }
              });

              JButton exitButton = new JButton("Exit");
              exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  System.exit(0);
                }
              });

              dialog.add(newGameButton);
              dialog.add(exitButton);

              dialog.setLocationRelativeTo(null);
              dialog.setVisible(true);
            }
          });
        } else {
          JDialog dialog = new JDialog();
          dialog.setTitle("Incorrect");
          dialog.setSize(300, 100);
          dialog.setLayout(new FlowLayout());
          dialog.setModal(true);
          JButton button = new JButton("Okay");
          button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              dialog.dispose();
            }
          });
          dialog.add(button);
          dialog.setLocationRelativeTo(null);
          dialog.setVisible(true);
        }
      }
    });

    frame.add(topPanel, BorderLayout.NORTH);

    // Set the layout for the Sudoku grid.
    gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(9, 9)); // 9x9 grid for Sudoku.

    // Create the Sudoku grid cells.
    int[][] puzzle = sudoku.seedPuzzle(difficulty);
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (puzzle[row][col] == 0) {
          JTextField cell = new JTextField();
          cell.setHorizontalAlignment(JTextField.CENTER);
          cell.setFont(new Font("Arial", Font.BOLD, 20));
          gridPanel.add(cell);
          cells.add(cell);
        } else {
          JLabel cell = new JLabel(String.valueOf(puzzle[row][col]));
          cell.setHorizontalAlignment(JTextField.CENTER);
          cell.setFont(new Font("Arial", Font.BOLD, 20));
          gridPanel.add(cell);
          cells.add(cell);
        }
      }
    }

    frame.add(gridPanel, BorderLayout.CENTER);
  }

  /**
   * Determines if the current Sudoku grid configuration meets the winning
   * criteria. It collects values from UI components (JTextField for inputs and
   * JLabel for fixed numbers), fills a matrix representing the Sudoku grid, and
   * checks if the grid is valid. Invalid or non-numeric inputs are treated as
   * empty cells. Uses sudoku.checkWholeGrid to assess the grid's validity against
   * Sudoku rules.
   */
  public boolean isWin() {
    int[][] sudokuGrid = sudoku.emptyGrid();
    int row = 0;
    int col = 0;
    for (JComponent j : cells) {
      if (j instanceof JTextField) {
        try {
          sudokuGrid[row][col] = Integer.parseInt(((JTextField) j).getText());
        } catch (NumberFormatException e) {
          sudokuGrid[row][col] = 0;
        }
      } else if (j instanceof JLabel) {
        try {
          sudokuGrid[row][col] = Integer.parseInt(((JLabel) j).getText());
        } catch (NumberFormatException e) {
          sudokuGrid[row][col] = 0;
        }
      }
      row = col == 8 ? row + 1 : row;
      col = col == 8 ? 0 : col + 1;
    }
    return sudoku.checkWholeGrid(sudokuGrid);
  }

  // Create a timer Thread
  public void timer() {
    Timer timer = new Timer(true);
    TimerTask task = new TimerTask() {
      public void run() {
        SudokuUI.this.timer += 1000;
        long hours = (SudokuUI.this.timer / 3600000) % 24;
        long minutes = (SudokuUI.this.timer / 60000) % 60;
        long seconds = (SudokuUI.this.timer / 1000) % 60;
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        SwingUtilities.invokeLater(() -> timerLabel.setText(formattedTime));
      }
    };
    timer.scheduleAtFixedRate(task, 0, 1000);
  }

  // As the method name, start a new game.
  // This method is often used when the player restarts the game manually. And
  // restart the game after winning.
  private void startNewGame() {
    sudoku = new Sudoku();
    cells = new ArrayList<>();
    timer = (long) 0;
    timerLabel.setText("00:00:00");
    gridPanel.removeAll();
    gridPanel.revalidate();
    gridPanel.repaint();
    int[][] puzzle = sudoku.seedPuzzle(difficulty);
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (puzzle[row][col] == 0) {
          JTextField cell = new JTextField();
          cell.setHorizontalAlignment(JTextField.CENTER);
          cell.setFont(new Font("Arial", Font.BOLD, 20));
          gridPanel.add(cell);
          cells.add(cell);
        } else {
          JLabel cell = new JLabel(String.valueOf(puzzle[row][col]));
          cell.setHorizontalAlignment(JLabel.CENTER);
          cell.setFont(new Font("Arial", Font.BOLD, 20));
          gridPanel.add(cell);
          cells.add(cell);
        }
      }
    }
    gridPanel.revalidate();
    gridPanel.repaint();
  }

  /**
   * An inner class intended to represent each 3x3 box of the Sudoku puzzle. This
   * class extends JPanel and is configured with a 3x3 GridLayout. Each instance
   * is intended to be bordered with a black line to visually delineate the 3x3
   * Sudoku boxes. However, this class is not fully implemented and serves as a
   * placeholder for potential future enhancements to the Sudoku UI.
   */
  class SudokuBoxPanel extends JPanel {
    public SudokuBoxPanel() {
      super(new GridLayout(3, 3));
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
  }
}
