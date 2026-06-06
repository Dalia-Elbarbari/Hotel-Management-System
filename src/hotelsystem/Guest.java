/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Guest extends User {

    private int roomNumber;
    private int stayDays;
    private ArrayList<Bill> billCollection = new ArrayList<>();
    private List<Booking> bookingHistory;
    private ArrayList<service> services = new ArrayList<>();

    public Guest(String userName, String userEmail, String userPhone,
            String userPassword ,int roomNumber, int stayDays ) throws Exception {
        super(userName, userEmail, userPhone, userPassword);
        this.roomNumber = roomNumber;
        this.stayDays = stayDays;
        this.bookingHistory = new ArrayList<>();
    }

    
public Guest(){
    
}
    // Getters and Setters
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public int getStayDays() { return stayDays; }
    public void setStayDays(int stayDays) { this.stayDays = stayDays; }

    public void displayInfo() {
        System.out.println("Name: " + userName);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Stay Days: " + stayDays);
        System.out.println("Bookings Count: " + bookingHistory.size());
    }

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    public List<Booking> viewBookings() {
        return bookingHistory;
    }

    public void orderRoomService() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose Service: 1. Food, 2. Spa, 3. Taxi, 4. Gym, 5. Airport Shuttle, 6. Laundry");
        int choice = input.nextInt();

        serviceType selectedType;
        double price;
        String desc;

        switch (choice) {
            case 1: selectedType = serviceType.Food; price = 200; desc = "Food Service"; break;
            case 2: selectedType = serviceType.Spa; price = 500; desc = "Spa Service"; break;
            case 3: selectedType = serviceType.Taxi; price = 150; desc = "Taxi Service"; break;
            case 4: selectedType = serviceType.Gym; price = 100; desc = "Gym Access"; break;
            case 5: selectedType = serviceType. AirportShuttle; price = 300; desc = "Airport Shuttle"; break;
            case 6: selectedType = serviceType.Laundry; price = 120; desc = "Laundry Service"; break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        int id = services.size() + 1;
        service newService = new service(id, selectedType, price, desc);
        services.add(newService);
        System.out.println("Service added successfully!");
    }

    @Override
    public String getRole() { 
        return "Guest"; 
    }

    @Override
    public void searchUser() {
        System.out.println(userName + " is searching for available rooms...");
    }

    public double PayBill() {
        Scanner input = new Scanner(System.in);
        double totalPaid = 0.0;

        System.out.println("--- Payment Process ---");

        if (bookingHistory == null || bookingHistory.isEmpty()) {
            System.out.println("No bookings found to pay for.");
            return 0.0;
        }

        for (Booking b : bookingHistory) {
            
            if (b.totalPrice > 0) {
                System.out.println("Paying for Booking ID: " + b.getBookingID()+ " | Amount: $" + b.totalPrice);
                totalPaid += b.totalPrice;
                b.processPayment(); 
            }
        }

        System.out.println("Total Amount Paid: $" + totalPaid);
        System.out.println("Thank you for your payment!");
        return totalPaid;
    }

    public void viewBill(int billID) {
        for (Bill b : billCollection) {
            if (b.getBillID() == billID) {
                b.printBill(); 
                return;
            }
        }
        System.out.println("Bill not found.");
    }
    
    public void saveGuestDataToFile() {
     
        String fileName = "Guest_" + userName + "_Data.txt";

       
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            
            writer.write("--- Guest Report ---\n");
            writer.write("Name: " + userName + "\n");
            writer.write("Room Number: " + roomNumber + "\n");
            writer.write("Stay Days: " + stayDays + "\n");
            writer.write("Total Bookings: " + bookingHistory.size() + "\n");
            writer.write("---------------------\n");
            
            System.out.println("Success: Guest data saved to " + fileName);
            
        } catch (java.io.IOException e) {
            
            System.out.println("Error: Could not save to file. " + e.getMessage());
        }
    }
    
}