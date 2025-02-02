import java.util.Scanner;
public class WordleRunner
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        Wordle game = new Wordle();
        String letter = "";
        
        do{
            do {
                System.out.print("\nType \"p\" to play game, \"t\" to test GameWheel methods, \"q\" to quit. ");
                letter = input.next();
                if(!letter.equals("p") && !letter.equals("t") && !letter.equals("q")){
                    System.out.println("Instruction not recognized");
                    continue;
                }
                else{
                    break;
                }
            } while(true);
            
            //test
            if(letter.equals("t")){
                do {
                    do {
                        System.out.print("\nType GameWheel method to call (toWords, randomize, sort). Or type \"m\" for main menu. ");
                        letter = input.next();
                        if(!letter.equals("toWords") && !letter.equals("randomize") && !letter.equals("sort") && !letter.equals("m")){
                            System.out.println("Instruction not recognized");
                            continue;
                        }
                        else{
                            break;
                        }
                    } while(true);
                    
                    if(letter.equals("toWords")) System.out.println("\n" + game.toWords());
                    else if(letter.equals("randomize")) game.randomize();
                    else if(letter.equals("sort")) game.sort();
                } while(!letter.equals("m"));
            }
            if(letter.equals("p")){
                String answer = "";
                System.out.println("\nLet's play Wordle!\n");
                for(int i=0;i<6;i++){
                    //System.out.println("Attempt #" + (i+1) + "\n");
                    System.out.println(game + "\n");
                    do {
                        System.out.print("Type a 5 letter word: ");
                        answer = input.next();
                        if(answer.length() != 5){
                            System.out.println("Error: Word should be 5 letters.");
                            continue;
                        }
                        else{
                            break;
                        }
                    } while(true);
                    if(game.guess(answer,i)){
                        System.out.println("\nGood Job! You got the Wordle.");
                        break;
                    }
                    else {
                        System.out.println("Oops, thats was incorrect!");
                    }
                }
                
                System.out.println("\n" + game + "\n");
                System.out.println("\nThe answer was " + game.getSecret());
                game.clear();
            }
            
        } while(!letter.equals("q"));
    }
}
