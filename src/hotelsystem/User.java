/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

/**
 *
 * @author daliaelbarbary
 */
import java.util.ArrayList;
import java.io.Serializable;

public abstract class User implements Serializable {

    protected int userID;
    protected String userName;
    protected String userEmail;
    protected String userPhone;
    private String userPassword;
    private boolean loggedIn = false;
    private static int nextID = 0;
    public static ArrayList<User> allUsers = new ArrayList<>();

    // Constructors
    public User() {
        userID = nextID++;
        userName = "";
        userEmail = "";
        userPhone = "";
        userPassword = "";
    }

    public User(String userName, String userEmail, String userPhone, String userPassword) throws Exception {
        this.userID = nextID++;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPassword = userPassword;

        try {
            // This is the missing piece! 
            // It checks the data BEFORE the user is fully accepted.
            validateData();

            // Only add to the list if validation passes
            allUsers.add(this);
        } catch (Exception e) {
            // If validation fails, decrease the ID back (since creation failed)
            nextID--;
            // Throw the error so the GUI (btnCreate) can catch it
            throw e;
        }
    }

    //Main Methods
    // Checks email and password to log in
    public void logIn(String email, String password) {
        boolean emailCorrect = this.userEmail.equals(email);
        boolean passwordCorrect = this.userPassword.equals(password);

        // If both are right, set status to logged in
        if (emailCorrect && passwordCorrect) {
            this.loggedIn = true;
            System.out.println("Success: Welcome back, " + this.userName + "!");
        } else {
            System.out.println("--- Login Failed ---");

            // Show specific error for email or password
            if (!emailCorrect) {
                System.out.println("[-] Email Error...");
            }
            if (!passwordCorrect) {
                System.out.println("[-] Password Error...");
            }
        }
    }

    // Changes status to logged out
    public void logOut() {
        if (loggedIn) {
            loggedIn = false;
            System.out.println("Logged out successfully.");
        } else {
            System.out.println("User is not logged in.");
        }
    }

    // Prints all user information to the screen
    public void viewProfile() {
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + userName);
        System.out.println("Email: " + userEmail);
        System.out.println("Phone: " + userPhone);
        System.out.println("Password: ****");
    }

    // Checks for the datatype
    public boolean validateData() throws Exception {
        // ID must be positive
        if (userID < 0) {
            throw new Exception("User ID Invalid");
        }

        // Name cannot be empty
        if (userName == null || userName.isEmpty()) {
            throw new Exception("Name cannot be empty");
        }

        // Email must contain '@'
        if (userEmail == null || !userEmail.contains("@")) {
            throw new Exception("Email Invalid");
        }

        // Phone must be exactly 9 digits
        if (userPhone.length() != 9) {
            throw new Exception("Phone Number must be 9 digits");
        }

        // Password must be at least 10 characters long
        if (userPassword == null || userPassword.length() < 10) {
            throw new Exception("Password must be at least 10 characters");
        }

        return true;
    }

    // Changes profile data and checks if the new data is valid
    public void updateProfile(String newName, String newEmail, String newPhone) throws Exception {

        // Save current data in case the new data is wrong
        String currentName = this.userName;
        String currentEmail = this.userEmail;
        String currentPhone = this.userPhone;

        try {
            // Try setting new values
            this.userName = newName;
            this.userEmail = newEmail;
            this.userPhone = newPhone;

            validateData();  // Run to check
            System.out.println("Profile updated successfully");

        } catch (Exception e) {
            //check fails, revert back to old data
            System.out.println("Update failed: " + e.getMessage());
            this.userName = currentName;
            this.userEmail = currentEmail;
            this.userPhone = currentPhone;
        }
    }

    //Updates the password if it meets the length rule
    public void changePassword(String newPassword) {
        if (newPassword.length() < 10) {
            System.out.println("Change failed: Password Invalid");
        } else {
            System.out.println("Password changed successfully");
            this.userPassword = newPassword;
        }

    }

    //abstract methods
    public abstract void searchUser();

    public abstract String getRole();

    //Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static void setNextID(int nextID) {
        User.nextID = nextID;
    }

//Getters
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public static int getNextID() {
        return nextID;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userName=" + userName
                + ", userEmail=" + userEmail
                + ", userPhone=" + userPhone + ", userPassword=" + userPassword
                + ", loggedIn=" + loggedIn + '}';
    }

}
