import java.util.ArrayList;
import java.util.Scanner;

/**
 * Student Name: Marcelo Gheiler
 * Filename: MasterMind
 * Date: 1/29/16
 * TA Name: Colin Pronovost
 * Assignment:
 * Lab Day: Monday
 * Lab Time: 5PM
 * Lab Location: CSB 703
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own
 */
public class MasterMind {

	String[] colorsArray;
	int[] code;

	public MasterMind() {
		setColors();
		setCode();
	}

	public void setColors() {
		Scanner input = new Scanner(System.in);

		// Ask and parse colors
		System.out.print("Enter the colors to be used in the game on one line: ");
		String colorsString = input.nextLine();
		String[] colorsArray = colorsString.split("\\s+");
		this.colorsArray = colorsArray;
	}

	public String[] getColors() {
		return this.colorsArray;
	}

	public void setCode() {
		Scanner input = new Scanner(System.in);

		// Ask for code
		System.out.print("[0123] Enter the code (Using numbers): ");
		String rawCode = input.nextLine();
		int[] code = new int[rawCode.length()];

		// Parse code into integer array
		for (int i = 0; i < rawCode.length(); i++) {
			code[i] = rawCode.charAt(i) - '0';
		}

		this.code = code;
	}

	public int[] getCode() {
		return this.code;
	}

	public static void generateAllPossibilities(ArrayList possibilities, char set[], int k) {
		int n = set.length;
		generateAllPossibilitiesRecursive(possibilities, set, "", n, k);
	}

	// The main recursive method to print all possible strings of length k
	public static void generateAllPossibilitiesRecursive(ArrayList possibilities, char set[], String prefix, int n, int
			k) {

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
			generateAllPossibilitiesRecursive(possibilities, set, newPrefix, n, k - 1);
		}
	}

	public static int numberOfPositionsAndColorRight(int[] code, int[] guess) {

		int numberCorrect = 0;
		for (int i = 0; i < code.length; i++) {
			if (code[i] == guess[i]) {
				numberCorrect += 1;
			}
		}

		// I need to subtract by the other method to prevent double counts
		return numberCorrect;
	}

	// This includes colors with the correct position! Account for this when calculating (numberOfPositionsAndColorsRight - numberOfColorsRightPositionsWrong)
	public static int numberOfColorsRightPositionsWrong(int[] code, int[] guess) {

		int numberCorrect = 0;
		Boolean foundForCurrentI;
		for (int i = 0; i < code.length; i++) {
			foundForCurrentI = false;
			for (int j = 0; j < code.length; j++) {
				if (!foundForCurrentI) {
					if (i != j) {
						if (code[i] == guess[j]) {
							numberCorrect += 1;
							foundForCurrentI = true;
						}
					}
				}
			}
		}
		return numberCorrect;
	}

	public static ArrayList<int[]> convertArrayListToIntArrayOfIntArrays(ArrayList<String> possibilities) {
		ArrayList<int[]> possibilitiesIntArray = new ArrayList<>();

		int[] tmpArray; // Use any element in the array, they're all the same

		for (int i = 0; i < possibilities.size(); i++) {
			tmpArray = new int[possibilities.get(0).length()];
			for (int j = 0; j < tmpArray.length; j++) {
				String a = possibilities.get(i);
				char c = a.charAt(j);
				tmpArray[j] = Integer.parseInt(String.valueOf(c));
			}
			possibilitiesIntArray.add(tmpArray);
		}

		return possibilitiesIntArray;
	}
}
