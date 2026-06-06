/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

/**
 *
 * @author daliaelbarbary
 */
//A common loged in user info
public class CurrentUserHolder {

    private static User currentUser;

    // Sets the user after a successful login
    public static void setUser(User u) {
        currentUser = u;
    }

    // Retrieves the user in any GUI screen
    public static User getUser() {
        return currentUser;
    }

    // Clears the data when the user logs out
    public static void clear() {
        currentUser = null;
    }

    // Helper to check if a user is currently active
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
