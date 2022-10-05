import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static void printArray(int[] array) {
        for (int a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static int[] createRandomArray(int arrayLength) {
        int[] array = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String fileName) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (int a : array) {
                fileWriter.write(a + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered an IOException");
            e.printStackTrace();
        }
    }

    public static int[] readArrayFromFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Integer> arrayList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                int a = Integer.parseInt(str);
                arrayList.add(a);
            }
            int[] array = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                array[i] = arrayList.get(i);
            }
            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            // printArray(array);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("Welcome to the array sorting program!\nThis program utilizes bubble sort to sort arrays!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int[] array = null;
            int arrayLength = 0;
            String fileName = null;

            System.out.println();
            System.out.println(
                    "Enter \"1\" if you would like to generate an array of random integers and store it in a file.");
            System.out.println(
                    "Enter \"2\" if you would like to read an existing file containing a list of integers (one integer per line), sort it and store the sorted array in another file.");
            System.out.println("Enter \"3\" if you would like to exit the program.");
            System.out.println();
            System.out.print(">");

            int userInput = scanner.nextInt();
            // need to add exception handling here for non-integers

            if (userInput == 1) {
                System.out.println();
                System.out.println("How many integers would you like in your array? Please enter a positive integer.");
                System.out.println();
                System.out.print(">");

                // need to add exception handling here
                arrayLength = scanner.nextInt();
                array = createRandomArray(arrayLength);

                System.out.println();
                System.out.println(
                        "Your array of random integers between 0 and 100 has been created. The values in your array are listed below:");
                System.out.println();

                printArray(array);

                System.out.println();
                System.out.println(
                        "Please enter a file name without a file extension to store your array. Your array will be stored in a \".txt\" file.");
                System.out.println();
                System.out.print(">");

                // need to add exception handling here
                fileName = scanner.next() + ".txt";
                writeArrayToFile(array, fileName);

                System.out.println();
                System.out.println("Your array can be found in " + fileName);
            } else if (userInput == 2) {
                System.out.println();
                System.out.println(
                        "Please enter the file name without the file extension containing the array that you would like to sort.");
                System.out.println();
                System.out.print(">");

                // need to add exception handling here
                fileName = scanner.next() + ".txt";
                array = readArrayFromFile(fileName);

                System.out.println();
                System.out.print("Here is your array prior to being sorted: ");
                printArray(array);

                bubbleSort(array);

                System.out.print("Here is your array after being sorted: ");
                printArray(array);

                fileName = fileName.replace(".txt", "Sorted.txt");
                writeArrayToFile(array, fileName);
                System.out.println();
                System.out.println("Your sorted array can be found in " + fileName);
            } else if (userInput == 3) {
                System.out.println();
                System.out.println("Thank you for using the array sorting program. Goodbye!");
                break;
            } else {
                System.out.println();
                System.out.println("Your input is not handled by the program. Please try entering another commmand.");
                // continue;
            }
        }
        scanner.close();
    }
}