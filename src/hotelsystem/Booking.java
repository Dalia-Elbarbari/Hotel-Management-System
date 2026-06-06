/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotelsystem;
import java.time.LocalDate;
public class Booking implements Payable {

    private static int counter = 1;

    private int bookingID;
    private Room room;
    protected double totalPrice;
    private Payment paymentTransaction;
    private bookingStatus status;
       private String customerName;
    private int duration;
    private int numberOfGuests;
    private String specialRemarks;
    private User customer;
private LocalDate checkInDate;
    private LocalDate checkOutDate;
   
    public Booking() {
        this.bookingID = counter++;
        this.status = bookingStatus.Requested;
    }

    
   public void setRoom(Room room) {
    this.room = room;
    recalcPrice();
}

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        recalcPrice();
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setSpecialRemarks(String specialRemarks) {
        this.specialRemarks = specialRemarks;
    }

    public void setStatus(bookingStatus status) {
        this.status = status;
    }

    public void setPaymentTransaction(Payment paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

 
   
  public void updateBooking(int duration, int guests, String remarks) {
    this.duration = duration;
    this.numberOfGuests = guests;
    this.specialRemarks = remarks;
    recalcPrice();
}
    public void cancelBooking() {
        this.status = bookingStatus.Cancelled;
        
    }


    public void confirmBooking() {
        if (totalPrice > 0) {
            this.status = bookingStatus.Confirmed;
        }
    }

    // ================= PAYMENT =================
    @Override
    public void processPayment() // Sends payment to the Payment class
    {
        if (paymentTransaction != null) {
            paymentTransaction.processPayment();
        }
    }

    @Override
    public double calculateAmount()//Returns the total price of the booking
    {
        return totalPrice;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    
    public int getBookingID() { return bookingID; }
    public Room getRoom() { return room; }
    public User getCustomer() { return customer; }
    public double getTotalPrice() { return totalPrice; }
    public bookingStatus getStatus() { return status; }
  

    public static int getCounter() {
        return counter;
    }

    public Payment getPaymentTransaction() {
        return paymentTransaction;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getSpecialRemarks() {
        return specialRemarks;
    }    
    
    
    public void setDates(LocalDate checkIn, LocalDate checkOut) {
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        
    }

    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }

  
    // for Bill GUI 
    public String getCustomerName() {
        return customerName;
    }
    private void recalcPrice() {
        if (room != null && duration > 0) {
            this.totalPrice = room.getPricePerNight() * duration;
        }
    }
}