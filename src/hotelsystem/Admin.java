/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

/**
 *
 * @author daliaelbarbary
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends Employeee {

    private int adminLevel;
    private final ArrayList<Employeee> empList = new ArrayList<>();
    
    
    
    public Admin(String userName, String userEmail, 
        String userPhone, String userPassword, String position, double salary,int adminLevel) throws Exception {

        super(userName, userEmail, userPhone, userPassword, position, salary);
        this.adminLevel = adminLevel;
    }

    // ---Main Methods ---
    // Method to add new employee
    public void addEmployee(Employeee emp) {
        // Check if the employee object is valid
        if (emp != null) {
            //Add to list
            empList.add(emp);
            System.out.println("Success: Employee " + emp.getUserName() + " added.");
        } //Handle empty/missin data
        else {
            System.out.println("Error: Cannot add a null employee.");
        }
    }

    // Method to remove a specific employee
    public void removeEmployee(int employeeID) {
        // Exit if there are no employees
        if (empList.isEmpty()) {
            System.out.println("The list is currently empty.");
            return;
        }

        // Creating a ptr before the first emp in the list 
        Iterator<Employeee> ptr = empList.iterator();

        //search till the last emp
        while (ptr.hasNext()) {
            Employeee e = ptr.next();

            if (e.getUserID() == employeeID) {
                ptr.remove(); // remove safely
                System.out.println("Employee : " + e.getUserName() + " is removed.");
                return;      // Exit immediately 
            }
        }
        //loop finished without finding the employee
        System.out.println("Employee not found.");
    }

    // Method to increase the salary for a specific employee
    // Update their salary
    public void increaseSalary(int employeeID, double amount) {

        //search for each emp in the list
        for (Employeee e : empList) {

            if (e.getUserID() == employeeID) {
                //update the salary
                e.setEmployeeSalary(e.getEmployeeSalary() + amount);
                System.out.println("Salary updated for: " + e.getUserName());
                return;    // Exit immediately 
            }
        }
        //loop finished without finding the employee
        System.out.println("Employee not found.");
    }

    //Helper Methods for Class Employee
    // Method to approve a leave request for a specific employee
    public void approveEmployeeLeave(Employeee emp, int days) {
        // Check if the employee has a pending leave request
        if ("Pending".equals(emp.getLeaveStatus())) {

            //Change the status to Approved
            emp.setLeaveStatus("Approved");

            //Calculate and update the new leave balance
            int updatedBalance = emp.getLeaveBalance() - days;
            emp.setLeaveBalance(updatedBalance);

            System.out.println("Admin Action: Leave request APPROVED for " + days + " days.");
        } else {
            System.out.println("Admin Action: No pending request found to approve.");
        }
    }

    // Method to reject a leave request for a specific employee
    public void rejectEmployeeLeave(Employeee emp) {
        // Check if there is a request to reject
        if ("Pending".equals(emp.getLeaveStatus())) {

            // Update the status to Rejected
            emp.setLeaveStatus("Rejected");
            System.out.println("Admin Action: Leave request REJECTED.");
        } else {
            System.out.println("Admin Action: No pending request found to reject.");
        }
    }

    ///////////////////////////////////////
    // --- Implementation of Abstract Methods ---
    
   
    // Searche for a user by their name and displays their details.
    @Override
    public void searchUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String inputName = sc.nextLine();

        //Loop through the static list in User class
        for (User u : User.allUsers) {

            //Check name using ignoreCase
            if (u.getUserName().equalsIgnoreCase(inputName)) {
                System.out.println("--- " + u.getRole() + " Found ---");
                u.viewProfile();     // Show general info

                //Check for Employee specific data
                if (u.getRole().equals("Employee")) {
                    Employeee e = (Employeee) u;
                    System.out.println("Position: " + e.getEmployeePosition());
                    System.out.println("Salary: " + e.getEmployeeSalary());
                } //for Guest specific data
                else if (u.getRole().equals("Guest")) {
                    Guest g = (Guest) u;
                    System.out.println("Room: " + g.getRoomNumber());
                    System.out.println("Stay: " + g.getStayDays() + " days");
                }
                return;
            }
        }
        //didn't find a user with that name
        System.out.println("No results found for: " + inputName);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    // --- Setters & Getters ---
    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public ArrayList<Employeee> getempList() {
        return empList;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminLevel=" + adminLevel + ", empList=" + empList + '}';
    }

}
