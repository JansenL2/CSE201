import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
    static boolean gameOver = false;
    
    public static void gameCallWordle() {

        String answer = Wordle.generateWord();
        System.out.print(answer);
        
        
        
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
        
        JButton backButton = new JButton();
        backButton.setText("Main Menu");

        backButton.setBounds(375, 300, 100, 50);
        panel.add(backButton);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setSize(500, 400);
        panel.setBackground(new Color(255, 100, 255, 100));
        
        
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
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if (!gameOver) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (currentLetter > 0) {
                            grid.get(currentLetter + (currentWord * 5) - 1).setText("");
                            word = word.substring(0, word.length() - 1);
                            currentLetter--; 
                        }
                    }
                    else if (currentLetter != 5) {
                        grid.get(currentLetter + (currentWord * 5)).setText((String.valueOf(e.getKeyChar())).toUpperCase());
                        word = word + String.valueOf(e.getKeyChar());
                        currentLetter++;
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        currentLetter = 0;
                                  
                       
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
                        word = "";
                    }
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
