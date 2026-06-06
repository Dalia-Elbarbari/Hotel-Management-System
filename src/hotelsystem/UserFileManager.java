/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

/**
 *
 * @author daliaelbarbary
 */
import java.io.*;
import java.util.ArrayList;

public class UserFileManager {

    // Saves the users list to files
    public static void saveUsersData() {
        // Outer try block catches any general errors during the saving process
        try {
            // Saving to users.txt
            try (ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("users.txt"))) {
                oos1.writeObject(User.allUsers);
            }

            // Saving to login.txt 
            try (ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("login.txt"))) {
                oos2.writeObject(User.allUsers);
            }

            System.out.println("Data successfully saved to users.txt and login.txt");
        } // Catch block prevents the app from crashing if the disk is full or file is locked
        catch (IOException e) {
            System.out.println("Error while saving files: " + e.getMessage());
        }
    }

    // Loads the users list from the file
    public static void loadUsersData() {
        File file = new File("users.txt");

        // Stop if the file doesn't exist to avoid errors
        if (!file.exists()) {
            return;
        }

        // Try to read the file; automatically closes the file when done
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            // Read and cast
            User.allUsers = (ArrayList<User>) ois.readObject();

            // Logic to keep the ID counter correct after loading
            if (!User.allUsers.isEmpty()) {
                int maxID = 0;
                for (User u : User.allUsers) {
                    if (u.getUserID() > maxID) {
                        maxID = u.getUserID();
                    }
                }
                User.setNextID(maxID + 1);   // Set counter to last ID + 1
            }
            System.out.println("Data loaded successfully from users.txt");

        } // Multi-catch: handles missing files or missing classes in one place
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading data: " + e.getMessage());
        }
    }
}
