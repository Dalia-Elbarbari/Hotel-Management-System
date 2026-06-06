/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package hotelsystem;

/**
 *
 * @author dinam
 */

import java.io.*;
import java.util.ArrayList;


public class Room implements Serializable {
    
    // ===== Room Data (Attributes) =====
    private int RoomNumber;
    private RoomType roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int capacity;
    private boolean hasWifi;
    private boolean hasTV;
    private boolean hasAC;

     // ===== Default Constructor =====
   public Room() {
        this.RoomNumber = 0;
        this.roomType = RoomType.SINGLE;
        this.pricePerNight = 0.0;
        this.capacity = 1;
        this.hasWifi = false;
        this.hasTV = false;
        this.hasAC = false;
        this.isAvailable = true;
    }

     // ===== Parameterized Constructor =====
    public Room (int RoomNumber, RoomType roomType, double pricePerNight, int capacity,
           boolean hasWifi, boolean hasTV, boolean hasAC) throws InvalidPriceException {
    
    if (pricePerNight <= 0) {
        throw new InvalidPriceException(); 
    }
    
    this.RoomNumber = RoomNumber;
    this.roomType = roomType;
    this.pricePerNight = pricePerNight; 
    this.capacity = capacity > 0 ? capacity : 1;
    this.hasWifi = hasWifi;
    this.hasTV = hasTV;
    this.hasAC = hasAC;
    this.isAvailable = true;
}

    // ===== Availability Operations =====
    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean checkAvailability() {
    return isAvailable;
}
  
public boolean bookRoom() {
    if (isAvailable) {
        isAvailable = false;
        return true;
    }
    return false;
}
    
    public void releaseRoom(){
        isAvailable = true;
    }
    
    // This Fubction handles updating room price with validation
    
    public boolean updatePrice(double newPrice) throws InvalidPriceException {
    if (newPrice <= 0) {
        throw new InvalidPriceException();
    }
    this.pricePerNight = newPrice;
    return true;
}
 
// Prints room details
    public void displayRoom(){
        System.out.println("================================");
        System.out.println("Room Number: " + RoomNumber);
        System.out.println("Type: " + roomType);
        System.out.println("Price Per Night: " + pricePerNight);
        System.out.println("Available: " + isAvailable);
        System.out.println("================================");
    }
    
     //====Getters====
    
    public int getRoomNumber() {
        return RoomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    
    public RoomType getRoomType() {
        return roomType;
    }
    
     public boolean isHasAC() {
        return hasAC;
    }


    public int getCapacity() {
        return capacity;
    }

    
    public boolean isHasWifi() {
        return hasWifi;
    }


    public boolean isHasTV() {
        return hasTV;
    }
    
   //====Setters====
    
    // ===== Setters with Validation =====
    public void setCapacity(int capacity) {
    try {
        if (capacity <= 0) {
           
            throw new Exception("Capacity must be at least 1 person!");
        }
        this.capacity = capacity;
    } catch (Exception e) {
        
        System.out.println("ALERT: " + e.getMessage());
        this.capacity = 1; 
    }
}

    
    public void setRoomNumber(int RoomNumber) {
        this.RoomNumber = RoomNumber;
    }

   
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

 

    
    public void setPricePerNight(double pricePerNight) throws InvalidPriceException {
    if (pricePerNight <= 0) {
        throw new InvalidPriceException();
    }
    this.pricePerNight = pricePerNight;
}

   
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    
    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

   
    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }
    
     // ===== File Handling =====
     // Saves list of rooms into a file
    
    public static void saveRooms(ArrayList<Room> rooms) {

    ObjectOutputStream objectOut = null;
    FileOutputStream fileOut = null;

    try {
        fileOut = new FileOutputStream("rooms.dat");
        objectOut = new ObjectOutputStream(fileOut);

        objectOut.writeObject(rooms);

        System.out.println("Saved successfully");

    } catch (IOException e) {   
        System.out.println("Save Error: " + e.getMessage());

    } finally {
        try {
            if (objectOut != null) {
                objectOut.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        } catch (IOException e) {   
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}

    
    // ===== File Loading =====
    // Loads list of rooms from file
public static ArrayList<Room> loadRooms() {

    ArrayList<Room> rooms = new ArrayList<>();

    ObjectInputStream objectIn = null;
    FileInputStream fileIn = null;

    try {
        fileIn = new FileInputStream("rooms.dat");
        objectIn = new ObjectInputStream(fileIn);

        rooms = (ArrayList<Room>) objectIn.readObject();

        System.out.println("Loaded successfully");

    } catch (FileNotFoundException e) {   
       System.out.println("File not found: " + e.getMessage());

    } catch (IOException e) {             
        System.out.println("Load Error: " + e.getMessage());

    } catch (ClassNotFoundException e) {  
        System.out.println("Class Error: " + e.getMessage());

    } finally {
        try {
            if (objectIn != null) {
                objectIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }

    return rooms;
}
}






    





    



