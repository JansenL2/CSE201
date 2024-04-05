import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class WordleGUI {
    static int currentLetter = 0;
    static int currentWord = 0;
    static String word = "";
    
    public static void main(String[] args) {

        
        boolean gameOver = false;
        
        int BUTTONHEIGHT = 25;
        int TOPPADDING = 10;
        
        
        ArrayList<JButton> grid = new ArrayList<>();

        JFrame frame = new JFrame("Wordle");

        JPanel panel = new JPanel();

        JLabel wordleHeader = new JLabel("Wordle");
        Dimension size = wordleHeader.getPreferredSize();
        wordleHeader.setBounds(215, TOPPADDING, size.width, size.height);
        
        panel.setLayout(null);
        
        for (int i = 0; i < 30; i++) {
            
            int rowCount = i / 5 + 1;
            int colCount = i % 5;
            
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 100));
            button.setText("");
            button.setBounds(100 + 5 + 50 * (colCount), BUTTONHEIGHT * rowCount * 2 - 20 + TOPPADDING, 50, 50);
            grid.add(button);
            panel.add(button);
        }
        
        panel.add(wordleHeader);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setSize(500, 400);
        panel.setBackground(new Color(255, 100, 255, 100));

        frame.setVisible(true);
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (currentLetter > 0) {
                        grid.get(currentLetter - 1).setText("");
                        word = word.substring(0, word.length() - 1);
                        currentLetter--; 
                    }
                }
                else if (currentLetter != 5) {
                    grid.get(currentLetter + (currentWord * 5)).setText(String.valueOf(e.getKeyChar()));
                    word = word + String.valueOf(e.getKeyChar());
                    currentLetter++;
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    currentLetter = 0;
                    currentWord++;
                    System.out.println(word);
                    word = "";

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        frame.setFocusable(true);
        frame.requestFocusInWindow();
      }
}
