/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

//import hotelsystem.serviceType;

/**
 *
 * @author dinam
 */
public class service {
    
    private int serviceId;
    private serviceType type;
    private double price;
    private String description;

    
    public service(int serviceId, serviceType type, double price, String description) {
        this.serviceId = serviceId;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    
    public serviceType getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

   
    public int getServiceId() {
        return serviceId;
    }

    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setType(serviceType type) {
        this.type = type;
    }
    
}
