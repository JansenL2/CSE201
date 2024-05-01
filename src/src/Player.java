public class Player {
    private String ID;
    private String password;
    private int connectionsGame;
    private int spellingBee;
    private int sudoku;
    private int wordle;


    public Player(){
        ID = null;
        password = null;
        spellingBee = 0;
        sudoku = 0;
        wordle = 0;
        connectionsGame = 0;
    }

    public Player(String ID, String password){
        this.ID = ID;
        this.password = password;
        spellingBee = 0;
        sudoku = 0;
        wordle = 0;
        connectionsGame = 0;
    }

    public Player(String ID, String password, int connectionsGame, int spellingBee, int sudoku, int wordle) {
        this.ID = ID;
        this.password = password;
        this.connectionsGame = connectionsGame;
        this.spellingBee = spellingBee;
        this.sudoku = sudoku;
        this.wordle = wordle;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSpellingBee() {
        return spellingBee;
    }

    public void addSpellingBee() {
        this.spellingBee++;
    }

    public void setSpellingBee(int spellingBee){
        this.spellingBee = spellingBee;
    }

    public int getSudoku() {
        return sudoku;
    }

    public void addSudoku() {
        this.sudoku++;
    }

    public void setSudoku(int sudoku){
        this.sudoku = sudoku;
    }

    public int getWordle() {
        return wordle;
    }

    public void addWordle() {
        this.wordle++;
    }

    public void setWordle(int wordle){
        this.wordle = wordle;
    }

    public int getConnectionsGame() {
        return connectionsGame;
    }

    public void addConnectionsGame() {
        this.connectionsGame++;
    }

    public void setConnectionsGame(int connectionsGame){
        this.connectionsGame = connectionsGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;

        if (getID() != null ? !getID().equals(player.getID()) : player.getID() != null) return false;
        return getPassword() != null ? getPassword().equals(player.getPassword()) : player.getPassword() == null;
    }

    @Override
    public String toString() {
        return ID + " " + password + " " + connectionsGame + " " + spellingBee + " " + sudoku + " " + wordle;
    }
}
