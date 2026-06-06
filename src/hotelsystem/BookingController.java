/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingController {

    
    
    private ArrayList<Booking> bookings = new ArrayList<>();

    private int availableRooms;
    private int totalRooms;
private ArrayList<Room> allRooms = new ArrayList<>();
public BookingController(int totalRooms) {
    this.totalRooms = totalRooms;
    this.availableRooms = totalRooms;
    
    try {
        allRooms.add(new Room(101, RoomType.SINGLE, 200, 1, true, true, true));
        allRooms.add(new Room(102, RoomType.DOUBLE, 350, 2, true, true, true));
        allRooms.add(new Room(201, RoomType.SUITE, 500, 2, true, true, true));
        allRooms.add(new Room(202, RoomType.SUITE, 500, 2, true, true, true));
    } catch (InvalidPriceException e) {
       
        System.out.println("Error adding room: " + e.getMessage());
    }
}

public Room findAvailableRoom(RoomType type) {
    for (Room r : allRooms) {
        if (r.getRoomType() == type && r.isAvailable()) {
            return r;
        }
    }
    return null; 
}

    // ================= BOOK ROOM =================
  

public Booking bookRoom(Guest guest, Room room, int duration, int guestsCount, 
                        String remarks, LocalDate checkIn, LocalDate checkOut) {

    // check if room is available
    if (room == null || !room.isAvailable()) {
        System.out.println("Room not available!");
        return null;
    }

    Booking newBooking = new Booking();
    newBooking.setCustomer(guest);
    newBooking.setRoom(room);
    newBooking.setDuration(duration);
    newBooking.setNumberOfGuests(guestsCount);
    newBooking.setSpecialRemarks(remarks);
    newBooking.setDates(checkIn, checkOut);

    
    room.setAvailable(false);
    availableRooms--;

    bookings.add(newBooking);

    return newBooking;
}
    // ================= CANCEL BOOKING =================
  public boolean cancelBooking(int bookingID) {
    for (int i = 0; i < bookings.size(); i++) {
        Booking b = bookings.get(i);
        if (b.getBookingID() == bookingID) {
            if (b.getRoom() != null) {
                b.getRoom().setAvailable(true);
                availableRooms++;
            }
            bookings.remove(i); // This addresses the "removed from collection" 
            return true;
        }
    }
    return false;
}
    // ================= UPDATE BOOKING =================
   public void updateBooking(int bookingID,
                          int newDuration,
                          int newGuests,
                          String newRemarks,
                          Room newRoom) {

    for (Booking b : bookings) {

        if (b.getBookingID() == bookingID) {

            // if user wants to change room
            if (newRoom != null && newRoom.isAvailable()) {

                // free old room
                if (b.getRoom() != null && b.getRoom() != newRoom) {
                    b.getRoom().setAvailable(true);
                    availableRooms++;
                }

                // reserve new room
                newRoom.setAvailable(false);
                availableRooms--;

                b.setRoom(newRoom);
            }

            
            b.setDuration(newDuration);
            b.setNumberOfGuests(newGuests);
            b.setSpecialRemarks(newRemarks);

            System.out.println("Booking updated successfully");
            return;
        }
    }

    System.out.println("Booking not found");
}
    // ================= VIEW BOOKINGS =================
   public ArrayList<Booking> viewBookings(User customer) {
    ArrayList<Booking> result = new ArrayList<>();
    for (Booking b : bookings) {
        if (b.getCustomer() != null && b.getCustomer().getUserName().equals(customer.getUserName())) {
            result.add(b);
        }
    }
    return result; 
}

    // ================= GETTERS =================
    public int getAvailableRooms() {
        return availableRooms;
    }

    public int getTotalRooms() {
        return totalRooms;
    }
    
// Method inside the Controller to handle file writing
public void saveBookingsToFile() {
    
    // Open the file "bookings.txt"; 'false' means overwrite the file instead of appending
    try (PrintWriter writer = new PrintWriter(new FileWriter("bookings.txt", false))) { 
        for (Booking b : bookings) {
            // Loop through the list of bookings
            
            // Write booking details formatted as a single line in the file
            writer.println("BookingID:" + b.getBookingID() + 
                           ", Customer:" + b.getCustomer().getUserName() + 
                           ", Room:" + b.getRoom().getRoomNumber() + 
                           ", CheckIn:" + b.getCheckInDate() + 
                           ", CheckOut:" + b.getCheckOutDate() + 
                           ", Total:" + b.getTotalPrice());
        }
        
        // Log success to the console
        System.out.println("Data saved successfully!");
    } catch (IOException e) {
        System.out.println("Error saving: " + e.getMessage());
    }

}

}
