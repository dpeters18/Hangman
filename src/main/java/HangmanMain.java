import java.util.*;

public class HangmanMain {

public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("H A N G M A N");
    HangmanMethods method = new HangmanMethods();
    method.simulation();
    System.out.println("Want to play again?Type 'y' for yes; 'n' for no.");
    String inputt = scan.nextLine();
    while (true) { try {
        while (!(inputt.equals("y")) && !(inputt.equals("n")) && !method.alphabetic(inputt)) {
            System.out.println("Type 'y' for yes; 'n' for no.");
            inputt = scan.nextLine();
        }
        if (inputt.equals("y")) {
            System.out.println("H A N G M A N");
            method.simulation();
            System.out.println("Want to play again?Type 'y' for yes; 'n' for no.");
            inputt = scan.nextLine();
        }
        if (inputt.equals("n")) {
            break;
        }
    }
    catch(Exception e){
        break;
    }
    }
}
}
