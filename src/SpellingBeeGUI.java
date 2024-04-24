
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class SpellingBeeGUI {
  static Color backgroundColor;
  static int numWords = 0;
  static int score = 0;
  static ArrayList<String> words = new ArrayList<String>();
  static String wordsString = "";
  static String word = "";
  private static Long timer;
  static JLabel timerLabel;

  public static void gameCallSpellingBee(Color backGroundColor) {
    int BUTTONHEIGHT = 25;
    int TOPPADDING = 20 + 5;
    
    timer = (long) 0;
        
    word = "";
    wordsString = "";
    words = new ArrayList<String>();
    numWords = 0;
    score = 0;

    ArrayList<Character> letters = SpellingBee.generateSevenLetters();
    try {
      letters = SpellingBee.scrambleLetters(letters);
    } catch (Exception e) {
      System.out.println("scramble error");
    }

    JFrame frame = new JFrame("Spelling Bee");

    JPanel panel = new JPanel();

    JLabel correctWordsLabel = new JLabel("Number of Words: " + numWords);
    Dimension size = correctWordsLabel.getPreferredSize();
    correctWordsLabel.setBounds(30, TOPPADDING, size.width + 40, size.height);
    
    JLabel scoreLabel = new JLabel("Score: " + score);
    scoreLabel.setBounds(30, TOPPADDING - 15, size.width, size.height);
    
    JLabel wordLabel = new JLabel(word);
    wordLabel.setBounds(225, TOPPADDING, size.width, size.height);

    JButton button1 = new JButton();
    button1.setPreferredSize(new Dimension(100, 100));
    button1.setText("" + letters.get(0));
    button1.setBounds(10 + 5 + 50 * (1), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button2 = new JButton();
    button2.setText("" + letters.get(1));
    button2.setBounds(10 + 5 + 50 * (2), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button3 = new JButton();
    button3.setText("" + letters.get(2));
    button3.setBounds(10 + 5 + 50 * (3), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button4 = new JButton();
    button4.setText("" + letters.get(3));
    button4.setBounds(10 + 5 + 50 * (4), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button5 = new JButton();
    button5.setText("" + letters.get(4));
    button5.setBounds(10 + 5 + 50 * (5), BUTTONHEIGHT + TOPPADDING, 50, 50);
    
    JButton button6 = new JButton();
    button6.setText("" + letters.get(5));
    button6.setBounds(10 + 5 + 50 * (6), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button7 = new JButton();
    button7.setText("" + letters.get(6));
    button7.setBounds(10 + 5 + 50 * (7), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton clearAllButton = new JButton();
    clearAllButton.setText("Clear");
    clearAllButton.setBounds(10 + 5 + 50 * (7), (BUTTONHEIGHT * 4) + TOPPADDING, 100, 50);

    JButton backButton = new JButton();
    backButton.setText("Main Menu");
    backButton.setBounds(10 + 5 + 50 * (7), (BUTTONHEIGHT * 7) + TOPPADDING, 100, 50);
    
    JLabel guessedWordsLabel = new JLabel(wordsString);
    guessedWordsLabel.setBounds(30, (BUTTONHEIGHT * 5), size.width * 2, size.height * 8);
    guessedWordsLabel.setVerticalAlignment(guessedWordsLabel.TOP);
    
    timerLabel = new JLabel("00:00:00");
    timerLabel.setBounds(225, TOPPADDING - 15, size.width, size.height);

    panel.setLayout(null);

    panel.add(correctWordsLabel);
    panel.add(wordLabel);
    panel.add(scoreLabel);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.add(button5);
    panel.add(button6);
    panel.add(button7);
    panel.add(clearAllButton);
    panel.add(backButton);
    panel.add(guessedWordsLabel);
    panel.add(timerLabel);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.add(panel);
    frame.setSize(500, 300);
    panel.setBackground(backGroundColor);

    frame.setVisible(true);
    timer();

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

    clearAllButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          word = "";
          wordLabel.setText(word);
      }
    });
    
    button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button1.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });
    
    button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button2.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });

    
    button3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button3.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });

    
    button4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button4.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });

    
    button5.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button5.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });

    
    button6.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button6.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });
    
    button7.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addLetter(button7.getText().charAt(0));
            if (shouldAddWord()) {
                addWord();
                guessedWordsLabel.setText("<html>" + wordsString + "</html>");
                correctWordsLabel.setText("Number of Words: " + numWords);
                scoreLabel.setText("Score: " + score);
            }
            wordLabel.setText(word);
        }
     });


  }
  
  private static void addLetter(char letter) {
      word += letter;
      word = word.toLowerCase();
  }
  
  private static void addWord() {
      words.add(word);
      wordsString = wordsString + word + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
      if (words.size() % 5 == 0) {
          wordsString = wordsString + "<br>";
      }
      numWords++;
      score += SpellingBee.getPoints(word);
      word = "";
  }
  
  private static boolean shouldAddWord() {
      return SpellingBee.checkEnterWord(word) && !words.contains(word) && word.length() >= 4;
  }
  
  public static void timer() {
      Timer timer = new Timer(true);
      TimerTask task = new TimerTask() {
        public void run() {
          SpellingBeeGUI.timer += 1000;
          long hours = (SpellingBeeGUI.timer / 3600000) % 24;
          long minutes = (SpellingBeeGUI.timer / 60000) % 60;
          long seconds = (SpellingBeeGUI.timer / 1000) % 60;
          String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
          SwingUtilities.invokeLater(() -> SpellingBeeGUI.timerLabel.setText(formattedTime));
        }
      };
      timer.scheduleAtFixedRate(task, 0, 1000);
    }

}
