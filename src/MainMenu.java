
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class MainMenu {
  static int dailyStreak;
  static Color backgroundColor = new Color(255, 209, 220);
  static Color titleColor = new Color(255 / 3, 209 / 3, 220 / 3);

  public static void main(String[] args) throws IOException {

    String className = getLookAndFeelClassName("Metal");
    try {
      UIManager.setLookAndFeel(className);
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    mainCall();

  }

  public static String getLookAndFeelClassName(String nameSnippet) {
    LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
    for (LookAndFeelInfo info : plafs) {
      if (info.getName().contains(nameSnippet)) {
        return info.getClassName();
      }
    }
    return null;
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
    frame.setSize(500, 400);
    JPanel panel = new JPanel();

    JLabel streakLabel = new JLabel("Streak: " + dailyStreak);
    Dimension size = streakLabel.getPreferredSize();
    streakLabel.setBounds(10, TOPPADDING, size.width, size.height);

    JLabel titleLabel = new JLabel("Cognify");
    size = titleLabel.getPreferredSize();
    titleLabel.setBounds(185, TOPPADDING + 15, 150, 50);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
    titleLabel.setForeground(titleColor);

    JButton button1 = new JButton();
    button1.setText("Close");
    button1.setBounds(frame.getWidth() / 2 - 50, BUTTONHEIGHT + TOPPADDING + 115 + 100, 100, 50);
    button1.setFocusPainted(false);
    button1.setBackground(titleColor);
    button1.setForeground(Color.WHITE);

    JButton button2 = new JButton();
    button2.setText("Spelling Bee");
    button2.setFocusPainted(false);
    button2.setBackground(titleColor);
    button2.setForeground(Color.WHITE);
    button2.setBounds(150 * (2) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 90, 150, 75);

    JButton button3 = new JButton();
    button3.setText("Sudoku");
    button3.setBounds(150 * (2) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 15, 150, 75);
    button3.setFocusPainted(false);
    button3.setBackground(titleColor);
    button3.setForeground(Color.WHITE);

    JButton button4 = new JButton();
    button4.setText("Wordle");
    button4.setBounds(150 * (3) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 90, 150, 75);
    button4.setFocusPainted(false);
    button4.setBackground(titleColor);
    button4.setForeground(Color.WHITE);

    JButton button5 = new JButton();
    button5.setText("Connections");
    button5.setBounds(150 * (3) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 15, 150, 75);
    button5.setFocusPainted(false);
    button5.setBackground(titleColor);
    button5.setForeground(Color.WHITE);

    JButton clearStreakButton = new JButton();
    clearStreakButton.setText("Reset Streak");
    clearStreakButton.setBounds(380, BUTTONHEIGHT + TOPPADDING + 115 + 100, 100, 50);
    clearStreakButton.setFocusPainted(false);
    clearStreakButton.setBackground(titleColor);
    clearStreakButton.setForeground(Color.WHITE);

    JButton pinkBackgroundButton = new JButton();
    pinkBackgroundButton.setText("Pink Background");
    pinkBackgroundButton.setBounds(15, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    pinkBackgroundButton.setFocusPainted(false);
    pinkBackgroundButton.setBackground(titleColor);
    pinkBackgroundButton.setForeground(Color.WHITE);

    JButton greenBackgroundButton = new JButton();
    greenBackgroundButton.setText("Green Background");
    greenBackgroundButton.setBounds(65, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    greenBackgroundButton.setFocusPainted(false);
    greenBackgroundButton.setBackground(titleColor);
    greenBackgroundButton.setForeground(Color.WHITE);

    JButton blueBackgroundButton = new JButton();
    blueBackgroundButton.setText("Blue Background");
    blueBackgroundButton.setBounds(115, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    blueBackgroundButton.setFocusPainted(false);
    blueBackgroundButton.setBackground(titleColor);
    blueBackgroundButton.setForeground(Color.WHITE);

    panel.setLayout(null);

    panel.add(titleLabel);
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
        titleColor = new Color(255 / 3, 209 / 3, 220 / 3);
        panel.setBackground(backgroundColor);
        titleLabel.setForeground(titleColor);
        button1.setBackground(titleColor);
        button2.setBackground(titleColor);
        button3.setBackground(titleColor);
        button4.setBackground(titleColor);
        button5.setBackground(titleColor);
        pinkBackgroundButton.setBackground(titleColor);
        greenBackgroundButton.setBackground(titleColor);
        blueBackgroundButton.setBackground(titleColor);
        clearStreakButton.setBackground(titleColor);

      }
    });
    greenBackgroundButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backgroundColor = new Color(193, 225, 193);
        titleColor = new Color(193 / 3, 225 / 3, 193 / 3);
        panel.setBackground(backgroundColor);
        titleLabel.setForeground(titleColor);
        button1.setBackground(titleColor);
        button2.setBackground(titleColor);
        button3.setBackground(titleColor);
        button4.setBackground(titleColor);
        button5.setBackground(titleColor);
        pinkBackgroundButton.setBackground(titleColor);
        greenBackgroundButton.setBackground(titleColor);
        blueBackgroundButton.setBackground(titleColor);
        clearStreakButton.setBackground(titleColor);
      }
    });
    blueBackgroundButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        backgroundColor = new Color(167, 199, 231);
        titleColor = new Color(167 / 3, 199 / 3, 231 / 3);
        panel.setBackground(backgroundColor);
        titleLabel.setForeground(titleColor);
        button1.setBackground(titleColor);
        button2.setBackground(titleColor);
        button3.setBackground(titleColor);
        button4.setBackground(titleColor);
        button5.setBackground(titleColor);
        pinkBackgroundButton.setBackground(titleColor);
        greenBackgroundButton.setBackground(titleColor);
        blueBackgroundButton.setBackground(titleColor);
        clearStreakButton.setBackground(titleColor);

      }
    });
  }

}
