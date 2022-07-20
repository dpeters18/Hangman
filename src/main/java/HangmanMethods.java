
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.nio.file.Files;


public class HangmanMethods {
    Scanner scan=new Scanner(System.in);
    String word;
    Charset utf= StandardCharsets.UTF_8;
    public HangmanMethods(){
        word=randomWord();
    }
    public void simulation(String guesses,int tries,String blanks){
        if(tries==0){
        System.out.println(phase(tries)+"\n"+(blanks)+"\n Guessed letters:"+guesses);
        System.out.println("Guess a letter for a word with "+word.length()+" blanks.");}

       // String inputt=scan.nextLine();
            String validated = ensureALetter();
            ArrayList<String> original=wasGuessed(validated,guesses);
            ArrayList<Integer> instas = contains(word, original.get(0));
            guesses=original.get(1);
            //in case the letter typed is not in the secret word:
            if (instas.isEmpty()) {
                tries++;
                System.out.println(phase(tries) + "\n" + (blanks)+"\n Guessed letters:"+guesses);
                if(tries<6) {
                    System.out.println("Guess another letter.");
                }
                else {
                    System.out.println("You lost. The word was:"+word);
                }
            }

            if (!instas.isEmpty()) {
                String finalGuesses = guesses;
                Optional<String> filledin= Arrays.stream(word.split("")).map(sl -> finalGuesses.contains(sl) ? sl : "_").reduce(String::concat);
                if(filledin.isPresent())
                 blanks= filledin.get();
                System.out.println(phase(tries) + "\n" + (blanks)+"\n Guessed letters:"+guesses);
                if(hasBlanks(blanks)) {
                    System.out.println("Guess another letter.");
                    validated = scan.nextLine();
                }
                else{
                    System.out.println("Yes! The secret word is "+word+"! You have won! Your score is "+(6-tries)+"." +
                            "The highest score is "+highestScore()+".");
                    try{
                        System.out.println("Please type your name for your score to be logged:");
                        String name=scan.next();
                    Files.write(Paths.get("C:/Users/Devon/IdeaProjects/Hangman/src/main/resources/scores.text"),
                            List.of(name+","+ (6 - tries)),utf,
                            StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                    }
                    catch(Exception e){
                        System.out.println("File couldn't be found or written to.");
                    }
                }
            }
        if(tries<6&&hasBlanks(blanks))
            simulation(guesses,tries,blanks);
    }


    String ensureALetter(){
        var input = scan.next();
        if(input.length() != 1||!isAlphabetic(input)) {
            System.out.println("Must guess a letter.");
            return ensureALetter();
        }
        return input;
    }
    ArrayList<String> wasGuessed(String guess,String guesses) {
        if (!guesses.contains(guess)) {
            if(guesses.length()==0){
                return new ArrayList<>(List.of(guess,guess));
            }
           String total=guesses + "," + guess;
           return new ArrayList<>(List.of(guess,total));
        }
        else {
            System.out.println("You already guessed that letter. Previous guesses include:" + guesses);
            String letter=ensureALetter();
           return wasGuessed(letter,guesses);
        }
    }
    record Data(ArrayList<Integer> indices, int counter) {
        Data next(boolean recordIndex) {
            var temp = new ArrayList<>(indices);
            if (recordIndex) {
                temp.add(this.counter);
            }
            return new Data(temp, counter + 1);
        }
    }

    public ArrayList<Integer> contains(String str,String letter){

        return Arrays.stream(str.split("")).reduce(
                new Data(new ArrayList<>(), 0) ,
                (acc, next) -> next.equals(String.valueOf(letter)) ? acc.next(true) : acc.next(false),
                (t1, t2) -> t1
        ).indices;

    }
    public boolean isAlphabetic(String s){
        try{
            return Character.isAlphabetic(Character.codePointAt((s.toCharArray()),0));
        }
        catch(Exception e){
            System.out.println("No typed input was given.");
         return false;
        }
    }
    public boolean hasBlanks(String s)
    {
        return s.contains("_");
    }
    public String phase(int n){

        try{List<String> phases=Files
                .readAllLines(Paths.get("C:/Users/Devon/IdeaProjects/Hangman/src/main/resources/hangmanStates.text"),utf);
            return phases.get(6*n)+"\n"+phases.get(6*n+1)+"\n"+phases.get(6*n+2)+"\n"
                    +phases.get(6*n+3)+"\n"+phases.get(6*n+4)+"\n"+phases.get(6*n+5)+"\n";
        }
        catch(Exception e){
            return "¯\\_(ツ)_/¯";
        }
    }
    public String randomWord(){
        try{
        List<String> wordbank=Files
                .readAllLines(Paths.get("C:/Users/Devon/IdeaProjects/Hangman/src/main/resources/wordbank.text"),utf);
        Random rand=new Random();
        return wordbank.get(rand.nextInt(wordbank.size()));}
        catch(Exception e){
            return "frog";
        }
    }

    public String getWord() {
        return word;
    }
    public int highestScore() {
        try {
        List<String> lines = Files.readAllLines(Paths.get("C:/Users/Devon/IdeaProjects/Hangman/src/main/resources/scores.text"), utf);
        return lines.stream()
                    .map(s -> Integer.parseInt(String.valueOf(s.charAt(s.length() - 1))))
                    .reduce(0, Integer::max);
        }
        catch(Exception e){
        return 0;
        }
    }
}
