
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SpellingBeeGUI {
  static Color backgroundColor;

  public static void gameCallSpellingBee(Color backGroundColor) {

    int numWords = 0;
    String word = "";
    int BUTTONHEIGHT = 25;
    int TOPPADDING = 10;

    JFrame frame = new JFrame("Spelling Bee");

    JPanel panel = new JPanel();

    JLabel correctWordsLabel = new JLabel("Number of Words: " + numWords);
    Dimension size = correctWordsLabel.getPreferredSize();
    correctWordsLabel.setBounds(10, TOPPADDING, size.width, size.height);

    JButton button1 = new JButton();
    button1.setPreferredSize(new Dimension(100, 100));
    button1.setText("A");
    button1.setBounds(10 + 5 + 50 * (0), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button2 = new JButton();
    button2.setText("B");
    button2.setBounds(10 + 5 + 50 * (1), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button3 = new JButton();
    button3.setText("C");
    button3.setBounds(10 + 5 + 50 * (2), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button4 = new JButton();
    button4.setText("D");
    button4.setBounds(10 + 5 + 50 * (3), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton button5 = new JButton();
    button5.setText("E");
    button5.setBounds(10 + 5 + 50 * (4), BUTTONHEIGHT + TOPPADDING, 50, 50);

    JButton clearAllButton = new JButton();
    button5.setText("Clear All");
    button5.setBounds(10 + 5 + 50 * (5), BUTTONHEIGHT + TOPPADDING, 100, 50);

    JButton backButton = new JButton();
    backButton.setText("Main Menu");

    backButton.setBounds(400, 300 - 75, 100, 50);

    panel.setLayout(null);

    panel.add(correctWordsLabel);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.add(button5);
    panel.add(backButton);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.add(panel);
    frame.setSize(500, 300);
    panel.setBackground(backGroundColor);

    frame.setVisible(true);

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

      }
    });

  }

}
