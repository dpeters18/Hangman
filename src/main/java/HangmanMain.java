import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class HangmanMain {
    static Scanner scan = new Scanner(System.in);
public static void main(String[] args) {

    String guesses="";
    int tries=0;
    Supplier<String> supplier= ()->"_";

    System.out.println("H A N G M A N");
    HangmanMethods method = new HangmanMethods();
    String word=method.getWord();
    String blanks= Stream.generate(supplier).limit(word.length()).toList()
            .stream().reduce("",(c1,c2)->c1+c2);
    method.simulation(guesses,tries,blanks);
    System.out.println("Want to play again? Type 'y' for yes; 'n' for no.");

    while (true) {
        try {
           String input=yesNo();
        if (input.equals("y")) {
            System.out.println("H A N G M A N");
            method = new HangmanMethods();
            word=method.getWord();
            blanks= Stream.generate(supplier).limit(word.length()).toList()
                    .stream().reduce("",(c1,c2)->c1+c2);
            method.simulation(guesses,tries,blanks);
            System.out.println("Want to play again? Type 'y' for yes; 'n' for no.");
            input = scan.nextLine();
        }
        if (input.equals("n")) {
            break;
        }
    }
    catch(Exception e){
        break;
    }
    }
}
    static String yesNo(){
        String input = scan.next();
        if(!Character.isAlphabetic(Character.codePointAt((input.toCharArray()),0))||input.length() != 1) {
            System.out.println("Must guess a letter.");
            return yesNo();
        }
        if(input.equals("y")||input.equals("n"))
            return input;
        else{
            System.out.println("Letter must be 'y' or 'n'.");
            return yesNo();
        }
    }
}
