
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SpellingBeeGUI {
  public static void main(String[] args) {

    int numWords = 0;
    String word = "";
    int BUTTONHEIGHT = 25;
    int TOPPADDING = 10;
    
    ArrayList<Character> letters = SpellingBee.generateSevenLetters();

    JFrame frame = new JFrame("Spelling Bee");

    JPanel panel = new JPanel();

    JLabel correctWordsLabel = new JLabel("Number of Words: " + numWords);
    Dimension size = correctWordsLabel.getPreferredSize();
    correctWordsLabel.setBounds(10, TOPPADDING, size.width, size.height);

    JButton button1 = new JButton();
    button1.setPreferredSize(new Dimension(100, 100));
    button1.setText("");
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

    panel.setLayout(null);

    panel.add(correctWordsLabel);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.add(button5);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.add(panel);
    frame.setSize(500, 300);
    panel.setBackground(new Color(255, 100, 255, 100));

    frame.setVisible(true);

  }
}
