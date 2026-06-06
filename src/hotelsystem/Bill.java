/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;
/**
 *
 * @author LamisEsmat
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class Bill {
    private final int billID;
    private final double totalAmount;
    public boolean isPaid;
    private final Booking booking; 
    private final ArrayList<service> services;
    private final double Discount = 0;

    public Bill(int billID, Booking booking, ArrayList<service> services) {
        this.billID = billID;
        this.booking = booking;
        this.services = services;
        this.isPaid = false;
        this.totalAmount = calculateTotalAmount();
    }

    // --- Calculation ---
    public double calculateTotalAmount() {
        double total = 0;
        if (booking != null) {
            total += booking.calculateAmount(); 
        }
        for (service s : services) {
            total += s.getPrice();
        }
        return total;
    }

    // ---  Exception Handling ---

    /**
     *
     * @param status
     * @throws Exception
     */
    public void updateStatus(boolean status)throws Exception {
        if (this.isPaid && status == true) {
            // Requirement 3: Throwing a meaningful exception
            throw new Exception("Update Failed: Bill " + billID + " is already paid.");
        }
        this.isPaid = status;
    }

    //  File Handling  
    public void printBill() {
        
        File file = new File("Bill_Report " + billID + ".txt");
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(file); 
            pw = new PrintWriter(fw);

            pw.println("******* HOTEL RECEIPT *****");
            pw.println("Bill ID: " + billID);
            pw.println("Status: " + (isPaid ? "PAID" : "UNPAID"));
            pw.println("Total Amount: $" + totalAmount);
            pw.println("                             ");
            

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
           
            if (pw != null) pw.close();
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                System.out.println("Error closing stream.");
            }
        }
    }

    //  Setters and Getters
 public double getTotalAmount() { 
     return totalAmount; }
 
    public boolean getIsPaid() { 
        return isPaid; }
    
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getBillID() {
        return billID;
    }
    
    public Booking getBooking() {
    return booking;
}

public ArrayList<service> getServices() {
    return services;
}
//helper methods for GUI 
    public double getRoomTotal() {
    return booking != null ? booking.calculateAmount() : 0;
}

public double getServiceTotal() {
    double total = 0;
    for (service s : services) {
        total += s.getPrice();
    }
    return total;
}

public String getCustomerName() {
    return booking != null ? booking.getCustomerName() : "";
}

    public double getDiscount() {
        return Discount;
    }
   
}
