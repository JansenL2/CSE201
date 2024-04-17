import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellingBee {
    static ArrayList<String> words = wordsList();
    
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
}
