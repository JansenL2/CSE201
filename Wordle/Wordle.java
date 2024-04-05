import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
    static ArrayList<String> words = wordsList();

    // Randomly select a word from the words list and return.
    public static String generateWord(){
        return words.get(new Random().nextInt(words.size()));
    }

    //change the word to char array
    public static char[] toLetters(String word){
        char[] letters = new char[5];
        for (int i = 0; i < 5;i++){
            letters[i] = word.charAt(i);
        }
        return letters;
    }

    //return turn if win
    public static boolean isWin(int[] result){
        int win = 0;
        for (int i = 0; i < 5; i++){
            if (result[i] == 0) win++;
        }
        if (win == 5) return true;
        return false;
    }

    // 0 if letter at the same place. 1 if appear in the word but not at the same place. 2 if not appear in the word
    public static int[] checkWord(String word, String wordToCheck){
        char[] letters = toLetters(word);
        char[] lettersToCheck = toLetters(wordToCheck);
        int[] result = new int[5];

        for (int i = 0; i < 5;i++){
            if (letters[i] == lettersToCheck[i]){
                result[i] = 0;
            }else if(checkWord(lettersToCheck[i],letters)){
                result[i] = 1;
            }else {
                result[i] = 2;
            }
        }
        return result;
    }

    //helper method
    public static boolean checkWord(char c, char[] letters){
        for (int i = 0; i < 5;i++){
            if (c == letters[i]) return true;
        }
        return false;
    }

    // This method is used to read a list of words from a text file and return it.
    public static ArrayList<String> wordsList(){
        ArrayList words = new ArrayList<String>();
        try{
            Scanner sr = new Scanner(new File("WordleWords.txt"));
            String word = "";
            while (sr.hasNextLine()){
                word = sr.nextLine();
                if (word.isEmpty()) continue;
                words.add(word);
            }
            sr.close();
        }catch (Exception e){
            e.getMessage();
        }
        return words;
    }

    // Filter out all the words in database that contain five letters and then generate a new file.
    public static void WordleWords(File file){
        try{
            Scanner sr = new Scanner(file);
            String word = "";
            FileWriter fileWriter = new FileWriter("WordleWords.txt", true);
            while (sr.hasNextLine()){
                word = sr.nextLine();
                if (word.length() == 5){
                    fileWriter.write(word + "\n");
                }
            }
            fileWriter.close();
            sr.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
