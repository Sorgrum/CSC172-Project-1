import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Boolean solved = false;
        int numberOfAttempts = 1;


        // Ask and parse colors
        System.out.print("Enter the colors to be used in the game on one line: ");
        String colorsString = input.nextLine();
        String[] colorsArray = colorsString.split("\\s+");
        ArrayList<String> colors = new ArrayList<>();

        for (String element : colorsArray) {
            colors.add(element);
        }

        for (int i = 0; i < colorsArray.length; i++) {
            for (int j = i + 1; j < colorsArray.length; j++) {
                if (colorsArray[i].equals(colorsArray[j])) {
                    System.out.println(colorsArray[i] + " already exists in the list of colors. Removing " + colorsArray[i] + " from the list.");
                    colors.remove(i);
                }
            }
        }

        System.out.println(colors);


        // Ask for code
        System.out.print("[1234] Enter the code (Using numbers): ");
        String rawCode = input.nextLine();
        Integer[] code = new Integer[rawCode.length()];

        // Parse code into integer array
        for (int i = 0; i < rawCode.length(); i++) {
            code[i] = rawCode.charAt(i) - '0';
        }


        // Calculate all possibilities
        HashSet<String> possibilities = new HashSet<>();

        char[] set = new char[colorsArray.length];
        for (int i = 0; i < colorsArray.length; i++) {
            set[i] = (char) (i + '0');
        }
        int k = code.length;
        printAllKLength(possibilities, set, k);





        Integer[] guess = new Integer[code.length];
        Integer[] previousGuess = new Integer[code.length];
        Integer[] knownColors = new Integer[code.length];
        int previousRPRC = 0;
        int previousWPRC = 0;

        while (!solved) {


            if (numberOfAttempts == 1) {
                for (int i = 0; i < guess.length; i++) {
                    guess[i] = 0;
                }
            } else if (numberOfAttempts == 2) {
                if ()
            }

            // Check how well the guess did

            // If every position is right
            if (numberOfPositionsAndColorRight(code, guess) == code.length) {
                solved = true;
            }



            //////////////////////////////////////
            numberOfAttempts += 1;

            for (int i = 0; i < guess.length; i++) { previousGuess[i] = guess[i]; }

        }


        System.out.println();
    }

    // The method that prints all possible strings of length k.  It is
    //  mainly a wrapper over recursive function printAllKLengthRec()
    // Taken from http://www.geeksforgeeks.org/print-all-combinations-of-given-length/
    static void printAllKLength(HashSet possibilities, char set[], int k) {
        int n = set.length;
        printAllKLengthRec(possibilities, set, "", n, k);

    }

    // The main recursive method to print all possible strings of length k
    static void printAllKLengthRec(HashSet possibilities, char set[], String prefix, int n, int k) {

        // Base case: k is 0, print prefix
        if (k == 0) {
            possibilities.add(prefix);
            return;
        }

        // One by one add all characters from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {

            // Next character of input added
            String newPrefix = prefix + set[i];

            // k is decreased, because we have added a new character
            printAllKLengthRec(possibilities, set, newPrefix, n, k - 1);
        }
    }

    public static int numberOfPositionsAndColorRight(Integer[] code, Integer[] guess) {

        int numberCorrect = 0;
        for (int i = 0; i < code.length; i++) {
            if (code[i].equals(guess[i])) {
                numberCorrect += 1;
            }
        }

        // I need to subtract by the other method to prevent double counts
        return numberCorrect;
    }

    // This includes colors with the correct position! Account for this when calculating (numberOfPositionsAndColorsRight - numberOfColorsRightPositionsWrong)
    public static int numberOfColorsRightPositionsWrong(Integer[] code, Integer[] guess) {

        int numberCorrect = 0;
        Boolean foundForCurrentI;
        for (int i = 0; i < code.length; i++) {
            foundForCurrentI = false;
            for (int j = 0; j < code.length; j++) {
                if (!foundForCurrentI) {
                    if (i != j) {
                        if (code[i].equals(guess[j])) {
                            numberCorrect += 1;
                            foundForCurrentI = true;
                        }
                    }
                }
            }
        }
        return numberCorrect;
    }

}


