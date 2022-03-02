import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String name, breed;
        int age, count=0;

        Dog[] dogPound = new Dog[10];

        Scanner keyboard = new Scanner(System.in);

        // Reading from the binary file dogs.dat
        File binaryFile = new File("dogs.dat");
        // Check to see if file exists AND non-zero size
        System.out.println("Previously saved dogs from binary file:");
        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // Read the entire array into dogPound
                // readObject returns the Object datatype
                // Dog[] = Object
                dogPound = (Dog[]) fileReader.readObject();
                // Loop through array, print out all objects
                while (dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else
            System.out.println("[None, please enter new dog data]");

        do {
            System.out.println("Please enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if (name.equalsIgnoreCase("quit"))
                break;
            System.out.println("Please enter dog's breed: ");
            breed = keyboard.nextLine();
            System.out.println("Please enter dog's age: ");
            age = keyboard.nextInt();
            keyboard.nextLine();

            // 1) Create a new Dog object, 2) Add to array, 3) Increment count
            dogPound[count++] = new Dog(name, breed, age);

        } while (true);

        // After the user quits, write the dogPound array to binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
