package hotelsystem;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LenOvo
 */
public class Employeee extends User {

    private String employeePosition;
    private double employeeSalary;
    private int leaveBalance = 21; 
    private String leaveStatus = "No Request";

    public Employeee( String userName, String userEmail, String userPhone,
            String userPassword , String position , double salary  ) throws Exception {
        super(userName, userEmail, userPhone, userPassword);
        this.employeePosition = position;
        this.employeeSalary = salary;
    }

     // Getters and Setters
     public String getEmployeePosition() {
        return employeePosition;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
   public void displayInfo() {
    System.out.println("Name: " + userName);
    System.out.println("Position: " + employeePosition);
    System.out.println("Salary: " + employeeSalary);
    
}
   // Method for the employee to submit a request
    // Method for the employee to submit a request with Exception Handling
    public void requestLeave(int days) {
        try {
            System.out.println("--- Processing Leave Request for: " + userName + " ---");

            if (days <= 0) {
                throw new IllegalArgumentException("Days must be a positive number.");
            }

            if (days > leaveBalance) {
                throw new Exception("Insufficient leave balance! Your current balance is: " + leaveBalance);
            }

            this.leaveStatus = "Pending";
            System.out.println("Success: Request for " + days + " days has been submitted.");

        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Leave Request Denied: " + e.getMessage());
            
        } finally {
            System.out.println("System Log: Leave request attempt finished.");
            System.out.println("Current Status: " + leaveStatus);
        }
    }
    // Getters and Setters
    public int getLeaveBalance() { return leaveBalance; }
    public void setLeaveBalance(int leaveBalance) { this.leaveBalance = leaveBalance; }

    public String getLeaveStatus() { return leaveStatus; }
    public void setLeaveStatus(String leaveStatus) { this.leaveStatus = leaveStatus; }
    
    @Override
    public String getRole() { return "Employee"; }

   @Override
    public void searchUser() {
        System.out.println(userName + " is searching in the system...");
    }
    
     }