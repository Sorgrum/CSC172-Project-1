import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

	    Scanner input = new Scanner(System.in);
	    Boolean active = true;
	    while (active) {

		    MasterMind masterMind = new MasterMind();
		    String[] colorsArray = masterMind.getColors();
		    int[] code = masterMind.getCode();


		    // Calculate all possibilities
		    ArrayList<String> possibilities = new ArrayList<>();

		    // Convert colorsArray to an int array. Each int represents the position of the color in the color array
		    char[] set = new char[colorsArray.length];
		    for (int i = 0; i < colorsArray.length; i++) {
			    set[i] = (char) (i + '0');
		    }
		    MasterMind.generateAllPossibilities(possibilities, set, code.length);
		    ArrayList<int[]> possibilitiesIntArray = MasterMind.convertArrayListToIntArrayOfIntArrays(possibilities);
		    // All possibilities calculated

		    Boolean solved = false;
		    Boolean solvedEarlyBoolean = false;
		    Boolean foundPossibilities = false;
		    int solvedEarly = 0;

		    int[] guess = new int[code.length];

		    // Generate first guess
		    for (int i = 0; i < code.length; i++) {
			    guess[i] = 0;
		    }
		    int numberOfGuesses = 0;


		    while (!foundPossibilities) {
			    // For some reason, this needs to be run twice
			    for (int k = 0; k < colorsArray.length * 2; k++) {


				    if (MasterMind.numberOfPositionsAndColorRight(code, guess) == code.length) {
					    solved = true;
					    solvedEarlyBoolean = true;
					    solvedEarly = guess[0];
				    }
				    int numberOfColorsFoundInCurrentGuess = MasterMind.numberOfPositionsAndColorRight(code, guess);
				    int inspectionIndex = 0;

				    // First phase of code breaking, guess the same color and remove from the array the possibilities that
				    // can't be part of the correct guess
				    for (int i = 0; i < possibilitiesIntArray.size(); i++) {

					    int numberOfCorrectFound = 0;

					    //if (numberOfColorsFoundInCurrentGuess > 0) {
					    for (int j = 0; j < code.length; j++) {
						    int currentValue = possibilitiesIntArray.get(inspectionIndex)[j];
						    if (guess[0] == currentValue) {
							    numberOfCorrectFound++;
						    }
					    }


					    if (numberOfCorrectFound != numberOfColorsFoundInCurrentGuess) {
						    possibilitiesIntArray.remove(inspectionIndex);
					    } else {
						    if (inspectionIndex < possibilitiesIntArray.size()) {
							    inspectionIndex++;
						    }
					    }

				    }


				    for (int i = 0; i < guess.length; i++) {
					    if (guess[i] < colorsArray.length) {
						    guess[i] = guess[i] + 1;
					    } else {
						    guess[i] = 0;
					    }
				    }

				    numberOfGuesses++;
			    }

			    numberOfGuesses /= 2;
			    foundPossibilities = true;

		    }

		    // Print the guesses
		    int index = 0;
		    int printIndex = 0;
		    String result = "";

		    if (solvedEarlyBoolean) {
			    int[] tmpGuess = new int[code.length];
			    for (int j = 0; j < code.length; j++) {
				    tmpGuess[j] = 0;
			    }
			    for (; printIndex <= solvedEarly; printIndex++) {

				    // Result for this guess
				    for (int i = 0; i < MasterMind.numberOfPositionsAndColorRight(code, tmpGuess); i++) {
					    result = result + " •";
				    }

				    // Create the response string (Either filled or empty circles)
				    String stringGuess = "[";
				    for (int i = 0; i < code.length; i++) {
					    // Do this for not the last element
					    if (i != code.length - 1) {
						    stringGuess += colorsArray[tmpGuess[i]] + ", ";
					    } else {
						    stringGuess += colorsArray[tmpGuess[i]];
					    }
				    }
				    stringGuess += "]";
				    // Print out the guess
				    System.out.println("Guess " + (printIndex + 1) + ": " + stringGuess + "    " +
						    "Response: " + result);

				    // Set up the next guess output
				    for (int j = 0; j < code.length; j++) {
					    tmpGuess[j] += 1;
				    }
			    }
		    } else {
			    int[] tmpGuess = new int[code.length];
			    for (int j = 0; j < code.length; j++) {
				    tmpGuess[j] = 0;
			    }
			    for (; printIndex < colorsArray.length; printIndex++) {

				    result = "";

				    // Result for this guess
				    for (int i = 0; i < MasterMind.numberOfPositionsAndColorRight(code, tmpGuess); i++) {
					    result = result + " •";
				    }

				    // Create the response string (Either filled or empty circles)
				    String stringGuess = "[";
				    for (int i = 0; i < code.length; i++) {
					    // Do this for not the last element
					    if (i != code.length - 1) {
						    stringGuess += colorsArray[tmpGuess[i]] + ", ";
					    } else {
						    stringGuess += colorsArray[tmpGuess[i]];
					    }
				    }
				    stringGuess += "]";

				    System.out.println("Guess " + (printIndex + 1) + ": " + stringGuess + "    " +
						    "Response: " + result);

				    // Set up the next guess
				    for (int j = 0; j < code.length; j++) {
					    tmpGuess[j] += 1;
				    }
			    }
		    }

		    while (!solved) {

			    // Create the response string (Either filled or empty circles)
			    String stringGuess = "[";
			    for (int i = 0; i < code.length; i++) {
				    // Do this for not the last element
				    if (i != code.length - 1) {
					    stringGuess += colorsArray[possibilitiesIntArray.get(index)[i]] + ", ";
				    } else {
					    stringGuess += colorsArray[possibilitiesIntArray.get(index)[i]];
				    }
			    }
			    stringGuess += "]";

			    // Create the response string (Either filled or empty circles)
			    result = "";
			    int accountedNumberOfWhites = MasterMind.numberOfColorsRightPositionsWrong(code, possibilitiesIntArray
					    .get(index)) - MasterMind.numberOfPositionsAndColorRight(code, possibilitiesIntArray.get
					    (index));

			    if (accountedNumberOfWhites < 0) {
				    accountedNumberOfWhites = 0;
			    }

			    // Result for this guess
			    for (int i = 0; i < MasterMind.numberOfPositionsAndColorRight(code, possibilitiesIntArray.get(index)); i++) {
				    result = result + " •";
			    }
			    // Result for this guess
			    for (int i = 0; i < accountedNumberOfWhites; i++) {
				    result = result + " ◦";
			    }
			    if (MasterMind.numberOfPositionsAndColorRight(code, possibilitiesIntArray.get(index)) == code.length) {
				    solved = true;
			    }

			    System.out.println("Guess " + (printIndex + 1) + ": " + stringGuess + "    " +
					    "Response: " + result);
			    index++;
			    printIndex++;
			    numberOfGuesses += 1;
		    }
		    // End printing guesses

		    numberOfGuesses -= 1; // Account for the 1 that is added when the code is found
		    if (solvedEarlyBoolean) {
			    numberOfGuesses = solvedEarly;
			    numberOfGuesses += 1;
		    }
		    System.out.println("Game over!");
		    System.out.println("Number of guesses: " + numberOfGuesses); //number of guesses gets over counted by one because once the
		    // code is found, it still counts towards the number of guesses
		    System.out.println("");
		    System.out.println("Play again?");
		    String replayResponse = input.nextLine();

		    if (!replayResponse.toLowerCase().startsWith("y")) {
			    active = false;
		    }

	    }
	    System.out.println("Thanks for playing!");
    }
}




