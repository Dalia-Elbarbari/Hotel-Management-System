/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

import java.util.ArrayList;
import java.util.List;

public class hotel { 
    
    private int hotelID;
    private String name;
    private String address;
    private String city;
    private int starRating;
    private int totalRooms;
    private int availableRooms;
    private List<String> facilities;
    private List<Room> roomsList; 
    private String contactNumber;
    private String email;
    private String managerName;

    public hotel(int hotelID, String name, String address, String city, int starRating,
                 int totalRooms, String contactNumber, String email, String managerName) {
        this.hotelID = hotelID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.starRating = starRating;
        this.totalRooms = 0; 
        this.availableRooms = 0;
        this.facilities = new ArrayList<>();
        this.roomsList = new ArrayList<>(); 
        this.contactNumber = contactNumber;
        this.email = email;
        this.managerName = managerName;
    }

    // --- Getters and Setters ---

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public List<Room> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(List<Room> roomsList) {
        this.roomsList = roomsList;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    

    public void addRoom(Room room) {
        roomsList.add(room); 
        totalRooms = roomsList.size(); 
        if(room.isAvailable()) {
            availableRooms++;
        }
    }

    public void addCustomer(Guest customer) {
        System.out.println("Customer added: " + customer.getUserName());
    }

    public List<Room> viewAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : roomsList) {
            if (room.isAvailable()) {
                available.add(room);
            }
        }
        return available;
    }

    public void addFacility(String facility) {
        facilities.add(facility);
    }
}
