package view.panels;

import controller.FeedbackController;
import view.components.ConfirmationDialog;
import java.sql.*;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.Feedback;
import javax.swing.JOptionPane;

public class FeedbackPanel extends javax.swing.JPanel {
    private FeedbackController controller;
    private Map<String, Integer> studentMap;
    private Map<String, Integer> counselorMap;
    private Map<String, Integer> appointmentMap;

    public FeedbackPanel(Connection connection) {
        initComponents();
        this.controller = new FeedbackController(this, connection);
        refreshComboBoxes();
        controller.loadFeedbackIntoTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        feedbackPanel = new javax.swing.JPanel();
        feedbackVerticalSeporator = new javax.swing.JSeparator();
        feedbackScrollPane = new javax.swing.JScrollPane();
        feedbackTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        ratingComboBox = new javax.swing.JComboBox<>();
        clearButton = new javax.swing.JButton();
        commentScollPane = new javax.swing.JScrollPane();
        commentTextPane = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        appointmentComboBox = new javax.swing.JComboBox<>();
        studentComboBox = new javax.swing.JComboBox<>();
        counselerComboBox = new javax.swing.JComboBox<>();

        feedbackPanel.setPreferredSize(new java.awt.Dimension(644, 331));

        feedbackVerticalSeporator.setForeground(new java.awt.Color(88, 90, 92));
        feedbackVerticalSeporator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        feedbackVerticalSeporator.setName(""); // NOI18N

        feedbackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Student Name", "Councelor Name", "Appointment", "Rating", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        feedbackTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        feedbackTable.setShowGrid(true);
        feedbackTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedbackTableMouseClicked(evt);
            }
        });
        feedbackScrollPane.setViewportView(feedbackTable);
        if (feedbackTable.getColumnModel().getColumnCount() > 0) {
            feedbackTable.getColumnModel().getColumn(0).setResizable(false);
            feedbackTable.getColumnModel().getColumn(1).setResizable(false);
            feedbackTable.getColumnModel().getColumn(3).setResizable(false);
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

        jLabel1.setText("Counseler");

        jLabel2.setText("Rating");

        jLabel3.setText("Comment");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        ratingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5" }));

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        commentScollPane.setViewportView(commentTextPane);

        jLabel4.setText("Student");

        jLabel5.setText("Appointment");

        appointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5" }));

        studentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5" }));

        counselerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout feedbackPanelLayout = new javax.swing.GroupLayout(feedbackPanel);
        feedbackPanel.setLayout(feedbackPanelLayout);
        feedbackPanelLayout.setHorizontalGroup(
            feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, feedbackPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearButton)
                    .addComponent(submitButton)
                    .addGroup(feedbackPanelLayout.createSequentialGroup()
                        .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(feedbackPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, feedbackPanelLayout.createSequentialGroup()
                                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)))
                        .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ratingComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 132, Short.MAX_VALUE)
                                .addComponent(counselerComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(studentComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(appointmentComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(commentScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(feedbackVerticalSeporator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(feedbackPanelLayout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(feedbackScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(475, 475, 475))
        );
        feedbackPanelLayout.setVerticalGroup(
            feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(feedbackVerticalSeporator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(feedbackPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(feedbackScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(updateButton))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(feedbackPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(appointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(counselerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(feedbackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(commentScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(feedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(feedbackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String type = "feedback entry";
        String details = "Good"; // Read from selected row

        if (ConfirmationDialog.confirmDelete(this, type, details)) {
            // Remove from db and clear table
            controller.deleteFeedbackLog();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        controller.handleSubmitFeedback();
    }//GEN-LAST:event_submitButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        controller.handleClearInputs();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        controller.handleUpdateFeedback();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void feedbackTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackTableMouseClicked
        // TODO add your handling code here:
        controller.handleSelectedFeedback();
    }//GEN-LAST:event_feedbackTableMouseClicked

    // Returns the current input values from the feedback form as an Object array
    public Object[] getInputs() {
        return new Object[] {
            studentComboBox.getSelectedItem(), // Selected student name
            counselerComboBox.getSelectedItem(), // Selected counselor name
            appointmentComboBox.getSelectedItem(),// Selected appointment label/id
            ratingComboBox.getSelectedItem(), // Selected rating (likely Integer or String)
            commentTextPane.getText() // Entered comments text
        };
    }
    
    //When a row in the panel is clicked, the data moves into the combo boxes and text fields.
    public void setInputs(String student, String counselor, String appointment, int rating, String comments) {
        studentComboBox.setSelectedItem(student);
        counselerComboBox.setSelectedItem(counselor);
        appointmentComboBox.setSelectedItem(appointment);
        ratingComboBox.setSelectedItem(String.valueOf(rating)); // Convert int rating to String for combo box
        commentTextPane.setText(comments);
    }
    
    // Returns the index of the currently selected feedback row in the table, or -1 if none selected
    public int getSelectedFeedbackIndex() {
        return feedbackTable.getSelectedRow();
    }
    
    // Shows an error dialog with the specified message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Shows an informational dialog with the specified message
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Clears all input fields and clears any selection in the feedback table
    public void clearInputs() {
        studentComboBox.setSelectedIndex(-1);
        counselerComboBox.setSelectedIndex(-1);
        appointmentComboBox.setSelectedIndex(-1);
        ratingComboBox.setSelectedIndex(-1);
        commentTextPane.setText("");
        feedbackTable.clearSelection();
    }
    
    // Loads the feedback list into the feedback table UI component
    public void loadFeedbackTable(List<Feedback> feedbackList) {
        DefaultTableModel model = (DefaultTableModel) feedbackTable.getModel();
        model.setRowCount(0);  // Clear existing rows

        for (Feedback fb : feedbackList) {
            model.addRow(new Object[]{
                fb.getId(),
                
                // Lookup student name by studentId from studentMap (key=Name, value=Id)
                studentMap.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(fb.getStudentId()))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(""),  // Default empty if not found

                // Lookup counselor name by counselorId
                counselorMap.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(fb.getCounselorId()))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(""),
                
                // Lookup appointment label by appointmentId
                appointmentMap.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(fb.getAppointmentId()))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(""),

                fb.getRating(),
                fb.getComments()
            });
        }
    }
    
    
    // Refreshes the student, counselor, and appointment combo boxes with latest data from controller
    public void refreshComboBoxes() {
        try {
            // Get updated maps of name -> id for students, counselors, and appointments
            studentMap = controller.getStudentMap();
            counselorMap = controller.getCounselorMap();
            appointmentMap = controller.getAppointmentMap();

            // Populate student combo box with student names
            studentComboBox.removeAllItems();
            for (String name : studentMap.keySet()) {
                studentComboBox.addItem(name);
            }

            // Populate counselor combo box with counselor names
            counselerComboBox.removeAllItems();
            for (String name : counselorMap.keySet()) {
                counselerComboBox.addItem(name);
            }

            // Populate appointment combo box with appointment labels
            appointmentComboBox.removeAllItems();
            for (String label : appointmentMap.keySet()) {
                appointmentComboBox.addItem(label);
            }
            
            // Populate rating combo box with ratings
            ratingComboBox.removeAllItems();
            for(int i = 1; i <= 5; i++){
                ratingComboBox.addItem(String.valueOf(i));
            }
            
            // Populate comments text box with the comments
            commentTextPane.setText(commentTextPane.getText());

        } catch (Exception e) {
            // Show error dialog if combo boxes fail to load
            showError("Failed to load combo boxes: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> appointmentComboBox;
    private javax.swing.JButton clearButton;
    private javax.swing.JScrollPane commentScollPane;
    private javax.swing.JTextPane commentTextPane;
    private javax.swing.JComboBox<String> counselerComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel feedbackPanel;
    private javax.swing.JScrollPane feedbackScrollPane;
    private javax.swing.JTable feedbackTable;
    private javax.swing.JSeparator feedbackVerticalSeporator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> ratingComboBox;
    private javax.swing.JComboBox<String> studentComboBox;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
