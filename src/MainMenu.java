
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
  public static void main(String[] args) throws IOException {

    mainCall();

  }

  public static void mainCall() throws IOException {

    File streakFile = new File("dateCheck.txt");
    Scanner myReader = new Scanner(streakFile);
    String prevDate = myReader.nextLine();
    int dailyStreak = myReader.nextInt();
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
    button2.setBounds(10 + 5 + 100 * (1), BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton button3 = new JButton();
    button3.setText("Sudoku");
    button3.setBounds(10 + 5 + 100 * (2), BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton button4 = new JButton();
    button4.setText("Wordle");
    button4.setBounds(10 + 5 + 100 * (3), BUTTONHEIGHT + TOPPADDING, 100, 50);

    panel.setLayout(null);

    panel.add(streakLabel);

    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.add(panel);

    panel.setBackground(new Color(255, 100, 255, 100));

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
        SpellingBeeGUI.gameCallSpellingBee();
      }
    });
  }

}
