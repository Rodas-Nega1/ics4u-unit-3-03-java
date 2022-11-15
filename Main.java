/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Rodas Nega
* @version 0.5
* @since   2022-11-12
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is Main Class.
 * Main Class.
 */
final class Main {

    /**
    * The min number for array.
    */
    public static final int ARRAY_SIZE = 250;

    /**
     * This input is invalid.
     */
    public static final String INVALID = "ERROR: Invalid Input";

    /**
    * The max number for array.
    */
    public static final int MAX = 999;

    /**
    * The number of elements in the array.
    */
    public static final int MIN = 0;

    /**
    * Prevent instantiation.
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */

    private Main() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param userArray the userArray.
    * @param userNumber the userNumber.
    * @param lowIndex the lowIndex.
    * @param highIndex the highIndex.
    * @return binarySearch the binarySearch.
    */
    static int binarySearch(final int[] userArray, final int userNumber,
                          final int lowIndex, final int highIndex) {
        // https://www.geeksforgeeks.org/binary-search/
        // Binary search in python, converted to java
        final int returns;
        if (lowIndex > highIndex) {
            returns = -1;

        } else {
            final int mid = (lowIndex + highIndex) / 2;
            if (userNumber == userArray[mid]) {
                returns = mid;

            } else if (userNumber > userArray[mid]) {
                returns = binarySearch(userArray, userNumber, mid
                                + 1, highIndex);
            } else {
                returns = binarySearch(userArray, userNumber,
                                lowIndex, mid - 1);
            }
        }
        return returns;
    }

    /**
    * This starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        System.out.println("Binary Search Program");
        try {
            // Initializing the random class
            final Random randNumber = new Random();

            // Initializing array of numbers
            final int[] randomNumberArray = new int[ARRAY_SIZE];

            // Adding numbers to the array
            for (int counter = 0; counter
                            < randomNumberArray.length; counter++) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }

            // Sorting the array
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // Getting user input as to what number they wish to search for
            final Scanner userInput = new Scanner(System.in);
            System.out.print("What number are you searching for in the array");
            System.out.print(" (integer between 0 and 999): ");
            final int searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            // Ensuring the user inputs an appropriate integer
            if (searchNumber > MAX || searchNumber < MIN) {
                System.out.println(INVALID);
            } else {
                // Using binary search to find the user's
                // chosen number in the array
                final int searchResult = binarySearch(numberArray,
                                searchNumber, 0, numberArray.length - 1);
                // Outputing the results of the search
                System.out.println();
                System.out.println("Your number is in index: " + searchResult);
            }

        // Catches and tells the user that an error occured
        } catch (java.util.InputMismatchException ex) {
            System.out.println();
            System.out.println(INVALID);
        }
    }
}

