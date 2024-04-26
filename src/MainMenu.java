
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainMenu {
  static int dailyStreak;
  static Color backgroundColor = new Color(255, 209, 220);

  public static void main(String[] args) throws IOException {

    mainCall();

  }

  public static void mainCall() throws IOException {

    File streakFile = new File("dateCheck.txt");
    Scanner myReader = new Scanner(streakFile);
    String prevDate = myReader.nextLine();
    dailyStreak = myReader.nextInt();
    myReader.close();

    FileWriter fileWriter = new FileWriter("dateCheck.txt");
    PrintWriter printWriter = new PrintWriter(fileWriter);

    if (java.time.LocalDate.now().toString().compareTo(prevDate) > 0) {
      dailyStreak++;
    }

    printWriter.println(java.time.LocalDate.now());
    printWriter.println(dailyStreak);

    printWriter.close();

    System.out.println(prevDate);
    System.out.println("Streak: " + dailyStreak);

    int BUTTONHEIGHT = 75;
    int TOPPADDING = 10;

    JFrame frame = new JFrame("Main Menu");
    frame.setSize(500, 300);
    JPanel panel = new JPanel();

    JLabel streakLabel = new JLabel("Streak: " + dailyStreak);
    Dimension size = streakLabel.getPreferredSize();
    streakLabel.setBounds(10, TOPPADDING, size.width, size.height);

    JButton button1 = new JButton();
    button1.setText("Close");
    button1.setBounds(frame.getWidth() / 2 - 50, 25 + TOPPADDING, 100, 50);

    JButton button2 = new JButton();
    button2.setText("Spelling Bee");
    button2.setBounds(10 + 5 + 100 * (1) - 65, BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton button3 = new JButton();
    button3.setText("Sudoku");
    button3.setBounds(10 + 5 + 100 * (2) - 65, BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton button4 = new JButton();
    button4.setText("Wordle");
    button4.setBounds(10 + 5 + 100 * (3) - 65, BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton button5 = new JButton();
    button5.setText("Connections");
    button5.setBounds(10 + 5 + 100 * (4) - 65, BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton clearStreakButton = new JButton();
    clearStreakButton.setText("Reset Streak");
    clearStreakButton.setBounds(400, 225, 100, 50);

    JButton pinkBackgroundButton = new JButton();
    pinkBackgroundButton.setText("Pink Background");
    pinkBackgroundButton.setBounds(15, BUTTONHEIGHT + TOPPADDING + 115, 50, 50);

    JButton greenBackgroundButton = new JButton();
    greenBackgroundButton.setText("Green Background");
    greenBackgroundButton.setBounds(65, BUTTONHEIGHT + TOPPADDING + 115, 50, 50);

    JButton blueBackgroundButton = new JButton();
    blueBackgroundButton.setText("Blue Background");
    blueBackgroundButton.setBounds(115, BUTTONHEIGHT + TOPPADDING + 115, 50, 50);

    panel.setLayout(null);

    panel.add(streakLabel);

    panel.add(button1); // close
    panel.add(button2); // spelling bee
    panel.add(button3); // sudoku
    panel.add(button4); // Wordle
    panel.add(button5); // connections
    panel.add(clearStreakButton);

    panel.add(pinkBackgroundButton);
    panel.add(greenBackgroundButton);
    panel.add(blueBackgroundButton);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.add(panel);

    panel.setBackground(backgroundColor);

    frame.setVisible(true);

    // ACTION LISTENERS

    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        System.exit(0);
      }
    });

    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
        SpellingBeeGUI.gameCallSpellingBee(backgroundColor);
        frame.dispose();
      }
    });
    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
        StartGame.sodokuStart(backgroundColor);
        frame.dispose();
      }
    });

    button4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
        WordleGUI.gameCallWordle(backgroundColor);
        frame.dispose();
      }
    });
    button5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // panel.setVisible(false);
        // WordleGUI.gameCallWordle(backgroundColor);
        // frame.dispose();
      }
    });
    clearStreakButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dailyStreak = 0;
        streakLabel.setText("Streak: " + dailyStreak);

        FileWriter fileWriter = null;
        try {
          fileWriter = new FileWriter("dateCheck.txt");
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(java.time.LocalDate.now());
        printWriter.println(dailyStreak);

        printWriter.close();

      }
    });

    pinkBackgroundButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        backgroundColor = new Color(255, 209, 220);
        panel.setBackground(backgroundColor);

      }
    });
    greenBackgroundButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backgroundColor = new Color(193, 225, 193);
        panel.setBackground(backgroundColor);

      }
    });
    blueBackgroundButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backgroundColor = new Color(167, 199, 231);
        panel.setBackground(backgroundColor);

      }
    });
  }

}
