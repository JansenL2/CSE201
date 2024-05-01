import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class ConnectionsGame extends JFrame {
    private static final int GRID_SIZE = 4; // grid size of 4x4
    private final JPanel mainPanel = new JPanel(new BorderLayout()); // main panel
    private final JPanel buttonPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE)); // panel for buttons only
    private final List<String[]> groups = new ArrayList<>(); // groups of words
    private final Map<String, String> wordToGroupMap = new HashMap<>(); // maps words to group names
    private final Map<String, JButton> wordToButtonMap = new LinkedHashMap<>(); // button to  word maps
    private final Set<String> correctlyIdentifiedGroups = new HashSet<>(); // tracks which group were selected correctly
    private int triesLeft = 4; // starts with 4 tries
    private final JLabel triesLabel = new JLabel("Tries left: " + triesLeft); // label for tries
    private final List<JButton> clickedButtons = new ArrayList<>(); // running list of clicked buttons
    private final Color backgroundColor;

    public ConnectionsGame(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        initializeUI();
        loadWordsFromFile("Word_Groups.txt"); // File path
        displayWords();
    }

    private void displayWords() {
        Collections.shuffle(groups); // shuffles the order of groups, so we get different each time
        List<String[]> selectedGroups = groups.subList(0, GRID_SIZE); // selects first group of words
        List<String[]> wordsWithGroups = new ArrayList<>(); // words with group name


        for (String[] group : selectedGroups) {
            for (int i = 1; i < group.length; i++) {
                wordsWithGroups.add(new String[]{group[i], group[0]}); // excludes group name
            }
        }

        Collections.shuffle(wordsWithGroups); // shuffles order of buttons
        buttonPanel.removeAll(); // clear existing buttons from the button panel
        wordToButtonMap.clear(); // clear the button map

        for (String[] wordAndGroup : wordsWithGroups) {
            JButton button = new JButton(wordAndGroup[0]);
            button.addActionListener(e -> buttonClickHandler(button));
            button.setBackground(backgroundColor);
            buttonPanel.add(button);
            wordToGroupMap.put(wordAndGroup[0], wordAndGroup[1]);
            wordToButtonMap.put(wordAndGroup[0], button); // map words to their buttons
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void buttonClickHandler(JButton clickedButton) {
        if (clickedButton.isEnabled()) {
            if (clickedButtons.contains(clickedButton)) {
                clickedButton.setBackground(backgroundColor); // remove highlight
                clickedButtons.remove(clickedButton); // removes from array
            } else if (clickedButtons.size() < 4) {
                clickedButton.setBackground(Color.YELLOW); // Highlight the button
                clickedButtons.add(clickedButton); // adds to array
                if (clickedButtons.size() == 4) {
                    checkGroups();
                }
            }
        }
    }

    private void checkGroups() {
        Set<String> groupNames = new HashSet<>();
        for (JButton button : clickedButtons) {
            groupNames.add(wordToGroupMap.get(button.getText()));
        }
        if (groupNames.size() == 1) {
            JOptionPane.showMessageDialog(this, "Correct! All selected words belong to: " + groupNames.iterator().next());
            moveAndHighlightCorrectWords(groupNames.iterator().next());
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect! Try again.");
            triesLeft--;
            triesLabel.setText("Tries left: " + triesLeft);
            if (triesLeft == 0) {
                JOptionPane.showMessageDialog(this, "Game Over! Resetting...");
                resetGame();
            }
        }
        // Reset clicked buttons state for new attempts
        clickedButtons.forEach(btn -> btn.setBackground(backgroundColor)); // unhighlight all clicked buttons
        clickedButtons.clear(); // clear the list of clicked buttons
    }

    private void moveAndHighlightCorrectWords(String groupName) {
        if (!correctlyIdentifiedGroups.contains(groupName)) {
            for (String word : wordToButtonMap.keySet()) {
                JButton button = wordToButtonMap.get(word);
                if (groupName.equals(wordToGroupMap.get(word))) {
                    button.setBackground(Color.GREEN);
                    button.setEnabled(false); // disable only the correct group's buttons
                }
            }
            correctlyIdentifiedGroups.add(groupName); // mark this group as correctly identified
        }

        if (correctlyIdentifiedGroups.size() == GRID_SIZE) { // final checker
            JOptionPane.showMessageDialog(this, "Congratulations! You have correctly identified all groups!");
            resetGame();
        }
    }

    private void loadWordsFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                groups.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeUI() {
        setTitle("Connections Game");
        setSize(750, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(triesLabel, BorderLayout.NORTH);
        triesLabel.setHorizontalAlignment(JLabel.CENTER);
        triesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        triesLabel.setForeground(Color.WHITE);
        
        mainPanel.setBackground(new Color(backgroundColor.getRed() / 3,
                 backgroundColor.getGreen() / 3, backgroundColor.getBlue() / 3));
        
        JButton backButton = new JButton();
        backButton.setText("Main Menu");
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(backgroundColor.getRed() / 3, backgroundColor.getGreen() / 3,
            backgroundColor.getBlue() / 3));
        backButton.setForeground(Color.WHITE);
        backButton.setBorderPainted(false);
        
        mainPanel.add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              setVisible(false);

              try {
                MainMenu.mainCall();
              } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }

              ;
            }
          });

        add(mainPanel);
    }

    private void resetGame() {
        triesLeft = 4;
        triesLabel.setText("Tries left: " + triesLeft);
        buttonPanel.removeAll();
        correctlyIdentifiedGroups.clear(); // Clear previously identified groups
        displayWords();
    }
}
