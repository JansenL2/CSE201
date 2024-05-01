
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainMenu {
  static int dailyStreak;
  static Color backgroundColor = new Color(255, 209, 220);
  static Color titleColor = new Color(255 / 3, 209 / 3, 220 / 3);
  private static ArrayList<Player> players = new ArrayList<>();

  private static Player thisPlayer;

  public static void main(String[] args) throws IOException {
    {
      File file = new File("Player.txt");
      file.createNewFile();
      readPlayerFile();
    }

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

    register();
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

    JLabel ID = new JLabel("Player " + thisPlayer.getID());
    ID.setBounds(10,TOPPADDING + 10, size.width + 50, size.height);
    JLabel connectionsGame = new JLabel("ConnectionsGame wins:" + thisPlayer.getConnectionsGame());
    connectionsGame.setBounds(10,TOPPADDING + 20, size.width + 100, size.height);
    JLabel spellingBee = new JLabel("SpellingBee wins:" + thisPlayer.getSpellingBee());
    spellingBee.setBounds(10,TOPPADDING + 30, size.width + 80, size.height);
    JLabel sudoku = new JLabel("Sudoku wins:" + thisPlayer.getSudoku());
    sudoku.setBounds(10,TOPPADDING + 40, size.width + 60, size.height);
    JLabel wordle = new JLabel("Wordle wins:" + thisPlayer.getWordle());
    wordle.setBounds(10,TOPPADDING + 50, size.width + 50, size.height);

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
    button1.setBorderPainted(false);

    JButton button2 = new JButton();
    button2.setText("Spelling Bee");
    button2.setFocusPainted(false);
    button2.setBackground(titleColor);
    button2.setForeground(Color.WHITE);
    button2.setBounds(150 * (2) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 90, 150, 75);
    // button2.setBorderPainted(false);
    button2.setBorder(BorderFactory.createLineBorder(backgroundColor));

    JButton button3 = new JButton();
    button3.setText("Sudoku");
    button3.setBounds(150 * (2) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 15, 150, 75);
    button3.setFocusPainted(false);
    button3.setBackground(titleColor);
    button3.setForeground(Color.WHITE);
    // button3.setBorderPainted(false);
    button3.setBorder(BorderFactory.createLineBorder(backgroundColor));

    JButton button4 = new JButton();
    button4.setText("Wordle");
    button4.setBounds(150 * (3) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 90, 150, 75);
    button4.setFocusPainted(false);
    button4.setBackground(titleColor);
    button4.setForeground(Color.WHITE);
    // button4.setBorderPainted(false);
    button4.setBorder(BorderFactory.createLineBorder(backgroundColor));

    JButton button5 = new JButton();
    button5.setText("Connections");
    button5.setBounds(150 * (3) - (75 * (3) - 25), BUTTONHEIGHT + TOPPADDING + 15, 150, 75);
    button5.setFocusPainted(false);
    button5.setBackground(titleColor);
    button5.setForeground(Color.WHITE);
    // button5.setBorderPainted(false);
    button5.setBorder(BorderFactory.createLineBorder(backgroundColor));

    JButton clearStreakButton = new JButton();
    clearStreakButton.setText("Reset Streak");
    clearStreakButton.setBounds(frame.getWidth() - 165, BUTTONHEIGHT + TOPPADDING + 115 + 100, 135, 50);
    clearStreakButton.setFocusPainted(false);
    clearStreakButton.setBackground(titleColor);
    clearStreakButton.setForeground(Color.WHITE);
    clearStreakButton.setBorderPainted(false);

    JButton pinkBackgroundButton = new JButton();
    pinkBackgroundButton.setText("");
    pinkBackgroundButton.setBounds(15, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    pinkBackgroundButton.setFocusPainted(false);
    pinkBackgroundButton.setBackground(new Color(255, 209, 220));
    pinkBackgroundButton.setForeground(Color.WHITE);
    pinkBackgroundButton
        .setBorder(BorderFactory.createLineBorder(new Color(255 / 3, 209 / 3, 220 / 3)));

    JButton greenBackgroundButton = new JButton();
    greenBackgroundButton.setText("");
    greenBackgroundButton.setBounds(65, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    greenBackgroundButton.setFocusPainted(false);
    greenBackgroundButton.setBackground(new Color(193, 225, 193));
    greenBackgroundButton.setForeground(Color.WHITE);
    greenBackgroundButton
        .setBorder(BorderFactory.createLineBorder(new Color(193 / 3, 225 / 3, 193 / 3)));

    JButton blueBackgroundButton = new JButton();
    blueBackgroundButton.setText("");
    blueBackgroundButton.setBounds(115, BUTTONHEIGHT + TOPPADDING + 115 + 100, 50, 50);
    blueBackgroundButton.setFocusPainted(false);
    blueBackgroundButton.setBackground(new Color(167, 199, 231));
    blueBackgroundButton.setForeground(Color.WHITE);
    blueBackgroundButton
        .setBorder(BorderFactory.createLineBorder(new Color(167 / 3, 199 / 3, 231 / 3)));

    panel.setLayout(null);

    panel.add(titleLabel);
    panel.add(streakLabel);

    panel.add(ID);
    panel.add(connectionsGame);
    panel.add(spellingBee);
    panel.add(sudoku);
    panel.add(wordle);

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
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    frame.add(panel);

    panel.setBackground(backgroundColor);

    frame.setVisible(true);

    // ACTION LISTENERS

    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        writePlayerFile();
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
        panel.setVisible(false);
        ConnectionsGame game = new ConnectionsGame(backgroundColor);
        game.setVisible(true);
        frame.dispose();
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

        clearStreakButton.setBackground(titleColor);
        button2.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button3.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button4.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button5.setBorder(BorderFactory.createLineBorder(backgroundColor));

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

        clearStreakButton.setBackground(titleColor);
        button2.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button3.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button4.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button5.setBorder(BorderFactory.createLineBorder(backgroundColor));
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

        clearStreakButton.setBackground(titleColor);
        button2.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button3.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button4.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button5.setBorder(BorderFactory.createLineBorder(backgroundColor));

      }
    });
  }

  public static void register(){
    JFrame frame = new JFrame("User Login System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 100);
    frame.setLayout(new FlowLayout());

    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showLoginDialog(frame);  // Show login dialog
        frame.dispose();
      }
    });

    JButton newUserButton = new JButton("Create New User");
    newUserButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showNewUserDialog(frame);  // Show new user dialog
        frame.dispose();
      }
    });

    frame.add(loginButton);
    frame.add(newUserButton);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private static void showLoginDialog(JFrame parentFrame) {
    JDialog loginDialog = new JDialog(parentFrame, "Login", true);
    loginDialog.setLayout(new BoxLayout(loginDialog.getContentPane(), BoxLayout.Y_AXIS));
    loginDialog.setSize(300, 150);

    JPanel userPanel = new JPanel();
    userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel userLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(10);
    userPanel.add(userLabel);
    userPanel.add(usernameField);

    JPanel passPanel = new JPanel();
    passPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel passLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(10);
    passPanel.add(passLabel);
    passPanel.add(passwordField);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton loginBtn = new JButton("Login");
    loginBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        Player player = new Player(userName,password);
        for (Player p:players) {
          if (player.equals(p)){
            loginDialog.dispose();
            thisPlayer = p;
            try {
              mainCall();
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
            return;
          }
        }
        JDialog dialog = new JDialog();
        dialog.setTitle("The user name or password is incorrect");
        dialog.setSize(350, 100);
        dialog.setLayout(new FlowLayout());
        dialog.setModal(true);
        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            dialog.dispose();
          }
        });
        dialog.add(button);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
      }
    });
    buttonPanel.add(loginBtn);

    loginDialog.add(userPanel);
    loginDialog.add(passPanel);
    loginDialog.add(buttonPanel);

    loginDialog.setLocationRelativeTo(parentFrame);
    loginDialog.setVisible(true);
  }

  private static void showNewUserDialog(JFrame parentFrame) {
    JDialog newUserDialog = new JDialog(parentFrame, "Create New User", true);
    newUserDialog.setLayout(new BoxLayout(newUserDialog.getContentPane(), BoxLayout.Y_AXIS));
    newUserDialog.setSize(300, 150);

    JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel userLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(10);
    userPanel.add(userLabel);
    userPanel.add(usernameField);

    JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel passLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(10);
    passPanel.add(passLabel);
    passPanel.add(passwordField);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton createUserBtn = new JButton("Create User");
    createUserBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        if (userName == null || password == null){
          JDialog dialog = new JDialog();
          dialog.setTitle("The user name or password shouldn't be null");
          dialog.setSize(350, 100);
          dialog.setLayout(new FlowLayout());
          dialog.setModal(true);
          JButton button = new JButton("Okay");
          button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              dialog.dispose();
            }
          });
          dialog.add(button);
          dialog.setLocationRelativeTo(null);
          dialog.setVisible(true);
        }
        Player player = new Player(userName,password);
        players.add(player);
        thisPlayer = player;

        newUserDialog.dispose();
        try {
          mainCall();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    buttonPanel.add(createUserBtn);

    newUserDialog.add(userPanel);
    newUserDialog.add(passPanel);
    newUserDialog.add(buttonPanel);

    newUserDialog.setLocationRelativeTo(parentFrame);
    newUserDialog.setVisible(true);
  }

  public static void readPlayerFile(){
    File file = new File("Player.txt");
    Scanner scanner = null;
    try{
      scanner = new Scanner(file);
    }catch (IOException e){
      e.getMessage();
    }
    while (scanner.hasNextLine()){
      String line = scanner.nextLine();
      if (line.isEmpty()) break;
      String[] e = line.split(" ");
      players.add(new Player(e[0],e[1],Integer.parseInt(e[2]),Integer.parseInt(e[3]),Integer.parseInt(e[4]),Integer.parseInt(e[5])));
    }
    scanner.close();
  }

  public static void writePlayerFile(){
    File file = new File("Player.txt");
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      for (Player p:players) {
        bufferedWriter.write(p.toString() + "\n");
      }
      bufferedWriter.close();
    }catch (IOException e){
      e.getMessage();
    }
  }

  public static void updatePlayerList(){
    for (Player p:players) {
      if (p.equals(thisPlayer)){
        p.setConnectionsGame(thisPlayer.getConnectionsGame());
        p.setSpellingBee(thisPlayer.getSpellingBee());
        p.setSudoku(thisPlayer.getSudoku());
        p.setWordle(thisPlayer.getWordle());
        break;
      }
    }
  }

  public static void winGame(int i){
    switch (i){
      case 1:
        thisPlayer.addConnectionsGame();
        break;
      case 2:
        thisPlayer.addSpellingBee();
        break;
      case 3:
        thisPlayer.addSudoku();
        break;
      case 4:
        thisPlayer.addWordle();
        break;
    }
    updatePlayerList();
  }

}
