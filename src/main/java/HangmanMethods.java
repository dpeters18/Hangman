import java.util.*;

public class HangmanMethods {
    public void simulation(){
        Scanner scan=new Scanner(System.in);
        ArrayList<Character> guesses=new ArrayList<>();
        int tries=0;
        String word=randomword();
        char[] chars=word.toCharArray();
        char[] blanks=new char[chars.length];
        for (int i=0;i<chars.length;i++)
        {
            blanks[i]='_';
        }
        System.out.println(phase(tries)+"\n"+(new String(blanks))+"\n Missed letters:"+guesses);
        System.out.println("Guess a letter for a word with "+chars.length+" blanks.");

        String inputt=scan.nextLine();
        do {
            while (inputt.length() != 1||!alphabetic(inputt)) {
                System.out.println("Must guess a letter.");
                inputt = scan.nextLine();
            }
            while (guesses.contains(inputt.charAt(0))){
                System.out.println("You already guessed that letter.");
                inputt = scan.nextLine();
                while (inputt.length() != 1||!alphabetic(inputt)) {
                    System.out.println("Must guess a letter.");
                    inputt = scan.nextLine();
                }
            }
            ArrayList<Integer> instas = contains(chars, inputt.charAt(0));
            if (instas.isEmpty()) {
                tries++;
                guesses.add(inputt.charAt(0));
                System.out.println(phase(tries) + "\n" + (new String(blanks))+"\n Missed letters:"+guesses);
                if(tries<6) {
                    System.out.println("Guess another letter.");
                    inputt = scan.nextLine();
                }
                else {
                    System.out.println("You lost. The word was:"+word);
                }
            }
            if (!instas.isEmpty()) {
                for (Integer num : instas)
                    blanks[num] = inputt.charAt(0);
                System.out.println(phase(tries) + "\n" + (new String(blanks))+"\n Missed letters:"+guesses);
                if(hasBlanks(blanks)) {
                    System.out.println("Guess another letter.");
                    inputt = scan.nextLine();
                }
                else{
                    System.out.println("Yes! The secret word is "+word+"! You have won!");
                }
            }
        }while(tries<6&&hasBlanks(blanks));
    }
    public ArrayList<Integer> contains(char[] cha,char letter){
        ArrayList<Integer> indeces=new ArrayList<>();
        for(int i=0;i<cha.length;i++)
        {
            if (cha[i]==letter)
                indeces.add(i);
        }
        return indeces;
    }
    public boolean alphabetic(String s){
        try{
            return Character.isAlphabetic(Character.codePointAt((s.toCharArray()),0));
        }
        catch(Exception e){
            System.out.println("No typed input was given.");
         return false;
        }
    }
    public boolean hasBlanks(char[] s)
    {
        for(char c:s)
        {
            if(c=='_')
                return true;
        }
        return false;
    }
    public String phase(int n){
        ArrayList<String> phases=new ArrayList<>();
        phases.add(" +---+\n     |\n     |\n     |\n     |\n=======");
        phases.add(" +---+\n O   |\n     |\n     |\n     |\n=======");
        phases.add(" +---+\n O   |\n |   |\n     |\n     |\n=======");
        phases.add(" +---+\n O   |\n |   |\n/    |\n     |\n=======");
        phases.add(" +---+\n O   |\n |   |\n/ \\  |\n     |\n=======");
        phases.add(" +---+\n O   |\n/|   |\n/ \\  |\n     |\n=======");
        phases.add(" +---+\n O   |\n/|\\  |\n/ \\  |\n     |\n=======");
        try{
            return phases.get(n);
        }
        catch(Exception e){
            return "Not a valid turn number.";
        }
    }
    public String randomword(){
        ArrayList<String> wordbank=new ArrayList<>();
        wordbank.add("a");
        wordbank.add("frog");
        wordbank.add("is");
        wordbank.add("one");
        wordbank.add("of");
        wordbank.add("many");
        wordbank.add("carnivorous");
        wordbank.add("amphibians");
        wordbank.add("that");
        wordbank.add("has");
        wordbank.add("no");
        wordbank.add("tail");
        wordbank.add("and");
        wordbank.add("starts");
        wordbank.add("from");
        wordbank.add("tadpole");
        wordbank.add("salamander");
        wordbank.add("webbed");
        wordbank.add("feet");
        wordbank.add("tortoise");
        Random rand=new Random();
        return wordbank.get(rand.nextInt(wordbank.size()));
    }
}
