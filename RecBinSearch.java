import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;

/**.
*
* This program reads multiple lines of strings from an input file.
* It checks two lines at a time:
* one for an array and one for the number to search.
* If both are valid, it searches for the number in the array.
*
* @author Remy Skelton
* @version 1.0
* @since 2025-05-1
*/

final class RecBinSearch {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private RecBinSearch() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {
        // Print the welcome message
        System.out.println("Welcome to the Recursive Binary Search program!");
        System.out.print("This program will read an array ");
        System.out.print("and a number from a file. If valid it will display");
        System.out.println(" the index it was found at in the output file.");

        // Read from input.txt
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);

        // Make outputWriter to write to output.txt
        FileWriter outputWriter = new FileWriter("output.txt");

        // Create outputStr
        String outputStr = "";

        // While loop to read String from input.txt
        while (scanner.hasNextLine()) {

            // Set line equal to the next line
            String line = scanner.nextLine();

            // Split the line by spaces to create the array
            String[] unsortedArrayStr = line.split(" ");

            // Declare the integer array
            int[] sortedArray;

            // Check if the array length is empty
            if (unsortedArrayStr.length == 1
                    && unsortedArrayStr[0].equals("")) {
                // Print an error message
                outputStr += "Invalid: "
                        + line
                        + " is not a valid array of integers.\n";
                continue;
            }

            // Try ctach when converting the string to an integer
            try {
                // Set the integer array to the length of the string array
                sortedArray = new int[unsortedArrayStr.length];
                // Make the array into an integer array
                for (int index = 0; index < unsortedArrayStr.length; index++) {
                    // Add array at index to the integer array
                    sortedArray[index] =
                    Integer.parseInt(unsortedArrayStr[index]);

                }
            } catch (NumberFormatException e) {
                // Print an error message
                outputStr += "Invalid: "
                        + line
                        + " is not a valid array of integers.\n";
                continue;
            }

            // Sort the array
            Arrays.sort(sortedArray);

            // Check line under for number that will be searched for
            if (scanner.hasNextLine()) {

                // Set line equal to the next line
                String numberStr = scanner.nextLine();

                // Check if the number
                try {
                    // Make the string into an integer
                    int number = Integer.parseInt(numberStr);

                    // Check if the number is negative
                    if (number < 0) {
                        // Print an error message
                        outputStr += Arrays.toString(sortedArray)
                                + "\nInvalid: "
                                + numberStr
                                + " is not a valid positive integer.\n";

                        // Restarts the loop if the number is negative
                        continue;
                    } else {
                        // Set low = 0
                        int low = 0;
                        // Set high = length of the array - 1
                        int high = sortedArray.length - 1;
                        // Call the recBinSer method
                        int indexBiSer =
                        recBinSer(sortedArray, number, low, high);

                        // Check if the number is in the array
                        if (indexBiSer == -1) {
                            // Print an error message
                            outputStr += Arrays.toString(sortedArray)
                                    + "\nInvalid: "
                                    + numberStr
                                    + " is not in the array.\n";
                            continue;
                        } else {
                            // Print the index of the number in the array
                            outputStr += Arrays.toString(sortedArray)
                                    + "\nThe value "
                                    + number
                                    + " is at index "
                                    + indexBiSer
                                    + " in the array.\n";
                        }
                    }

                } catch (NumberFormatException e) {
                    // Print an error message
                    outputStr += Arrays.toString(sortedArray) + "\nInvalid: "
                            + numberStr
                            + " is not a valid positive integer.\n";
                    continue;
                }
            }

        }

        // Write the output string to output.txt
        outputWriter.write(outputStr);

        // Print the Fibonacci results to the console
        System.out.println("Binary Search results written to output.txt.");

        // Close the scanner
        scanner.close();

        // Close the output writer
        outputWriter.close();
    }

    /**
     * This method will search for a number in an array using
     * recursive binary search.
     *
     * @param sortedArray The array to search in.
     * @param number The number to search for.
     * @param low The low index of the array.
     * @param high The high index of the array.
     * @return The index of the number in the array or -1 if not found.
     */
    public static int recBinSer(final int[] sortedArray, final int number,
            final int low, final int high) {
        // Set mid to the middle index of the array
        int mid = (low + high) / 2;

        // Check if the low index is greater than the high index
        if (low > high) {
            // Return -1 if the number is not found
            return -1;

        } else if (sortedArray[mid] == number) {
            // Check if the number is equal to the middle element
            // Return the index of the number in the array
            return mid;

        } else {
            // Check if the number is less than the middle element
            if (number < sortedArray[mid]) {
                // Call the method recursively with the low index and mid - 1
                return recBinSer(sortedArray, number, low, mid - 1);

            } else {
                // Call the method recursively with the mid + 1 and high index
                return recBinSer(sortedArray, number, mid + 1, high);
            }
        }
    }
}
