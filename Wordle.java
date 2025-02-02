import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public class Wordle {
    
    private ArrayList<String> wordList = new ArrayList<String>(
        Arrays.asList("apple", "banjo", "chair", "doggy", "roast",
                  "fable", "ghost", "haste", "image", "jolly",
                  "knife", "lucky", "merry", "night", "ocean",
                  "peace", "queen", "robot", "smile", "table"));
                  
    private String[][] keyboard = new String[3][10];                  
    private String[][] guesses = new String[6][5];
    
    private String secretWord;
    
    public Wordle(){
        
        //Empty Lists
        for(int i=0;i<keyboard.length;i++){
            for(int j=0;j<keyboard[i].length;j++){
                keyboard[i][j] = "";
            }
        }
        for(int k=0;k<guesses.length;k++){
            for(int l=0;l<guesses[k].length;l++){
                guesses[k][l] = "";
            }
        }
        
        //Set Secret Word
        secretWord = getRandom(wordList);
        
        fill(keyboard[0],"qwertyuiop",0);
        fill(keyboard[1],"asdfghjkl",1);
        fill(keyboard[2],"zxcvbnm",2);
    }
    
    public static String getRandom(ArrayList<String> array){
        Random random = new Random();   
        int index = random.nextInt(array.size());
        return array.get(index);
    }
    
    public void fill(String[] line, String letters, int add){
        for(int i=0;i<letters.length();i++){
            line[i+add] = letters.substring(i,i+1);
        }
    }
    
    public String toString(){
        String output = "";
        for(String[] guess: guesses){
            for(String letter: guess){
                output += " " + letter;
            }
            output += "\n";
        }
        output += "\n\n";
        
        for(String[] line: keyboard){
            for(String key: line){
                output += key + " ";
            }
            output += "\n";
        }
        
        return output;
    }
    
    //testing methods
    
    public void randomize(){
        Collections.shuffle(wordList);
    }
    
    public void sort(){
        Collections.sort(wordList);
    }
    
    public String toWords(){
        String output = "";
        for(String word:wordList){
            output += word + " ";
        }
        return output;
    }
    
    //play methods
    
    public boolean guess(String answer, int round){
        String replace = "";
        for(int i=0;i<guesses[round].length;i++){
            if(secretWord.substring(i,i+1).equalsIgnoreCase(answer.substring(i,i+1))){
                guesses[round][i] = answer.substring(i,i+1).toUpperCase();
                replace = answer.substring(i,i+1).toUpperCase();
            }
            else if(secretWord.contains(answer.substring(i,i+1).toLowerCase())){ //!secretWord.substring(i,i+1).equalsIgnoreCase(answer.substring(i,i+1)) && 
                guesses[round][i] = answer.substring(i,i+1).toLowerCase();
                replace = answer.substring(i,i+1).toUpperCase();
            }
            else {
                guesses[round][i] = "\u0336" + answer.substring(i,i+1).toLowerCase();
                replace = " ";
            }
            
            for(int j=0;j<keyboard.length;j++){
                for(int k=0;k<keyboard[j].length;k++){
                    if(keyboard[j][k].equalsIgnoreCase(answer.substring(i,i+1))){
                        keyboard[j][k] = replace;
                        break;
                    }
                }
            }
        }
        if(answer.equalsIgnoreCase(secretWord)) return true;
        return false;
    }
    
    public String getSecret(){
        return secretWord;
    }
    
    public void clear(){
        secretWord = getRandom(wordList);
        
        for(int k=0;k<guesses.length;k++){
            for(int l=0;l<guesses[k].length;l++){
                guesses[k][l] = "";
            }
        }
        
        fill(keyboard[0],"qwertyuiop",0);
        fill(keyboard[1],"asdfghjkl",1);
        fill(keyboard[2],"zxcvbnm",2);
        
    }
    
    
}
