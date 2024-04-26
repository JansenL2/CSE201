import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class WordleGUI {
  static int currentLetter = 0;
  static int currentWord = 0;
  static String word = "";
  static boolean gameOver = false;
  static Color backgroundColor;
  private static Long timer;
  static JLabel timerLabel;

  public static void gameCallWordle(Color backgroundColor) {

    currentLetter = 0;
    currentWord = 0;
    word = "";
    gameOver = false;

    timer = (long) 0;

    String answer = Wordle.generateWord();
    System.out.print(answer);

    int BUTTONHEIGHT = 25;
    int TOPPADDING = 10;

    ArrayList<JButton> grid = new ArrayList<>();

    JFrame frame = new JFrame("Wordle");

    JPanel panel = new JPanel();

    panel.setLayout(null);

    for (int i = 0; i < 30; i++) {

      int rowCount = i / 5 + 1;
      int colCount = i % 5;

      JButton button = new JButton();
      button.setPreferredSize(new Dimension(100, 100));
      button.setText("");
      button.setBounds(100 + 5 + 50 * (colCount), BUTTONHEIGHT * rowCount * 2 - 20 + TOPPADDING, 50,
          50);
      button.setBackground(Color.WHITE);
      ;
      grid.add(button);
      panel.add(button);
    }

    JButton backButton = new JButton();
    backButton.setText("Main Menu");

    backButton.setBounds(375, 300, 100, 50);
    backButton.setFocusPainted(false);
    backButton.setBackground(new Color(backgroundColor.getRed() / 3, backgroundColor.getGreen() / 3,
        backgroundColor.getBlue() / 3));
    backButton.setForeground(Color.WHITE);
    backButton.setBorderPainted(false);
    panel.add(backButton);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    timerLabel = new JLabel("00:00:00");
    Dimension size = timerLabel.getPreferredSize();
    timerLabel.setBounds(212, TOPPADDING, size.width + 50, size.height);
    panel.add(timerLabel);

    frame.add(panel);
    frame.setSize(500, 400);
    panel.setBackground(backgroundColor);
    grid.get(currentLetter + (currentWord * 5)).setBackground(Color.LIGHT_GRAY);

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);

        try {
          MainMenu.mainCall();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

        ;
      }
    });

    frame.setVisible(true);
    timer();

    frame.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {

        if (!gameOver) {
          if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            grid.get(currentLetter + (currentWord * 5)).setBackground(Color.WHITE);
            if (currentLetter > 0) {
              grid.get(currentLetter + (currentWord * 5) - 1).setText("");
              word = word.substring(0, word.length() - 1);
              currentLetter--;
            }
          } else if (currentLetter != 5) {
            if (Character.isLetter(e.getKeyChar())) {
              grid.get(currentLetter + (currentWord * 5)).setBackground(Color.WHITE);
              grid.get(currentLetter + (currentWord * 5))
                  .setText((String.valueOf(e.getKeyChar())).toUpperCase());
              word = word + String.valueOf(e.getKeyChar());
              currentLetter++;
            }
          } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            grid.get(currentLetter + (currentWord * 5)).setBackground(Color.WHITE);
            currentLetter = 0;

            if (Wordle.checkEnterWord(word)) {
              int[] results = Wordle.checkWord(answer, word);
              int result;
              for (int i = 0; i < results.length; i++) {
                result = results[i];

                switch (result) {
                  case 0:
                    grid.get(i + (currentWord * 5)).setBackground(Color.GREEN);
                    break;
                  case 1:
                    grid.get(i + (currentWord * 5)).setBackground(Color.YELLOW);
                    break;
                  case 2:
                    break;
                  default:
                    break;
                }
              }

              if (Wordle.isWin(Wordle.checkWord(answer, word))) {
                gameOver = true;
              }

              currentWord++;
            } else {
              currentWord++;
              grid.get(currentLetter-- + (currentWord * 5) - 1).setText("");
              grid.get(currentLetter-- + (currentWord * 5) - 1).setText("");
              grid.get(currentLetter-- + (currentWord * 5) - 1).setText("");
              grid.get(currentLetter-- + (currentWord * 5) - 1).setText("");
              grid.get(currentLetter-- + (currentWord * 5) - 1).setText("");
            }

            word = "";
          }
        }

        if (currentLetter != 5 && currentWord != 6 && !gameOver) {
          grid.get(currentLetter + (currentWord * 5)).setBackground(Color.LIGHT_GRAY);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });

    frame.setFocusable(true);
    frame.requestFocusInWindow();

  }

  public static void timer() {
    Timer timer = new Timer(true);
    TimerTask task = new TimerTask() {
      public void run() {

        if (!gameOver) {
          WordleGUI.timer += 1000;
          long hours = (WordleGUI.timer / 3600000) % 24;
          long minutes = (WordleGUI.timer / 60000) % 60;
          long seconds = (WordleGUI.timer / 1000) % 60;
          String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
          SwingUtilities.invokeLater(() -> WordleGUI.timerLabel.setText(formattedTime));
        }
      }
    };
    timer.scheduleAtFixedRate(task, 0, 1000);
  }
}
