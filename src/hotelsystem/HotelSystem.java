/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import hotelsystem.Admin;
import hotelsystem.Bill;
import hotelsystem.Booking;
import hotelsystem.BookingController;
import hotelsystem.CurrentUserHolder;
import hotelsystem.Employeee;
import hotelsystem.Guest;
import hotelsystem.InvalidPriceException;
import hotelsystem.Payment;
import hotelsystem.Room;
import hotelsystem.RoomType;
import hotelsystem.hotel;
import hotelsystem.service;
import hotelsystem.serviceType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelSystem {
  
    public static void main(String[] args) {
     
    Scanner input = new Scanner(System.in);
        
        // --- 1. Initial System Setup (Internal) ---
        hotel myHotel = new hotel(101, "Grand Luxe", "Cairo", "Egypt", 5, 0, "123456789", "info@luxe.com", "Maged");
        BookingController controller = new BookingController(20);
        
        // Initial Rooms
        try {
            myHotel.addRoom(new Room(101, RoomType.SINGLE, 500, 1, true, true, true));
            myHotel.addRoom(new Room(202, RoomType.DOUBLE, 800, 2, true, true, true));
            myHotel.addRoom(new Room(303, RoomType.SUITE, 2000, 4, true, true, true));
        } catch (Exception e) {}

        System.out.println("====================================================");
        System.out.println("   WELCOME TO  HOTEL SYSTEM ");
        System.out.println("====================================================");

        try {
            // --- 2. Interactive User Creation (User Class Test) ---
            System.out.println("\n[Phase 1: Account Creation]");
            System.out.print("Enter your Name: ");
            String name = input.nextLine();
            System.out.print("Enter your Email: ");
            String email = input.nextLine();
            System.out.print("Enter your Phone (9 digits): ");
            String phone = input.nextLine();
            System.out.print("Enter your Password (min 10 chars): ");
            String pass = input.nextLine();

            // Create Guest (Testing Validation & Constructor)
            Guest activeGuest = new Guest(name, email, phone, pass, 0, 0);
            CurrentUserHolder.setUser(activeGuest);
            System.out.println("\n Account Created & Logged In as " + activeGuest.getUserName());

            boolean exit = false;
            while (!exit) {
                System.out.println("\n------------------------------------");
                System.out.println("MAIN MENU:");
                System.out.println("1. View Available Rooms (Search)");
                System.out.println("2. Book a Room");
                System.out.println("3. Order Room Service");
                System.out.println("4. View Profile & Checkout (Billing)");
                System.out.println("5. Admin/Staff Simulation (Leave Request)");
                System.out.println("6. Save & Exit");
                System.out.print("Select an option: ");
                
                int choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1: // View Available Rooms
                        System.out.println("\n--- Current Available Rooms ---");
                        for (Room r : myHotel.viewAvailableRooms()) {
                            r.displayRoom();
                        }
                        break;

                    case 2: // Booking Flow (BookingController Test)
                        System.out.println("\nEnter Room Type (SINGLE, DOUBLE, SUITE): ");
                        String typeStr = input.nextLine().toUpperCase();
                        RoomType type = RoomType.valueOf(typeStr);
                        
                        Room found = controller.findAvailableRoom(type);
                        if (found != null) {
                            System.out.print("Enter number of nights: ");
                            int nights = input.nextInt();
                            System.out.print("Enter number of guests: ");
                            int guests = input.nextInt();
                            input.nextLine(); 
                            
                            Booking b = controller.bookRoom(activeGuest, found, nights, guests, "Interactive Booking", LocalDate.now(), LocalDate.now().plusDays(nights));
                            activeGuest.addBooking(b);
                            System.out.println(" Booking Successful! Room " + found.getRoomNumber() + " is now yours.");
                        } else {
                            System.out.println(" No rooms of this type available.");
                        }
                        break;

                    case 3: // Service Flow (Service Class Test)
                        System.out.println("\nAvailable Services: 1. Food ($200)  2. Spa ($500)");
                        System.out.print("Choice: ");
                        int sChoice = input.nextInt();
                        if (sChoice == 1) {
                            service s = new service(701, serviceType.Food, 200, "Dinner Order");
                            System.out.println(" Food ordered.");
                        } else {
                            service s = new service(702, serviceType.Spa, 500, "Full Massage");
                            System.out.println("Spa session booked.");
                        }
                        break;

                    case 4: // Billing & Payment (Bill/Payment Class Test)
                        if (activeGuest.viewBookings().isEmpty()) {
                            System.out.println(" No active bookings to bill.");
                        } else {
                            Booking b = activeGuest.viewBookings().get(0);
                            ArrayList<service> mockServices = new ArrayList<>(); // Simplify for demo
                            Bill bill = new Bill(999, b, mockServices);
                            
                            System.out.println("\n--- FINAL BILL ---");
                            System.out.println("Total Amount Due: $" + bill.getTotalAmount());
                            System.out.print("Proceed to pay? (yes/no): ");
                            if (input.nextLine().equalsIgnoreCase("yes")) {
                                Payment pay = new Payment(111, bill, "Credit Card");
                                pay.processPayment();
                                System.out.println("Payment Successful! Bill Status: " + (bill.getIsPaid() ? "PAID" : "UNPAID"));
                                bill.printBill(); // Saves receipt
                            }
                        }
                        break;

                    case 5: // Admin Simulation (Admin/Employee Test)
                        System.out.println("\n--- ADMIN/STAFF DEMO ---");
                        Admin admin = new Admin("System_Boss", "boss@luxe.com", "111222333", "BossPass@2026", "Manager", 50000, 1);
                        Employeee emp = new Employeee("Staff_User", "staff@luxe.com", "444555666", "StaffPass@2026", "Reception", 5000);
                        
                        admin.addEmployee(emp);
                        System.out.print("Employee Karim requests leave. How many days? ");
                        int days = input.nextInt();
                        emp.requestLeave(days);
                        admin.approveEmployeeLeave(emp, days);
                        break;

                    case 6: //  (File Handling Test)
                        System.out.println("\nSaving data to files...");
                        ArrayList<Room> roomsToSave = new ArrayList<>(myHotel.getRoomsList());
                        Room.saveRooms(roomsToSave);
                        controller.saveBookingsToFile();
                        activeGuest.saveGuestDataToFile();
                        System.out.println(" All data saved. Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (Exception e) {
            System.err.println("\n Error occurred: " + e.getMessage());
            System.out.println("Please restart and enter valid data format.");
        }
        input.close();
    }
}

    
   

        
        
        
    

    

    
    
    
