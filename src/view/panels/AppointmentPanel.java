package view.panels;

import controller.AppointmentController;
import dao.AppointmentDAO;
import java.util.List;
import javax.swing.JOptionPane;
import view.components.ConfirmationDialog;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.Appointment;


public class AppointmentPanel extends javax.swing.JPanel {

    private AppointmentController controller;
    private Map<String, Integer> studentMap;
    private Map<String, Integer> counselorMap;

    public AppointmentPanel(Connection connection) {
        initComponents();
        controller = new AppointmentController(this, connection);
        refreshComboBoxes();
        controller.loadAppointmentsIntoTable();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        apointmentVerticalSeporator = new javax.swing.JSeparator();
        appointmentScrollPane = new javax.swing.JScrollPane();
        appointmentTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        couselerComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        datePicker = new org.httprpc.sierra.DatePicker();
        jLabel4 = new javax.swing.JLabel();
        timePicker = new org.httprpc.sierra.TimePicker();
        jLabel5 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        bookButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        studentComboBox = new javax.swing.JComboBox<>();
        cancelButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 340));

        apointmentVerticalSeporator.setForeground(new java.awt.Color(88, 90, 92));
        apointmentVerticalSeporator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        apointmentVerticalSeporator.setName(""); // NOI18N

        appointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Student", "Counseler", "Date", "Time", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        appointmentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        appointmentTable.setShowGrid(true);
        appointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentTableMouseClicked(evt);
            }
        });
        appointmentScrollPane.setViewportView(appointmentTable);
        if (appointmentTable.getColumnModel().getColumnCount() > 0) {
            appointmentTable.getColumnModel().getColumn(0).setResizable(false);
            appointmentTable.getColumnModel().getColumn(1).setResizable(false);
            appointmentTable.getColumnModel().getColumn(2).setResizable(false);
            appointmentTable.getColumnModel().getColumn(3).setResizable(false);
            appointmentTable.getColumnModel().getColumn(4).setResizable(false);
            appointmentTable.getColumnModel().getColumn(5).setResizable(false);
        }

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Student");

        jLabel2.setText("Counseler");

        couselerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jLabel3.setText("Date");

        jLabel4.setText("Time");

        jLabel5.setText("Status");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "Scheduled",
            "Rescheduled",
            "Completed",
            "Cancelled",
            "No Show" }));
statusComboBox.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        statusComboBoxActionPerformed(evt);
    }
    });

    bookButton.setText("Book");
    bookButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bookButtonActionPerformed(evt);
        }
    });

    clearButton.setText("Clear");
    clearButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearButtonActionPerformed(evt);
        }
    });

    studentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

    cancelButton.setText("Cancel");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(couselerComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(studentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(cancelButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bookButton)))
            .addGap(36, 36, 36)
            .addComponent(apointmentVerticalSeporator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(appointmentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(updateButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(deleteButton)))
            .addContainerGap(20, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(apointmentVerticalSeporator, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(studentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(couselerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bookButton)
                                .addComponent(cancelButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(clearButton))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(appointmentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(deleteButton)
                                .addComponent(updateButton))))
                    .addGap(0, 23, Short.MAX_VALUE)))
            .addContainerGap())
    );
    }// </editor-fold>//GEN-END:initComponents

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        controller.handleDeleteAppointment();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
      controller.handleBookAppointment();
    }//GEN-LAST:event_bookButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        controller.handleUpdateAppointment();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void appointmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentTableMouseClicked
        controller.handleSelectedAppointment();
    }//GEN-LAST:event_appointmentTableMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        controller.handleClearInputs();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        controller.handleCancelAppointment();
    }//GEN-LAST:event_cancelButtonActionPerformed

    
    
    
    
    
    



    // Returns the current input values from the form as an Object array
    public Object[] getInputs() {
        return new Object[] {
            studentComboBox.getSelectedItem(),   // Selected student name
            couselerComboBox.getSelectedItem(),  // Selected counselor name
            datePicker.getDate(),                 // Selected appointment date
            timePicker.getTime(),                 // Selected appointment time
            statusComboBox.getSelectedItem()     // Selected appointment status
        };
    }

    // Sets the form inputs to the provided values
    public void setInputs(String student, String counselor, LocalDate date, LocalTime time, String status) {
        studentComboBox.setSelectedItem(student);
        couselerComboBox.setSelectedItem(counselor);
        datePicker.setDate(date);
        timePicker.setTime(time);
        statusComboBox.setSelectedItem(status);
    }

    // Clears all form inputs and any table selection
    public void clearInputs() {
        studentComboBox.setSelectedIndex(-1);   // Deselect any selected student
        couselerComboBox.setSelectedIndex(-1);  // Deselect any selected counselor
        statusComboBox.setSelectedIndex(-1);    // Deselect any selected status
        appointmentTable.clearSelection();       // Remove any row selection in the table
    }

    // Returns the index of the currently selected appointment row in the table, or -1 if none selected
    public int getSelectedAppointmentIndex() {
        return appointmentTable.getSelectedRow();
    }

    // Loads the appointment data into the appointment table
    public void loadAppointmentTable(List<Appointment> appointments) {
        DefaultTableModel model = (DefaultTableModel) appointmentTable.getModel();
        model.setRowCount(0);  // Clear existing table rows
        
        // Add each appointment as a new row
        for (Appointment appt : appointments) {
            model.addRow(new Object[]{
                appt.getId(),
                appt.getStudentName(),
                appt.getCounselorName(),
                appt.getDate(),
                appt.getTime(),
                appt.getStatus()
            });
        }
    }

    // Shows an error dialog with the provided message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Shows an information dialog with the provided message
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Refreshes the student and counselor combo boxes with updated data from the controller
    public void refreshComboBoxes() {
        try {
            // Fetch the latest student and counselor maps from controller
            studentMap = controller.getStudentMap();
            counselorMap = controller.getCounselorMap();

            // Clear current items and repopulate student combo box
            studentComboBox.removeAllItems();
            for (String fullName : studentMap.keySet()) {
                studentComboBox.addItem(fullName);
            }

            // Clear current items and repopulate counselor combo box
            couselerComboBox.removeAllItems();
            for (String fullName : counselorMap.keySet()) {
                couselerComboBox.addItem(fullName);
            }

        } catch (Exception e) {
            // Show error dialog if loading fails and print stack trace
            JOptionPane.showMessageDialog(this, "Failed to load combo boxes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator apointmentVerticalSeporator;
    private javax.swing.JScrollPane appointmentScrollPane;
    private javax.swing.JTable appointmentTable;
    private javax.swing.JButton bookButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> couselerComboBox;
    private org.httprpc.sierra.DatePicker datePicker;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JComboBox<String> studentComboBox;
    private org.httprpc.sierra.TimePicker timePicker;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
