import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mgheiler on 1/22/2016.
 */
public class Game {

    ArrayList colors;
    Integer[] code;

    public ArrayList getColors() {
        return colors;
    }

    public Integer[] getCode() {
        return code;
    }

    public void askForColors() {
        Scanner input = new Scanner(System.in);

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
        this.colors = colors;

        input.close();
    }

    public void askForCode() {
        Scanner input = new Scanner(System.in);
        // Ask for code
        System.out.print("[1234] Enter the code (Using numbers): ");
        String rawCode = input.nextLine();
        Integer[] code = new Integer[rawCode.length()];

        // Parse code into integer array
        for (int i = 0; i < rawCode.length(); i++) {
            code[i] = rawCode.charAt(i) - '0';
        }

        for (int i = 0; i < code.length; i++) {
            System.out.println(code[i]);
        }
        this.code = code;
        input.close();
    }
}
