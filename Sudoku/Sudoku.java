import java.util.*;
/**
 * Sudoku Generator Class.
 *
 * This class implements a Sudoku puzzle generator that fills a 9x9 grid according to the rules of Sudoku:
 * 1. Each row must contain the numbers 1 to 9, without repetitions.
 * 2. Each column must contain the numbers 1 to 9, without repetitions.
 * 3. Each of the nine 3x3 sub-grids that compose the grid must contain the numbers 1 to 9, without repetitions.
 * 4. Each of the two main diagonals must also contain the numbers 1 to 9, without repetitions(It is a rule that makes the puzzle harder, can be removed).
 *
 * The class provides methods to:
 * - Create an empty Sudoku grid filled with 0s.
 * - Generate a complete Sudoku puzzle through a recursive backtracking algorithm.
 * - Check if a given number can be placed in a specific cell without violating Sudoku rules (row, column, 3x3 sub-grid, and diagonals).
 * - Print the generated Sudoku puzzle to the console.
 *
 * It utilizes a randomized approach for number placement to ensure a variety of puzzles can be generated, adhering to the standard Sudoku rules as well as the additional diagonal constraint for enhanced complexity in certain variations.
 */
public class Sudoku {
    private int[][] sudokuGrid;

    public Sudoku(){
        sudokuGrid = createS();
    }

    public int[][] getSudoku(){
        return sudokuGrid;
    }

    //Create a Sudoku fill with 0.
    public int[][] emptyGrid(){
        int[][] sudoku = new int[9][9];
        for (int i = 0;i < 9;i++){
            for (int k = 0;k < 9;k++){
                sudoku[i][k] = 0;
            }
        }
        return sudoku;
    }

    //Initiates the creation of a Sudoku puzzle.
    private int[][] createS(){
        int[][] sudoku = emptyGrid();
        if (createS(sudoku,0,0)){
            return sudoku;
        }
        return null;
    }

    //Recursive helper method for creating a Sudoku puzzle.
    private boolean createS(int[][] sudoku, int row, int col){
        if (row == 9) return true;
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        //fill in the numbers.
        for (Integer num : numbers) {
            if (check(sudoku, row, col, num)) {
                sudoku[row][col] = num;
                if (createS(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    //Checks if a number can be placed in the specified cell without violating Sudoku rules.
    private boolean check(int[][] sudoku, int row, int col, int num){
        int box = (row / 3) * 3 + (col / 3);
        return checkRow(sudoku, row, num) && checkColumn(sudoku, col, num) && checkBox(sudoku, box, num) && checkDiagonal(sudoku,row,col,num);
    }

    //Checks if a number can be placed in the specified row.
    private boolean checkRow(int[][] sudoku, int row, int num){
        for (int col = 0; col < 9; col++) {
            if (sudoku[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    //Checks if a number can be placed in the specified column.
    private boolean checkColumn(int[][] sudoku, int col, int num){
        for (int row  = 0; row  < 9; row ++) {
            if (sudoku[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    //Checks if a number can be placed in the specified 3x3 box.
    private boolean checkBox(int[][] sudoku, int box, int num){
        int startRow = (box / 3) * 3;
        int startCol = (box % 3) * 3;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (sudoku[startRow + row][startCol + col] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    //Checks if a number can be placed on either of the two main diagonals without repeating.
    private boolean checkDiagonal(int[][] sudoku, int row, int col, int num){
        if (row == col) {
            for (int i = 0; i < 9; i++) {
                if (sudoku[i][i] == num) return false;
            }
        }

        if (row + col == 8) {
            for (int i = 0; i < 9; i++) {
                if (sudoku[i][8 - i] == num) return false;
            }
        }
        return true;
    }

    /**
     * Checks if the entire Sudoku grid is valid.
     * It validates every row, column, 3x3 box, and both main diagonals to ensure each contains unique numbers from 1 to 9 without any repetition or zeros.
     */
    public boolean checkWholeGrid(int[][] sudoku){
        //check every row
        for (int row = 0; row < 9; row++) {
            if (!checkArray(sudoku[row])) return false;
        }
        //check every column
        for (int col = 0; col < 9; col++) {
            int[] columnArray = new int[9];
            for (int row = 0; row < 9; row++) {
                columnArray[row] = sudoku[row][col];
            }
            if (!checkArray(columnArray)) return false;
        }
        //check every box
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                int[] boxArray = new int[9];
                int index = 0;
                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        boxArray[index++] = sudoku[row][col];
                    }
                }
                if (!checkArray(boxArray)) return false;
            }
        }
        //check the diagonal from left to right
        int[] diagonalLR = new int[9];
        for (int i = 0; i < 9; i++) {
            diagonalLR[i] = sudoku[i][i];
        }
        if (!checkArray(diagonalLR)) return false;
        //check the diagonal from right to left
        int[] diagonalRL = new int[9];
        for (int i = 0; i < 9; i++) {
            diagonalRL[i] = sudoku[i][8 - i];
        }
        if (!checkArray(diagonalRL)) return false;
        return true;
    }

    /**
     * Checks if an array contains unique numbers from 1 to 9 without any repetition or zeros.
     * This method is used to validate rows, columns, boxes, and diagonals in a Sudoku grid.
     */
    private boolean checkArray(int[] array) {
        boolean[] seen = new boolean[10];
        for (int num : array) {
            if (num == 0) return false;
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }


    // Generates a random number between 1 and 9.
    // Note: As of the current implementation, this method is not being used.
    private int randomNum(){
        Random random = new Random();
        return random.nextInt(9) + 1;
    }

    // Prints the Sudoku grid. This method is used to verify that the Sudoku grid was generated correctly.
    public void print(int[][] sudoku){
        for (int i = 0;i < 9;i++){
            for (int k = 0;k < 9;k++){
                System.out.print(sudoku[i][k] + " ");
            }
            System.out.println();
        }
    }

    // Generates a partially filled Sudoku grid based on the specified difficulty level.
    // Note: difficulty should between 1 and 4. This concerns the number of clues generated. The larger the number, the fewer the clues generated.
    public int[][] seedPuzzle(int difficulty){
        int clues = new Random().nextInt(5) + 40 - 5 * difficulty;
        int[][] puzzle = emptyGrid();
        List<Integer> random = new ArrayList<>();
        for (int i = 0; i < clues; i++) {
            random.add(1);
        }
        for (int i = clues; i < 81; i++){
            random.add(0);
        }
        Collections.shuffle(random);
        int index = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (random.get(index++) == 1) {
                    puzzle[row][col] = sudokuGrid[row][col];
                }
            }
        }
        return puzzle;
    }
}