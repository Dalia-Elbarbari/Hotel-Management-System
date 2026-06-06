/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelsystem.Gui;

import hotelsystem.Admin;
import hotelsystem.Booking;
import hotelsystem.CurrentUserHolder;
import hotelsystem.Employeee;
import hotelsystem.Guest;
import hotelsystem.User;
import hotelsystem.UserFileManager;
import java.util.List;

/**
 *
 * @author daliaelbarbary
 */
public class UserProfileForm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserProfileForm.class.getName());

    private final User currentUser; // Stores the logged-in user for this screen

    public UserProfileForm() {
        this.currentUser = CurrentUserHolder.getUser(); // Receive the user object

        // Safety check: redirect to login if no session is found
        if (this.currentUser == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Session expired. Please login again.");
            new LoginForm().setVisible(true);
            this.dispose();
            return;
        }

        initComponents();     // Build the UI components

        // Initial state: Hide everything special first
        //Hide Employee fields
        EmployeePanel.setVisible(false);
        SeparatorEmployee.setVisible(false);
        //Hide Guest fields
        SeparatorGuest1.setVisible(false);

        btnBookingHistory.setVisible(false);
        //Hide Admin fields
        btnManageEmployee.setVisible(false);

        //Hide Edit fields
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        // Lock fields so user can't type by accident
        txtName.setEditable(false);
        txtEmail.setEditable(false);
        txtPhone.setEditable(false);

        // Now, run our custom logic to show the right parts
        checkPermissions();
        showData();

        // Center the form on screen
        this.setLocationRelativeTo(null);

    }

    private void checkPermissions() {
        String role = currentUser.getRole(); // Get the user's role 

        switch (role) {
            case "Admin":
                // Admins see their specific fields
                EmployeePanel.setVisible(true);
                SeparatorEmployee.setVisible(true);
                btnManageEmployee.setVisible(true);
                //set geust fields to hidden
                SeparatorGuest1.setVisible(false);
                btnBillCollection.setVisible(false);
                btnServices.setVisible(false);
                btnBookingHistory.setVisible(false);
                break;

            case "Employee":
                EmployeePanel.setVisible(true);
                SeparatorEmployee.setVisible(true);
                // Employees do NOT see admin tools or admin level
                lblAdminLevel.setVisible(false);
                txtlevel.setVisible(false);
                btnManageEmployee.setVisible(false);
                //set geust fields to hidden
                SeparatorGuest1.setVisible(false);
                btnBillCollection.setVisible(false);
                btnServices.setVisible(false);
                btnBookingHistory.setVisible(false);
                break;

            case "Guest":
                // Guest see their specific data
                SeparatorGuest1.setVisible(true);
                btnBillCollection.setVisible(true);
                btnServices.setVisible(true);
                btnBookingHistory.setVisible(true);
                // set Employee/Admin fields to hidden
                EmployeePanel.setVisible(false);
                SeparatorEmployee.setVisible(false);
                btnManageEmployee.setVisible(false);
                break;

            default:
                // Safety fallback
                EmployeePanel.setVisible(false);
                break;

        }
    }

    private void showData() {

        // Fill basic info shared by everyone
        txtID.setText(String.valueOf(currentUser.getUserID()));
        txtName.setText(currentUser.getUserName());
        txtPhone.setText(currentUser.getUserPhone());
        txtEmail.setText(currentUser.getUserEmail());

        // Check if user is an Employee to access salary/position
        // Cast to Employee type
        if (currentUser instanceof Employeee emp) {
            txtSalary.setText(String.valueOf(emp.getEmployeeSalary()));
            txtPosition.setText(emp.getEmployeePosition());

            // Further check: if they are an Admin, show the level
            // Cast to Admin type
            if (currentUser instanceof Admin adm) {
                txtlevel.setText(String.valueOf(adm.getAdminLevel()));
            }
        }
    }

    private void finishEditing() {
        // Lock the fields again
        txtName.setEditable(false);
        txtEmail.setEditable(false);
        txtPhone.setEditable(false);

        // Reset button visibility
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        btnEdit.setVisible(true);
        btnChangePassword.setVisible(true);

        switch (currentUser.getRole()) {
            case "Admin":
                btnManageEmployee.setVisible(true);
                break;

            case "Guest":
                btnBillCollection.setVisible(true);
                btnServices.setVisible(true);
                btnBookingHistory.setVisible(true);
                break;

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MyProfile = new javax.swing.JLabel();
        EmployeePanel = new javax.swing.JPanel();
        txtSalary = new javax.swing.JTextField();
        lblSalary = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        txtPosition = new javax.swing.JTextField();
        txtlevel = new javax.swing.JTextField();
        lblAdminLevel = new javax.swing.JLabel();
        btnBackToHotel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        SeparatorEmployee = new javax.swing.JSeparator();
        SeparatorGuest1 = new javax.swing.JSeparator();
        btnBookingHistory = new javax.swing.JButton();
        btnBillCollection = new javax.swing.JButton();
        btnServices = new javax.swing.JButton();
        btnManageEmployee = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        EndLine1 = new javax.swing.JSeparator();
        infoSide = new javax.swing.JPanel();
        lbl_ID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(680, 500));
        setPreferredSize(new java.awt.Dimension(670, 550));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MyProfile.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        MyProfile.setText("My Profile");
        getContentPane().add(MyProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));

        txtSalary.setEditable(false);
        txtSalary.setText("jTextField1");
        txtSalary.addActionListener(this::txtSalaryActionPerformed);

        lblSalary.setText("Salary :");

        lblPosition.setText("Position :");

        txtPosition.setEditable(false);
        txtPosition.setText("jTextField1");

        txtlevel.setEditable(false);
        txtlevel.setText("jTextField1");
        txtlevel.addActionListener(this::txtlevelActionPerformed);

        lblAdminLevel.setText("Admin level : ");

        javax.swing.GroupLayout EmployeePanelLayout = new javax.swing.GroupLayout(EmployeePanel);
        EmployeePanel.setLayout(EmployeePanelLayout);
        EmployeePanelLayout.setHorizontalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSalary)
                    .addComponent(lblAdminLevel))
                .addGap(59, 59, 59)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(txtSalary, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtlevel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EmployeePanelLayout.setVerticalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPosition))
                .addGap(33, 33, 33)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalary)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdminLevel)))
        );

        getContentPane().add(EmployeePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 430, 150));

        btnBackToHotel.setBackground(new java.awt.Color(174, 198, 207));
        btnBackToHotel.setText("Back");
        btnBackToHotel.setHideActionText(true);
        btnBackToHotel.addActionListener(this::btnBackToHotelActionPerformed);
        getContentPane().add(btnBackToHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 150, 30));

        btnEdit.setBackground(new java.awt.Color(255, 255, 186));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 150, 30));

        btnLogOut.setBackground(new java.awt.Color(255, 183, 178));
        btnLogOut.setText("Log Out");
        btnLogOut.setHideActionText(true);
        btnLogOut.addActionListener(this::btnLogOutActionPerformed);
        getContentPane().add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 150, 30));

        btnChangePassword.setBackground(new java.awt.Color(186, 225, 255));
        btnChangePassword.setText("Change Password");
        btnChangePassword.addActionListener(this::btnChangePasswordActionPerformed);
        getContentPane().add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 150, 30));

        SeparatorEmployee.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(SeparatorEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 10, 350));

        SeparatorGuest1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(SeparatorGuest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 10, 210));

        btnBookingHistory.setBackground(new java.awt.Color(230, 230, 250));
        btnBookingHistory.setText("Booking History");
        btnBookingHistory.setHideActionText(true);
        btnBookingHistory.addActionListener(this::btnBookingHistoryActionPerformed);
        getContentPane().add(btnBookingHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 190, 35));

        btnBillCollection.setBackground(new java.awt.Color(199, 233, 191));
        btnBillCollection.setText("Bill Collection");
        btnBillCollection.setHideActionText(true);
        btnBillCollection.addActionListener(this::btnBillCollectionActionPerformed);
        getContentPane().add(btnBillCollection, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 190, 35));

        btnServices.setBackground(new java.awt.Color(255, 229, 180));
        btnServices.setText("Services");
        btnServices.setHideActionText(true);
        btnServices.addActionListener(this::btnServicesActionPerformed);
        getContentPane().add(btnServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 190, 35));

        btnManageEmployee.setBackground(new java.awt.Color(203, 213, 255));
        btnManageEmployee.setText("Manage Employee");
        btnManageEmployee.setHideActionText(true);
        btnManageEmployee.addActionListener(this::btnManageEmployeeActionPerformed);
        getContentPane().add(btnManageEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 190, 38));
        btnManageEmployee.getAccessibleContext().setAccessibleName(" Manage");

        btnSave.setBackground(new java.awt.Color(168, 230, 207));
        btnSave.setText("Save");
        btnSave.setHideActionText(true);
        btnSave.addActionListener(this::btnSaveActionPerformed);
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 150, 30));

        btnCancel.setBackground(new java.awt.Color(255, 218, 193));
        btnCancel.setText("Cancel");
        btnCancel.setHideActionText(true);
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 150, 30));
        getContentPane().add(EndLine1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 190, 10));

        lbl_ID.setText("ID :");

        txtID.setEditable(false);
        txtID.setText("jTextField1");
        txtID.addActionListener(this::txtIDActionPerformed);

        lblName.setText("Name :");

        txtName.setEditable(false);
        txtName.setText("jTextField1");

        lblEmail.setText("Email Address : ");

        txtPhone.setEditable(false);
        txtPhone.setText("jTextField1");

        lblPhone.setText("Phone Number :");

        txtEmail.setEditable(false);
        txtEmail.setText("jTextField1");

        javax.swing.GroupLayout infoSideLayout = new javax.swing.GroupLayout(infoSide);
        infoSide.setLayout(infoSideLayout);
        infoSideLayout.setHorizontalGroup(
            infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoSideLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblPhone)
                    .addComponent(lblName)
                    .addComponent(lbl_ID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        infoSideLayout.setVerticalGroup(
            infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoSideLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(infoSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone)))
        );

        getContentPane().add(infoSide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 430, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //Enable typing in the text fields
        txtName.setEditable(true);
        txtEmail.setEditable(true);
        txtPhone.setEditable(true);

        //Switch visibility: Show Save/Cancel, hide Edit
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        //other btn hidden
        btnEdit.setVisible(false);
        btnChangePassword.setVisible(false);
        btnBillCollection.setVisible(false);
        btnServices.setVisible(false);
        btnBookingHistory.setVisible(false);
        btnManageEmployee.setVisible(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed

        //Ask for current password to verify identity
        String currentPass = javax.swing.JOptionPane.showInputDialog(this, "Enter Current Password:");

        if (currentPass != null && currentPass.equals(currentUser.getUserPassword())) {

            //Ask for the new password
            String newPass = javax.swing.JOptionPane.showInputDialog(this, "Enter New Password (min 10 chars):");

            if (newPass != null) {
                //Use your User class method logic
                currentUser.changePassword(newPass);

                //Check if it passed your 10-char rule
                if (currentUser.getUserPassword().equals(newPass)) {
                    UserFileManager.saveUsersData(); // Save to your txt file
                    javax.swing.JOptionPane.showMessageDialog(this, "Password changed successfully!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error: Password too short!", "Validation Failed", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (currentPass != null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Incorrect current password.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnManageEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageEmployeeActionPerformed
        // Create the new Admin Control Panel (No parameters needed now)
        AdminControlePanel adminPanel = new AdminControlePanel();

        // Show the admin screen
        adminPanel.setVisible(true);

        // Close the current Profile screen
        this.dispose();
    }//GEN-LAST:event_btnManageEmployeeActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtlevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlevelActionPerformed

    private void btnBookingHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingHistoryActionPerformed

        if (currentUser instanceof Guest guest) {
            // Use the viewBookings() method from your Guest class
            List<Booking> history = guest.viewBookings();

            if (history == null || history.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "No bookings found.");
                return;
            }

            StringBuilder sb = new StringBuilder("--- Your Bookings ---\n");
            for (Booking b : history) {
                sb.append("ID: ").append(b.getBookingID())
                        .append(" | Status: ").append(b.getStatus())
                        .append(" | Total: $").append(b.calculateAmount()).append("\n");
            }
            javax.swing.JOptionPane.showMessageDialog(this, sb.toString());
        }


    }//GEN-LAST:event_btnBookingHistoryActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        //Reset text fields to the original object data
        showData();

        //Return to "View Mode"
        finishEditing();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBillCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillCollectionActionPerformed
        if (currentUser instanceof Guest guest) {
            // This calls your method which loops through bookings and processes payment
            double totalPaid = guest.PayBill();

            if (totalPaid > 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Payment Successful!\nTotal Amount: $" + totalPaid
                        + "\nThank you for choosing our hotel.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No outstanding bills to pay.");
            }
        }
    }//GEN-LAST:event_btnBillCollectionActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed

        //Call the logOut method from your User class to update status
        if (currentUser != null) {
            currentUser.logOut();
        }

        //Create an instance of the Login screen
        //Replace 'LoginForm' with the actual name of your login class
        LoginForm login = new LoginForm();

        //Show the login screen
        login.setVisible(true);

        //Close the current profile screen
        this.dispose();

        //Optional: Show a confirmation message
        javax.swing.JOptionPane.showMessageDialog(this, "Logged out successfully!");

    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicesActionPerformed
        if (currentUser instanceof Guest) {
            String[] options = {"Food", "Spa", "Taxi", "Gym", "Shuttle", "Laundry"};
            String selection = (String) javax.swing.JOptionPane.showInputDialog(this,
                    "Select a Service:", "Room Service",
                    javax.swing.JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selection != null) {
                javax.swing.JOptionPane.showMessageDialog(this, selection + " service has been requested!");
            }
        }
    }//GEN-LAST:event_btnServicesActionPerformed

    private void btnBackToHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToHotelActionPerformed
        //Create the Hotel window and pass the current user back to it
        HotellGUI hotel = new HotellGUI();

        //Make the hotel screen visible
        hotel.setVisible(true);

        //Close the current Profile screen
        this.dispose();
    }//GEN-LAST:event_btnBackToHotelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        try {
            //Update the User object with text currently in the fields
            //Trigger validation logic automatically
            currentUser.updateProfile(txtName.getText(), txtEmail.getText(), txtPhone.getText());

            //Save the updated list to the text file
            UserFileManager.saveUsersData();

            //Refresh screen (this reverts the text if validation failed)
            showData();

            //Return to "View Mode" (Lock fields and hide buttons)
            finishEditing();

            javax.swing.JOptionPane.showMessageDialog(this, "Changes saved successfully!");
        } catch (Exception e) {
            // If validation fails, show the specific error message to the user
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Update failed: " + e.getMessage(),
                    "Validation Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);

            // Reload original data into fields to clear the invalid input
            showData();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel for a modern UI appearance */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Failed to set Look and Feel", ex);
        }

        /* Create and display the form on the Event Dispatch Thread (EDT) */
        java.awt.EventQueue.invokeLater(() -> {
            // Ensure a user is logged in via CurrentUserHolder before launching
            // If CurrentUserHolder.getUser() is null, the constructor will redirect to LoginForm
            new UserProfileForm().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmployeePanel;
    private javax.swing.JSeparator EndLine1;
    private javax.swing.JLabel MyProfile;
    private javax.swing.JSeparator SeparatorEmployee;
    private javax.swing.JSeparator SeparatorGuest1;
    private javax.swing.JButton btnBackToHotel;
    private javax.swing.JButton btnBillCollection;
    private javax.swing.JButton btnBookingHistory;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnManageEmployee;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnServices;
    private javax.swing.JPanel infoSide;
    private javax.swing.JLabel lblAdminLevel;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lbl_ID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtlevel;
    // End of variables declaration//GEN-END:variables
}
