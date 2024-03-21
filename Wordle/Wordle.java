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

    public static void main(String[] args) {
        
    }
}
