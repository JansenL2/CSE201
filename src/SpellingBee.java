import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class SpellingBee {
    static ArrayList<String> words = wordsList();
    private static HashSet<String> wordsHash = new HashSet<>(words);
    
    public static ArrayList<String> wordsList(){
        ArrayList words = new ArrayList<String>();
        try{
            Scanner sr = new Scanner(new File("words_alpha.txt"));
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
    
    public static ArrayList<Character> generateSevenLetters() {
        ArrayList<Character> letters = new ArrayList<Character>();
        String randomWord = null;
        
        while (letters.size() != 7) {
            letters.clear();
            randomWord = words.get(new Random().nextInt(words.size())).toUpperCase();
            
            for (int i = 0; i < randomWord.length(); i++) {
                if (!letters.contains(randomWord.charAt(i))) {
                    letters.add(randomWord.charAt(i));
                }
            }
                
        }
        return letters;
    }
    
    public static ArrayList<Character> scrambleLetters(ArrayList<Character> letters) {
        ArrayList<Character> newLetters = new ArrayList<Character>();
        for (int i = 0; i < letters.size(); i++) {
            newLetters.add(' ');
        }
        int index = new Random().nextInt(7);
        
        for (int i = 0; i < letters.size(); i++) {
            while (newLetters.get(index) != ' ') {
                index = new Random().nextInt(letters.size());
            }
            
            newLetters.remove(index);
            newLetters.add(index, letters.get(i));
        }
        return newLetters;
    }
    
    public static boolean checkEnterWord(String word){
        return wordsHash.contains(word);
    }
    
    public static int getPoints(String word) {
        return word.length() * word.length() / 3;
    }
    
    public static boolean checkWin(int score) {
        return score >= 50;
    }
    
    public static void main(String[] args) {
        ArrayList<Character> letters = generateSevenLetters();
        scrambleLetters(letters);
    }
}
