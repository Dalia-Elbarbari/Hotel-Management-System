/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;
/**
 *
 * @author LamisEsmat
 */
import java.util.Date;

//payment class 
public class Payment implements Payable {
    //private attributes 
    private final int paymentID;
    private final double amount;
    private final String paymentDate;
    private String paymentStatus;
    private final String paymentMethod;
    private final Bill bill; 
    
    //payment constructor 
    public Payment(int paymentID, Bill bill, String paymentMethod) {
        this.paymentID = paymentID;
        this.bill = bill;
        this.amount = bill.getTotalAmount(); 
        this.paymentMethod = paymentMethod;
        this.paymentDate = new Date().toString();
        this.paymentStatus = "Pending";
    }

    //  Exception Handling 
    private void validatePayment() throws Exception {
        if (this.amount <= 0) {
            throw new Exception("Invalid Payment: Amount must be greater than zero.");
        }
        if (this.bill == null) {
            throw new Exception("Invalid Payment: No associated bill found.");
        }
        if (this.bill.getIsPaid()) {
            throw new Exception("Invalid Payment: This bill has already been settled.");
        }
    }
 
    //implementing the payable interface functions 
    @Override
    public void processPayment() {
        try {
            
            validatePayment();
            
            System.out.println("Processing payment of " + amount + " via " + paymentMethod);
            this.paymentStatus = "Confirmed";

            this.bill.updateStatus(true); 
            System.out.println("Payment successful.");
            
            } 
        catch (Exception e) {
            System.out.println("Payment Failed: " + e.getMessage());
        } finally {
            
            System.out.println("Transaction attempt finished for ID: " + paymentID);
        }
    }

    @Override
    public double calculateAmount() {
        return this.amount;
    }

    // Getters and Setters
    public int getPaymentID() { return paymentID; }
    public double getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getPaymentMethod() { return paymentMethod; }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    

    public void displayPaymentDetails() {
        System.out.println("ID: " + paymentID + " | Status: " + paymentStatus + " | Amount: " + amount);
    }
}